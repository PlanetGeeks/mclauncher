package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DesktopUtils
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
		}
		catch (URISyntaxException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on opening webpage '" + url + "'! Failed to read url sintax!");
		}
    }
    
    public static boolean checkLink(String url)
    {
        try
		{
			new URL(url);
			return true;
		}
		catch (MalformedURLException e)
		{
			return false;
		}
    }
    
    public static void openFolder(File folder)
    {
    	try
		{
			Desktop.getDesktop().open(folder);
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on opening folder!");
			e.printStackTrace();
		}
    }
}
