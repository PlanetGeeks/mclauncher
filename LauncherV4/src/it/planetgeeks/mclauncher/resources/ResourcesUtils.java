package it.planetgeeks.mclauncher.resources;

import java.io.InputStream;

public class ResourcesUtils
{
	public InputStream getInputStream(String str)
	{
	    InputStream input = getClass().getResourceAsStream(str);
		return input;
	}
}
