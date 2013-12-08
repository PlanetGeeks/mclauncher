package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.DirUtils.OS;
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
	private static boolean isNewFormat(String version)
	{
	    String withoutDot = version.replaceAll(".", "");
	    if(withoutDot.length() > 3)
	    	withoutDot = withoutDot.substring(0,3);
	    if(withoutDot.length() == 1)
	    	return true;
	    if(withoutDot.length() == 2)
	    	withoutDot += "0";
	    try
	    {
	    	int i = Integer.valueOf(withoutDot);
	    	if(i < 160)
	    	{
	    		return false;
	    	}
	    }
	    catch(NumberFormatException e)  {}
	  
	    return true;
	}
	
	public static void launchGame()
	{
		OS op = DirUtils.getPlatform();
		
		String os = op == OS.windows ? "win" : (op == OS.linux ? "linux" : (op == OS.macos ? "mac" : "UNKNOW")); 

		ModPack modpack = ModPackUtils.selected;

		File gameDirectory = new File(modpack.getModPackDir() + File.separator + "files");

		File nativeDir = new File(gameDirectory, "natives-" + os);

		JavaProcessLauncher processLauncher = new JavaProcessLauncher(DirUtils.getJavaDir(), new String[0]);

		processLauncher.directory(gameDirectory);
		
		if(!isNewFormat(modpack.mcVersion))
		{
			if (DirUtils.getPlatform() == DirUtils.OS.macos)
			{
				processLauncher.addCommands(new String[] { "-Xdock:name=" + modpack.packName });			
			}
			
			String defaultArgument = "-Xmx" + MemoryUtils.getMem(ProfilesUtils.getSelectedProfile().ram).size + "m";
			processLauncher.addSplitCommands(defaultArgument);

			processLauncher.addCommands(new String[] { "-Djava.library.path=" + nativeDir.getAbsolutePath() });
			processLauncher.addCommands(new String[] { "-cp", constructClassPath(new File(gameDirectory, "bin"), null) });
			processLauncher.addCommands(new String[] { modpack.mainClass });
		
			Profile profile = ProfilesUtils.getSelectedProfile();
			
			processLauncher.addSplitCommands((profile.minecraftName != null ? profile.minecraftName : profile.username) + " " + (profile.sessionID != null ? profile.sessionID.trim().split(":")[1] : "noSessionID"));
		}
		else
		{
			File assetsDirectory = new File(gameDirectory, "assets");

			if (DirUtils.getPlatform() == DirUtils.OS.macos)
			{
				processLauncher.addCommands(new String[] { "-Xdock:icon=" + new File(assetsDirectory, "icons/minecraft.icns").getAbsolutePath(), "-Xdock:name=" + modpack.packName });
			}

			//processLauncher.addCommands(new String[] { "-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump" });
			 
			String defaultArgument = "-Xmx" + MemoryUtils.getMem(ProfilesUtils.getSelectedProfile().ram).size + "m";
			processLauncher.addSplitCommands(defaultArgument);

			processLauncher.addCommands(new String[] { "-Djava.library.path=" + nativeDir.getAbsolutePath() });
			processLauncher.addCommands(new String[] { "-cp", constructClassPath(new File(gameDirectory, "bin"),new File(gameDirectory, "libraries")) });
			processLauncher.addCommands(new String[] { modpack.mainClass });

			Profile profile = ProfilesUtils.getSelectedProfile();
				
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add("--username");
			parameters.add(profile.minecraftName != null ? profile.minecraftName : profile.username);
			parameters.add("--session");
			parameters.add(profile.sessionID != null ? profile.sessionID.trim() : "noSessionID");
			parameters.add("--version");
			parameters.add(modpack.mcVersion);
			parameters.add("--gameDir");
			parameters.add("\"" + gameDirectory.getAbsolutePath() + "\"");
			parameters.add("--assetsDir");
			parameters.add("\"" + assetsDirectory.getAbsolutePath() + "\"");
			if(!modpack.tweakClass.equals("null"))
			{
				parameters.add("--tweakClass");
				parameters.add(modpack.tweakClass);
			}
				
			String[] strParams = new String[parameters.size()];
			
			for(int i = 0; i < parameters.size(); i++)
			{
				strParams[i] = parameters.get(i);
			}
			
			processLauncher.addCommands(strParams);
		}
		
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

            final JavaProcess p =	processLauncher.start();
           
        	Launcher.hideOrShowWindows(true);
            p.setExitRunnable(new JavaProcessRunnable()
            {
				@Override
				public void onJavaProcessEnded(JavaProcess paramJavaProcess)
				{
				//	 p.getRawProcess().exitValue();
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
		
		ArrayList<String> libsPaths = libsDir != null ? getJars(libsDir) : new ArrayList<String>();
		
		ArrayList<String> binPaths = getJars(binDirs);
		
		for(int i = 0; i < binPaths.size(); i++)
		{
			libsPaths.add(binPaths.get(i));
		}
		
		StringBuilder result = new StringBuilder();

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
