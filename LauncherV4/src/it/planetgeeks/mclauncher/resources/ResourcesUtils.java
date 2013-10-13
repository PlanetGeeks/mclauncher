package it.planetgeeks.mclauncher.resources;

import java.io.InputStream;
import java.net.URL;

public class ResourcesUtils
{
	public InputStream getInputStream(String str)
	{
	    InputStream input = getClass().getResourceAsStream(str);
		return input;
	}
	
	public URL getResourceUrl(String str)
	{
		return getClass().getResource(str);
	}
}
