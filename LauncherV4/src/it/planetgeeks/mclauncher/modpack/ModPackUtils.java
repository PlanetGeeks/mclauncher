package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.GameLauncher;
import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherLogger;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.DirUtils.OS;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author PlanetGeeks
 * 
 */

class ThreadGetPacksInfo implements Runnable
{
	@Override
	public void run()
	{
		ModPackUtils.analyzePacks();
	}
}

public class ModPackUtils
{
	public static ArrayList<ModPack> modPacks = new ArrayList<ModPack>();
	public static EnumFilterType filter = EnumFilterType.ALL;
	public static String filterStr = null;
	public static ArrayList<ModPack> filteredList = new ArrayList<ModPack>();
	public static ModPack selected = null;
	public static boolean updatePaused = false;
	public static boolean updateStopped = false;

	public static void startLoading()
	{

		Thread thread = new Thread(new ThreadGetPacksInfo());
		thread.start();
	}

	protected static void analyzePacks()
	{
		modPacks = new ArrayList<ModPack>();
		filter = EnumFilterType.ALL;
		filterStr = null;
		filteredList = new ArrayList<ModPack>();
		selected = null;

		try
		{
			ArrayList<String> urls = getUrls();

			if (urls != null)
			{

				for (int i = 0; i < urls.size(); i++)
				{
					ModPack currentPack = getPack(false, urls.get(i));
					if (currentPack != null)
					{
						addModPack(currentPack);
					}
					Launcher.getLauncherFrame().mainPanel.updateModPacks(modPacks, i != urls.size() - 1 ? false : true);

					Thread.sleep(1);
				}

				if (modPacks.size() > 0)
				{
					LauncherLogger.log(LauncherLogger.INFO, "Loaded " + modPacks.size() + " modpacks!");
				}
				else
				{
					LauncherLogger.log(LauncherLogger.INFO, "No modpack loaded!");
				}
			}
			else
			{
				File[] list = DirUtils.getWorkingDirectory().listFiles();
				for (int i = 0; i < list.length; i++)
				{
					File current = list[i];
					if (current.isDirectory() && !current.getName().equals("launcher"))
					{
						ModPack pack = getPack(true, current.getAbsolutePath());
						if (pack != null)
						{
							addModPack(pack);
						}
						Launcher.getLauncherFrame().mainPanel.updateModPacks(modPacks, true);
					}
				}
			}

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private static ModPack getPack(boolean local, String url)
	{
		String packName = null;
		String packOwner = null;
		String packMcVersion = null;
		String packServerLink = null;
		ImageIcon imgIcon = null;
		ArrayList<String> mods = new ArrayList<String>();
		String setup = new String();
		String setupIndex = null;
		boolean serverLinkDirect = false;
		String packBgLink = null;
		String modsListLink = null;
		String packVersion = null;
		String mainClass = null;
		String tweakClass = null;
		File modpack = null;
		if (local)
		{
			File[] list = (new File(url)).listFiles();
			for (int i = 0; i < list.length; i++)
			{
				if (list[i].getName().endsWith(".modpack"))
				{
					modpack = list[i];
					break;
				}
			}
			if (modpack == null)
			{
				return null;
			}
		}
		else
		{
			modpack = new File(local ? url : DirUtils.getLauncherDirectory() + File.separator + "temp");
		}

		if (modpack.exists() && !local)
		{
			modpack.delete();
		}
		if (local || FileUtils.downloadFile(url, modpack))
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(modpack));
				String readed = br.readLine();
				while (readed != null)
				{
					if (readed.startsWith("name="))
					{
						packName = readed.substring(5);
					}
					else if (readed.startsWith("owner="))
					{
						packOwner = readed.substring(6);
					}
					else if (readed.startsWith("version="))
					{
						packVersion = readed.substring(8);
					}
					else if (readed.startsWith("mcVersion="))
					{
						packMcVersion = readed.substring(10);
					}
					else if (readed.startsWith("serverLink="))
					{
						packServerLink = readed.substring(11);
					}
					else if (readed.startsWith("mods="))
					{
						if (local)
						{
							File[] list = modpack.getParentFile().listFiles();
							for (int a = 0; a < list.length; a++)
							{
								if (list[a].getName().endsWith(".list"))
								{
									modsListLink = readed.substring(5);
									mods = readFileContent(true, list[a].getAbsolutePath());
									break;
								}
							}
						}
						else
						{
							modsListLink = readed.substring(5);
							mods = readFileContent(false, modsListLink);
						}
					}
					else if (readed.startsWith("image="))
					{
						packBgLink = readed.substring(6);
						Image image = null;
						if (local)
						{
							File[] list = modpack.getParentFile().listFiles();
							for (int a = 0; a < list.length; a++)
							{
								if (list[a].getName().endsWith(".png"))
								{
									image = ImageIO.read(list[a]);
									break;
								}
							}
						}
						else
						{
							image = ImageIO.read(new URL(packBgLink));
						}

						if (image != null)
						{
							imgIcon = new ImageIcon(image);
						}
					}
					else if (readed.startsWith("setup="))
					{
						setup = readed.substring(6);
					}
					else if (readed.startsWith("setup-index="))
					{
						setupIndex = readed.substring(12);
					}
					else if (readed.startsWith("serverLink-direct="))
					{
						serverLinkDirect = (readed.substring(18).trim()).equals("true") ? true : false;
					}
					else if (readed.startsWith("mainClass="))
					{
						mainClass = readed.substring(10);
					}
					else if (readed.startsWith("tweakClass="))
					{
						tweakClass = readed.substring(11);
					}

					readed = br.readLine();
				}
				br.close();
			}
			catch (IOException e)
			{
				LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading modpack info!");
				if (!local)
					modpack.delete();
				return null;
			}
			if (!local)
				modpack.delete();
		}
		else
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading modpack info! URL : '" + url + "'");
			return null;
		}

		ModPack returned = new ModPack(packMcVersion, packName, packOwner, packServerLink);
		returned.setModList(mods);
		returned.setPackImage(imgIcon);
		returned.setSetupLink(setup);
		returned.setSetupIndex(setupIndex);
		returned.setServerLinkDirect(serverLinkDirect);
		returned.setModPackLink(url);
		returned.setPackBgLink(packBgLink);
		returned.setModsListLink(modsListLink);
		returned.setMainClass(mainClass);
		returned.setTweakClass(tweakClass);
		returned.setPackVersion(packVersion);

		return returned;
	}

	private static ArrayList<String> readFileContent(boolean local, String url)
	{
		ArrayList<String> list = new ArrayList<String>();

		File file = new File(local ? url : DirUtils.getLauncherDirectory() + File.separator + "tempread");
		if (file.exists() && !local)
		{
			file.delete();
		}
		if (local || FileUtils.downloadFile(url, file))
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String readed = br.readLine();
				while (readed != null)
				{
					list.add(readed);
					readed = br.readLine();
				}
				br.close();
			}
			catch (IOException e)
			{
				LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading file content '" + url + "' !");
				if (!local)
				{
					file.delete();
				}
				return null;
			}

			if (!local)
			{
				file.delete();
			}
		}

		return list;
	}

	private static ArrayList<String> getUrls()
	{
		if (!FileUtils.internetConnected(Settings.modpacks))
		{
			return null;
		}
		ArrayList<String> urls = new ArrayList<String>();
		File modpacks = new File(DirUtils.getLauncherDirectory() + File.separator + "modpacks.list");
		if (modpacks.exists())
		{
			modpacks.delete();
		}
		if (FileUtils.downloadFile(Settings.modpacks, modpacks))
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(modpacks));
				String readed = br.readLine();
				while (readed != null)
				{
					urls.add(readed);
					readed = br.readLine();
				}
				br.close();
			}
			catch (IOException e)
			{
				LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading modpack list!");
				return null;
			}
			modpacks.delete();
		}
		else
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading modpack list! URL : '" + Settings.modpacks + "'");
			return null;
		}
		return urls;
	}

	private static void addModPack(ModPack pack)
	{
		if (pack != null)
		{
			modPacks.add(pack);
		}
		else
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Impossible to add a null pack to modPack list!");
		}
	}

	public static ArrayList<ModPack> getFilteredList(ArrayList<ModPack> inputList, EnumFilterType filter, String str)
	{
		ArrayList<ModPack> returned = new ArrayList<ModPack>();

		for (int i = 0; i < inputList.size(); i++)
		{
			ModPack current = inputList.get(i);

			if (current != null)
			{
				if (filter == EnumFilterType.ALL)
				{
					return inputList;
				}
				else if (filter == EnumFilterType.MCVERSION)
				{
					if (current.mcVersion.contains(str))
						returned.add(current);
				}
				else if (filter == EnumFilterType.PACKNAME)
				{
					if (current.packName.contains(str))
						returned.add(current);
				}
				else if (filter == EnumFilterType.PACKOWNER)
				{
					if (current.packOwner.contains(str))
						returned.add(current);
				}
				else if (filter == EnumFilterType.HASSERVER)
				{
					if (current.packServerLink != null && !current.packServerLink.equals(""))
						returned.add(current);
				}
				else if (filter == EnumFilterType.HASMOD)
				{
					if (current.containMod(str))
						returned.add(current);
				}
				else if (filter == EnumFilterType.DOWNLOADED)
				{
					if (current.getModPackDir().exists())
						returned.add(current);
				}
			}
		}

		return returned;
	}

	public static ArrayList<ModPack> getAllPacks()
	{
		return modPacks;
	}

	private static void loadLatestSetup(ModPack modpack)
	{
		File f = new File(modpack.getModPackDir() + File.separator + "setup.settings");

		if (f != null && f.isFile())
		{
			ArrayList<String> lines = ModPackUtils.readFileContent(true, f.getAbsolutePath());

			ArrayList<String> onlySavePaths = new ArrayList<String>();

			for (String l : lines)
			{
				l = l.split(":")[1] != null ? l.split(":")[1] : "null";

				String[] str = l.split("/");

				String path = modpack.getModPackDir().getAbsolutePath() + File.separator + "files";

				for (int i = 0; i < str.length; i++)
				{
					path += File.separator + str[i];
				}

				onlySavePaths.add(path);
			}

			for (String l : onlySavePaths)
			{
				boolean exists = false;
				for (ModPackFile s : modpack.setup)
				{
					if (l.equals(s.getSaveFile().getAbsolutePath()))
					{
						exists = true;
						break;
					}
				}
				if (!exists)
				{
					File fi = new File(l);
					if (fi.exists())
					{
						fi.delete();
					}
				}
			}
		}
	}

	public static void setupModPack(final ModPack modpack)
	{
		Launcher.setUpdatingModPack(true);

		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				Launcher.getLauncherFrame().southPanel.updateStatus(-1, "", 100, 100, 100);
				modpack.setSetup(readFileContent(false, modpack.setupLink));
				loadLatestSetup(modpack);
				FileUtils.downloadFile(modpack.setupLink, new File(modpack.getModPackDir() + File.separator + "setup.settings"));
				if (ModPackUtils.updateStopped)
				{
					updateStopped = false;
					Launcher.setUpdatingModPack(false);
					return;
				}
				while (updatePaused)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					if (updateStopped)
					{
						updateStopped = false;
						Launcher.setUpdatingModPack(false);
						return;
					}
				}
				if (modpack.setup != null)
				{
					boolean dim = false;
					for (int i = 0; i < modpack.setup.size(); i++)
					{
						i = dim ? i - 1 : i;
						dim = false;
						if (updateStopped)
						{
							updateStopped = false;
							Launcher.setUpdatingModPack(false);
							return;
						}
						while (updatePaused)
						{
							try
							{
								Thread.sleep(1000);
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							if (updateStopped)
							{
								updateStopped = false;
								Launcher.setUpdatingModPack(false);
								return;
							}
						}
						ModPackFile current = modpack.setup.get(i);

						File currentFile = current.getSaveFile();
						if (currentFile.exists())
						{
							Launcher.getLauncherFrame().southPanel.updateStatus(2, currentFile.getName(), 100, i + 1, modpack.setup.size());
							if (Launcher.forceUpdate || (current.check() && !(current.getMD5().equals(FileUtils.generateBufferedHash(currentFile)) && current.getSize().equals(FileUtils.getFileSize(currentFile)))))
							{
								currentFile.delete();
								if (!Launcher.getLauncherFrame().southPanel.setDownloadingFile(current.getDownloadURL(modpack.setupIndex), currentFile, i + 1, modpack.setup.size()))
								{
									dim = true;
								}
							}
						}
						else
						{
							if (current.getOs() == OS.unknown || current.getOs() == DirUtils.getPlatform())
							{
								if (!Launcher.getLauncherFrame().southPanel.setDownloadingFile(current.getDownloadURL(modpack.setupIndex), currentFile, i + 1, modpack.setup.size()))
								{
									dim = true;
								}
							}
						}
					}

					FileUtils.downloadFile(modpack.modpackLink, new File(modpack.getModPackDir() + File.separator + modpack.packName + ".modpack"));
					FileUtils.downloadFile(modpack.packBgLink, new File(modpack.getModPackDir() + File.separator + "packBg.png"));
					FileUtils.downloadFile(modpack.modsListLink, new File(modpack.getModPackDir() + File.separator + "mods.list"));
				    Launcher.setUpdatingModPack(false);
					GameLauncher.launchGame();
				}
				else
				{
					JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.modpacks.update.downloadingMapError"), LanguageUtils.getTranslated("launcher.login.warning"), JOptionPane.WARNING_MESSAGE);
					Launcher.setUpdatingModPack(false);
					GameLauncher.launchGame();
				}
			}

		};

		Thread tr = new Thread(r);
		tr.start();
	}

}
