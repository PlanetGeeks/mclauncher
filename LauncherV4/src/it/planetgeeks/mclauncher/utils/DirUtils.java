package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.Settings;

import java.io.File;

/**
 * @author PlanetGeeks
 *
 */

public class DirUtils
{
	public static File workDir = null;

	public static File getWorkingDirectory()
	{
		if (workDir == null)
		{
			workDir = getWorkingDirectory(Settings.gameDir);
		}

		return workDir;
	}

	public static File getLauncherDirectory()
	{
		File launcherDir = new File(getWorkingDirectory() + File.separator + "launcher");
		if (!launcherDir.exists())
		{
			launcherDir.mkdirs();
		}
		return launcherDir;
	}

	public static File getWorkingDirectory(String applicationName)
	{
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (getPlatform())
		{
			case solaris:
			case linux:
				workingDirectory = new File(userHome, '.' + applicationName + '/');
				break;
			case windows:
				String applicationData = System.getenv("APPDATA");
				if (applicationData != null)
					workingDirectory = new File(applicationData, "." + applicationName + '/');
				else
					workingDirectory = new File(userHome, '.' + applicationName + '/');
				break;
			case macos:
				workingDirectory = new File(userHome, "Library/Application Support/" + applicationName);
				break;
			default:
				workingDirectory = new File(userHome, applicationName + '/');
		}
		if (workingDirectory.mkdirs())
			;
		return workingDirectory;
	}

	public static OS getPlatform()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return OS.windows;
		if (osName.contains("mac"))
			return OS.macos;
		if (osName.contains("solaris"))
			return OS.solaris;
		if (osName.contains("sunos"))
			return OS.solaris;
		if (osName.contains("linux"))
			return OS.linux;
		if (osName.contains("unix"))
			return OS.linux;
		return OS.unknown;
	}
	
	public static OS getPlatform(String str)
	{
		if(str.toLowerCase().contains("win"))
			return OS.windows;
		if(str.toLowerCase().contains("lin"))
			return OS.linux;
		if(str.toLowerCase().contains("mac"))
			return OS.macos;
		
		return OS.unknown;
	}

	public static enum OS
	{
		linux, solaris, windows, macos, unknown;
	}
}