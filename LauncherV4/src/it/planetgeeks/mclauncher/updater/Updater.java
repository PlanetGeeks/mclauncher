package it.planetgeeks.mclauncher.updater;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.settings.Settings;
import it.planetgeeks.mclauncher.utils.DirUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
class UpdateThread implements Runnable
{

	@Override
	public void run()
	{
		Updater.startUpdate();
	}
}

public class Updater
{
	public static void startUpdateThread()
	{
		Thread thread = new Thread(new UpdateThread());
		thread.start();
	}

	protected static void startUpdate()
	{
		if (downloadLauncher())
		{
			LauncherUpdater.openLauncher();
		}
		else
		{
	        Launcher.main(new String[] { "start" });
		}
	}

	private static boolean downloadLauncher()
	{

		File launcher = new File(DirUtils.getWorkingDirectory() + File.separator + "launcher" + File.separator + "launcher.jar");
		if (launcher.exists())
		{
			launcher.delete();
		}

		try
		{
			URL url = new URL(Settings.launcherLink);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			float totalDataRead = 0;
			BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(launcher);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int i = 0;
			while ((i = in.read(data, 0, 1024)) >= 0)
			{
				totalDataRead = totalDataRead + i;
				bout.write(data, 0, i);
			}
			bout.close();
			fos.close();
			in.close();
			connection.disconnect();
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

}
