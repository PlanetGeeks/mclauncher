package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.DirUtils.OS;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class GameLauncher
{
	public static void launchGame()
	{
		try
		{
			String pathToJar;
			File jar = new File(GameLauncher.class.getProtectionDomain().getCodeSource().getLocation().getFile());
			pathToJar = jar.getAbsolutePath();
			ArrayList<String> params = new ArrayList<String>();
			OS os = DirUtils.getPlatform();
			if (os == OS.windows)
			{
				params.add("javaw");
			}
			else if (os == OS.macos)
			{
				params.add("java");
				params.add("-Xdock:name=" + Settings.launcherName);
			}
			else
			{
				params.add("java");
			}
			params.add("-Xmx" + MemoryUtils.getMem(ProfilesUtils.getSelectedProfile().ram).size + "m");
			if(Launcher.getOptions() != null)
			{
				String addParams[] = Launcher.getOptions().getAddParams();
				if(addParams != null)
				{
					for(int i = 0; i < addParams.length; i++)
					{
						params.add(addParams[i]);
					}
				}
			}
			params.add("-classpath");
			params.add(pathToJar);
			params.add(GameLauncher.class.getName());
			params.add(ModPackUtils.selected.packName);
			params.add("--username");
			params.add(ProfilesUtils.getSelectedProfile().username);
			params.add("--session");
			params.add(ProfilesUtils.getSelectedProfile().sessionID != null ? ProfilesUtils.getSelectedProfile().sessionID : "0");
			params.add("--version");
			params.add(ModPackUtils.selected.mcVersion);
			params.add("--gameDir");
			params.add((new File(ModPackUtils.selected.getModPackDir() + File.separator + "files")).getAbsolutePath());
			
			ProcessBuilder pb = new ProcessBuilder();
			pb.command(params);
			final Process process = pb.start();
			if (process == null)
				throw new Exception("!");
			Launcher.hideOrShowWindows(true);
			loadConsoleListeners(process);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void onGameClosed()
	{
		if (LauncherProperties.getProperty("openLauncherAfterExit").equals("true"))
		{
			Launcher.hideOrShowWindows(false);
		}
		else
		{
			Launcher.closeLauncher();
		}
	}

	private static void loadConsoleListeners(final Process process)
	{
		Runnable error = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					String errorLine;
					BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					while ((errorLine = error.readLine()) != null)
					{
						System.out.println("[MINECRAFT]" + errorLine);
					}
					error.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		};
		Runnable input = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					String inputLine;
					BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((inputLine = input.readLine()) != null)
					{
						System.out.println("[MINECRAFT]" + inputLine);
					}
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();

				}
				onGameClosed();
			}
		};
		Thread tr0 = new Thread(error);
		tr0.start();
		Thread tr1 = new Thread(input);
		tr1.start();
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

	public static void main(String[] args)
	{
		if(args.length > 0)
		{
			File file = new File(DirUtils.getWorkingDirectory() + File.separator + args[0] + File.separator + "files");

			System.setProperty("org.lwjgl.librarypath", new File(file + File.separator + "natives-win").getAbsolutePath());

			File lib = new File(file + File.separator + "libraries");

			ArrayList<String> libs = getJars(lib);

			File bin = new File(file + File.separator + "bin");

			ArrayList<String> bins = getJars(bin);

			for (int i = 0; i < bins.size(); i++)
			{
				libs.add(bins.get(i));
			}

			URL[] urls = new URL[libs.size()];
			for (int i = 0; i < libs.size(); i++)
			{
				File f = new File(libs.get(i));
				try
				{
					urls[i] = f.toURI().toURL();
				}
				catch (MalformedURLException e)
				{
					e.printStackTrace();
				}
			}

			@SuppressWarnings("resource")
			URLClassLoader urlcl = new URLClassLoader(urls);
			Class<?> classS;
			try
			{
				classS = urlcl.loadClass("net.minecraft.client.main.Main");
				Method method = classS.getMethod("main", String[].class);

				String[] par = new String[args.length - 1];
				for(int i = 1; i < args.length; i++)
				{
					par[i - 1] = args[i];
				}
				method.invoke(null, (Object) par);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Parameters required!");
		}
		
	}

}
