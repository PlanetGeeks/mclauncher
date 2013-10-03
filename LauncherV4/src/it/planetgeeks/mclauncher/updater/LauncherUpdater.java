package it.planetgeeks.mclauncher.updater;

import it.planetgeeks.mclauncher.settings.Settings;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

public class LauncherUpdater
{
	public static void startCheck()
	{
		
		File launcher = new File(DirUtils.getWorkingDirectory() + File.separator + "launcher" + File.separator + "launcher.jar");
		if (launcher.exists())
		{
			String checkLauncher = checkLauncher(launcher);
			if (checkLauncher.equals("UPDATE"))
			{
				boolean update = true;
				if(Settings.showUpdateConfirm)
				{
					int dialogResult = JOptionPane.showConfirmDialog (null, LanguageUtils.getTranslated("updater.dialogMessage"), LanguageUtils.getTranslated("updater.dialogTitle"), JOptionPane.YES_NO_OPTION);
				    update = dialogResult == JOptionPane.YES_OPTION ? true : false;
				}
				if(update)
				{
					Updater.startUpdateThread();
				}
				else
				{
					openLauncher();
				}
			}
			else if(checkLauncher.equals("NOCONNECTION") || checkLauncher.equals("OK"))
			{
				openLauncher();
			}
		}
		else
		{
			Updater.startUpdateThread();
		}

	}

	private static String checkLauncher(File launcher)
	{
		try
		{
			String md5 = FileUtils.generateBufferedHash(launcher);
			String size = FileUtils.getFileSize(launcher);
			URL md5size = new URL(Settings.launcherMD5);
			BufferedReader in = new BufferedReader(new InputStreamReader(md5size.openStream()));
			String inputLine = in.readLine();
			in.close();
			if (inputLine.split(":")[0].equals(md5) && inputLine.split(":")[1].equals(size))
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
			return "NOCONNECTION";
		}
	}
	
	public static void openLauncher()
	{
		try
		{
			URL url = new File(DirUtils.getWorkingDirectory() + File.separator + "launcher" + File.separator + "launcher.jar").toURI().toURL();
			URLClassLoader classLoader = new URLClassLoader(new URL[] { url }, new LauncherUpdater().getClass().getClassLoader());
			Class<?> myMainClass = classLoader.loadClass("it.planetgeeks.mclauncher.Launcher");
			Method main = myMainClass.getMethod("main", String[].class);
			main.invoke(null, new Object[] { new String[] { "start" } });
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
