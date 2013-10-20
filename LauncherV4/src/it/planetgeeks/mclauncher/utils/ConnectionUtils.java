package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ConnectionUtils
{
    public static void openWebPage(String url)
    {
    	try
		{
			Desktop.getDesktop().browse(new URI(url));
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on opening webpage '" + url + "'!");
			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on opening webpage '" + url + "'! Failed to read url sintax!");
			e.printStackTrace();
		}
    }
}
