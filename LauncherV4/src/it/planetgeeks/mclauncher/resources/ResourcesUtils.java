package it.planetgeeks.mclauncher.resources;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;

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
	
	public ImageIcon getResource(String img)
	{
		return new ImageIcon(getClass().getResource(img));
	}

	public BufferedImage getResourceBuffered(String img)
	{
		ImageIcon icon = new ImageIcon(getClass().getResource(img));
		BufferedImage returned = new BufferedImage(icon.getImage().getWidth(null), icon.getImage().getHeight(null), 2);
		returned.getGraphics().drawImage(icon.getImage(), 0, 0, returned.getWidth(), returned.getHeight(), null);
		return returned;
	}


}
