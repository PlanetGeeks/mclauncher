package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherLogger;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	private static ArrayList<ModPack> modPacks = new ArrayList<ModPack>();
	public static EnumFilterType filter = EnumFilterType.ALL;
	public static String filterStr = null;
	public static ArrayList<ModPack> filteredList = new ArrayList<ModPack>();
	public static ModPack selected = null;

	public static void startLoading()
	{
		Thread thread = new Thread(new ThreadGetPacksInfo());
		thread.start();
	}

	protected static void analyzePacks()
	{
		try
		{
			ArrayList<String> urls = getUrls();

			for (int i = 0; i < urls.size(); i++)
			{
				ModPack currentPack = getPack(urls.get(i));
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
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private static ModPack getPack(String url)
	{
		String packName = null;
		String packOwner = null;
		String packMcVersion = null;
		String packServerLink = null;
		ImageIcon imgIcon = null;
		ArrayList<String> mods = new ArrayList<String>();
		File modpack = new File(DirUtils.getLauncherDirectory() + File.separator + "temp");
		if (modpack.exists())
		{
			modpack.delete();
		}
		if (FileUtils.downloadFile(url, modpack))
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
					else if (readed.startsWith("mcVersion="))
					{
						packMcVersion = readed.substring(10);
					}
					else if (readed.startsWith("serverLink="))
					{
						packServerLink = readed.substring(11);
					}
					else if (readed.startsWith("mod="))
					{
						mods.add(readed.substring(4));
					}
					else if (readed.startsWith("image="))
					{
						Image image = ImageIO.read(new URL(readed.substring(6)));
						if (image != null)
						{
							imgIcon = new ImageIcon(image);
						}
					}

					readed = br.readLine();
				}
				br.close();
			}
			catch (IOException e)
			{
				LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading modpack info!");
				modpack.delete();
				return null;
			}
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

		return returned;
	}

	private static ArrayList<String> getUrls()
	{
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
			}
			modpacks.delete();
		}
		else
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading modpack list! URL : '" + Settings.modpacks + "'");
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
			}
		}

		return returned;
	}

	public static ArrayList<ModPack> getAllPacks()
	{
		return modPacks;
	}

	public static ArrayList<ModPack> getClonedList(ArrayList<ModPack> packs)
	{
		ArrayList<ModPack> modpacks = new ArrayList<ModPack>();

		for (int i = 0; i < packs.size(); i++)
		{
			modpacks.add(packs.get(i).clone());
		}

		return modpacks;
	}
}
