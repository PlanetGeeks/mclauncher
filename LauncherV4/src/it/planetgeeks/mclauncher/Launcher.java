package it.planetgeeks.mclauncher;

import java.awt.Color;

import it.planetgeeks.mclauncher.frames.ConsoleFrame;
import it.planetgeeks.mclauncher.frames.InfoFrame;
import it.planetgeeks.mclauncher.frames.LauncherFrame;
import it.planetgeeks.mclauncher.frames.MemoryFrame;
import it.planetgeeks.mclauncher.frames.OptionsFrame;
import it.planetgeeks.mclauncher.frames.ProfileFrame;
import it.planetgeeks.mclauncher.resources.ResourcesUtils;
import it.planetgeeks.mclauncher.updater.LauncherUpdater;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher
{
	private static LauncherFrame launcherFrame;
	private static ProfileFrame profileFrame;
	private static MemoryFrame memoryFrame;
	private static InfoFrame infoFrame;
	public static ConsoleFrame consoleFrame;
	public static OptionsFrame optionsFrame;
	public static ResourcesUtils resources = new ResourcesUtils();

	public static void main(String[] args)
	{
		if (args.length > 0 && args[0].equals("start"))
		{
			loadLookAndFeel();
			consoleFrame = new ConsoleFrame();
			LauncherLogger.loadLogger();
			printLauncherInfo();
			LauncherProperties.loadProperties();
			dropUpdaterErrors(args);
			LanguageUtils.loadLanguages();
			consoleFrame.updateComponents();
			ProfilesUtils.loadProfiles();
			MemoryUtils.loadMemories();
			launcherFrame = new LauncherFrame();
			launcherFrame.setVisible(true);
		}
		else
		{
			LauncherUpdater.startCheck();
		}
	}

	private static void loadLookAndFeel()
	{
		try
		{
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("nimbusBlueGrey", (new Color(Settings.buttonsBackground)));
					UIManager.put("nimbusBase", (new Color(Settings.colorBase)));
					UIManager.put("control", (new Color(Settings.control)));
					UIManager.put("nimbusLightBackground", (new Color(Settings.lightBackground)));
					UIManager.put("nimbusFocus", (new Color(Settings.focus)));
					UIManager.put("text", (new Color(Settings.textDefault)));
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public static void openProfileEditor(Profile profile)
	{
		profileFrame = new ProfileFrame(profile);
		profileFrame.setVisible(true);
	}

	public static void openMemoryEditor(int parent, Object extra)
	{
		memoryFrame = new MemoryFrame(parent, extra);
		memoryFrame.setVisible(true);
	}
	
	public static void openOrCloseInfoFrame()
	{
		if(isInfoOpened())
		{
			infoFrame.setVisible(false);
		}
		else
		{
			infoFrame = new InfoFrame();
			infoFrame.setVisible(true);
		}
	}

	public static void openOrCloseConsole()
	{
		if (isConsoleOpened())
		{
			consoleFrame.setVisible(false);
		}
		else
		{
			consoleFrame.setVisible(true);
		}
	}
	
	public static void openOrCloseOptionsFrame()
	{
		if(isAdvOptionsOpened())
		{
			optionsFrame.setVisible(false);
		}
		else
		{
			optionsFrame = new OptionsFrame();
			optionsFrame.setVisible(true);
		}
	}

	public static boolean isConsoleOpened()
	{
		return consoleFrame != null ? (consoleFrame.isVisible() ? true : false) : false;
	}
	
	public static boolean isAdvOptionsOpened()
	{
		return optionsFrame != null ? (optionsFrame.isVisible() ? true : false) : false;
	}
	
	public static boolean isInfoOpened()
	{
		return infoFrame != null ? (infoFrame.isVisible() ? true : false) : false;
	}

	public static LauncherFrame getLauncherFrame()
	{
		return launcherFrame;
	}

	private static void dropUpdaterErrors(String[] args)
	{
		if (args.length > 1)
		{
			for (int i = 1; i < args.length; i++)
			{
				if (args[i].equals("ERROR0"))
				{
					LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading launcher check file!");
				}
				else if (args[i].equals("ERROR1"))
				{
					LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading launcher!");
				}
			}
		}
	}

	private static void printLauncherInfo()
	{
		LauncherLogger.log(LauncherLogger.INFO, Settings.launcherName + " | " + "Version " + Settings.launcherVersion + " | " + "Owned by " + Settings.launcherOwner);
	}

	public static void closeLauncher()
	{
        System.exit(0);
	}
}
