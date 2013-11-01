package it.planetgeeks.mclauncher.updater;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

public class LauncherUpdater
{
	public static String downloadUrl;
	private static String md5;
	private static String size;

	public static void startCheck()
	{
		File launcher = new File(DirUtils.getWorkingDirectory() + File.separator + "launcher" + File.separator + "launcher.jar");
		if (launcher.exists())
		{
			if (getLauncherCheckFile())
			{
				String checkLauncher = checkLauncher(launcher);
				if (checkLauncher.equals("UPDATE"))
				{
					boolean update = true;
					if (Settings.showUpdateConfirm)
					{
						int dialogResult = JOptionPane.showConfirmDialog(null, LanguageUtils.getTranslated("updater.dialogMessage"), LanguageUtils.getTranslated("updater.dialogTitle"), JOptionPane.YES_NO_OPTION);
						update = dialogResult == JOptionPane.YES_OPTION ? true : false;
					}
					if (update)
					{
						Updater.startUpdateThread();
					}
					else
					{
						openLauncher();
					}
				}
				else if (checkLauncher.equals("OK"))
				{
					openLauncher();
				}
			}
			else
			{
				openLauncher();
			}
		}
		else
		{
			if (getLauncherCheckFile())
			{
				Updater.startUpdateThread();
			}
			else
			{
				Launcher.main(new String[] { "start", "ERROR0" });
			}
		}
	}

	private static String checkLauncher(File launcher)
	{
		try
		{
			String temp_md5 = FileUtils.generateBufferedHash(launcher);
			String temp_size = FileUtils.getFileSize(launcher);

			if (temp_md5.equals(md5) && temp_size.equals(size))
			{
				return "OK";
			}
			else
			{
				return "UPDATE";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "UPDATE";
		}
	}

	public static void openLauncher()
	{
		try
		{
			URL url = new File(DirUtils.getWorkingDirectory() + File.separator + "launcher" + File.separator + "launcher.jar").toURI().toURL();
			@SuppressWarnings("resource")
			URLClassLoader classLoader = new URLClassLoader(new URL[] { url }, new LauncherUpdater().getClass().getClassLoader());
			Class<?> myMainClass = classLoader.loadClass("it.planetgeeks.mclauncher.Launcher");
			Method main = myMainClass.getMethod("main", String[].class);
			main.invoke(null, new Object[] { new String[] { "start" } });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static boolean getLauncherCheckFile()
	{
		File check = new File(DirUtils.getLauncherDirectory() + File.separator + "check");

		if (FileUtils.downloadFile(Settings.launcherLink, check))
		{
			BufferedReader br;
			try
			{
				br = new BufferedReader(new FileReader(check));
				String readed = br.readLine();
				while (readed != null)
				{
					if (readed.startsWith("url="))
					{
						String splitted[] = readed.split("=");
						String url = "";
						for (int i = 1; i < splitted.length; i++)
						{
							url += splitted[i];
						}
						downloadUrl = url;
					}
					else if (readed.startsWith("md5="))
					{
						String splitted[] = readed.split("=");
						String temp_md5 = "";
						for (int i = 1; i < splitted.length; i++)
						{
							temp_md5 += splitted[i];
						}
						md5 = temp_md5;
					}
					else if (readed.startsWith("size="))
					{
						String splitted[] = readed.split("=");
						String temp_size = "";
						for (int i = 1; i < splitted.length; i++)
						{
							temp_size += splitted[i];
						}
						size = temp_size;
					}
					readed = br.readLine();
				}
				br.close();
				return true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}

}
