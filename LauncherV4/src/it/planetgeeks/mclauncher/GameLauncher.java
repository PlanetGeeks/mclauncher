package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;
import it.planetgeeks.mclauncher.utils.process.JavaProcess;
import it.planetgeeks.mclauncher.utils.process.JavaProcessLauncher;
import it.planetgeeks.mclauncher.utils.process.JavaProcessRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher
{
	public static void launchGame()
	{
		String os = "win";

		if (DirUtils.getPlatform() == DirUtils.OS.windows)
			os = "win";
		if (DirUtils.getPlatform() == DirUtils.OS.linux)
			os = "linux";
		if (DirUtils.getPlatform() == DirUtils.OS.macos)
			os = "mac";

		ModPack modpack = ModPackUtils.selected;

		File gameDirectory = new File(modpack.getModPackDir() + File.separator + "files");

		File nativeDir = new File(gameDirectory, "natives-" + os);

		JavaProcessLauncher processLauncher = new JavaProcessLauncher(DirUtils.getJavaDir(), new String[0]);

		processLauncher.directory(gameDirectory);

		File assetsDirectory = new File(gameDirectory, "assets");

		if (DirUtils.getPlatform() == DirUtils.OS.macos)
		{
			processLauncher.addCommands(new String[] { "-Xdock:icon=" + new File(assetsDirectory, "icons/minecraft.icns").getAbsolutePath(), "-Xdock:name=" + modpack.packName });
		}

		String defaultArgument = "-Xmx" + MemoryUtils.getMem(ProfilesUtils.getSelectedProfile().ram).size + "m";
		processLauncher.addSplitCommands(defaultArgument);

		processLauncher.addCommands(new String[] { "-Djava.library.path=" + nativeDir.getAbsolutePath() });
		processLauncher.addCommands(new String[] { "-cp", constructClassPath(new File(gameDirectory, "bin"),new File(gameDirectory, "libraries")) });
		processLauncher.addCommands(new String[] { modpack.mainClass });

		Profile profile = ProfilesUtils.getSelectedProfile();
			
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("--username " + (profile.minecraftName != null ? profile.minecraftName : profile.username));
		parameters.add(" --session " +  (profile.sessionID != null ? profile.sessionID.trim() : "noSessionID"));
		parameters.add(" --version " + modpack.mcVersion);
		parameters.add(" --gameDir " + gameDirectory.getAbsolutePath());
		parameters.add(" --assetsDir " + assetsDirectory.getAbsolutePath());
		if(!modpack.tweakClass.equals("null"))
			parameters.add(" --tweakClass " + modpack.tweakClass);
		
		String strParams = "";
		
		for(int i = 0; i < parameters.size(); i++)
		{
			strParams += parameters.get(i);
		}
		
		System.out.println(strParams);
		
		processLauncher.addSplitCommands(strParams);

		try
		{
			List<String> parts = processLauncher.getFullCommands();
			StringBuilder full = new StringBuilder();
			boolean first = true;

			for (String part : parts)
			{
				if (!first)
					full.append(" ");
				full.append(part);
				first = false;
			}

            JavaProcess p =	processLauncher.start();
        	Launcher.hideOrShowWindows(true);
            p.setExitRunnable(new JavaProcessRunnable()
            {
				@Override
				public void onJavaProcessEnded(JavaProcess paramJavaProcess)
				{
					if(LauncherProperties.getProperty("openLauncherAfterExit").equals("true"))
					{
						Launcher.hideOrShowWindows(false);
					}
					else
					{
						Launcher.closeLauncher();
					}
				}
            });
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
	}

	private static ArrayList<String> getJars(File dir)
	{
		File[] list = dir.listFiles();
		ArrayList<String> paths = new ArrayList<String>();
		for (File f : list)
		{
			if (f.isDirectory())
			{
				ArrayList<String> ret = getJars(f);
				for (String r : ret)
				{
					paths.add(r);
				}
			}
			else
			{
				if (f.getName().endsWith(".jar") || f.getName().endsWith(".zip"))
				{
					paths.add(f.getAbsolutePath());
				}
			}
		}

		return paths;
	}

	private static String constructClassPath(File binDirs, File libsDir)
	{
		ArrayList<String> libsPaths = getJars(libsDir);
		StringBuilder result = new StringBuilder();

		ArrayList<String> binPaths = getJars(binDirs);
		
		for(int i = 0; i < binPaths.size(); i++)
		{
			libsPaths.add(binPaths.get(i));
		}

		String separator = System.getProperty("path.separator");

		for (String s : libsPaths)
		{
			File file = new File(s);
			if (!file.isFile())
				throw new RuntimeException("Classpath file not found: " + file);
			if (result.length() > 0)
				result.append(separator);
			result.append(file.getAbsolutePath());
		}

		return result.toString();
	}

}
