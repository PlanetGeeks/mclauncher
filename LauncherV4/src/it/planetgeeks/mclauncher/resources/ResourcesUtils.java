package it.planetgeeks.mclauncher.resources;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author PlanetGeeks
 *
 */

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
	
	public BufferedImage getScaledImage(BufferedImage icon, float scale, int type)
	{
		AffineTransform at = new AffineTransform();

		at.scale(scale, scale);

		AffineTransformOp op = new AffineTransformOp(at, type);
		icon = op.filter(icon, null);

		return icon;
	}

	public ImageIcon getCroppedResorce(String img, int startX, int startY, int width, int height)
	{
		try
		{
			BufferedImage src;
			src = ImageIO.read(getClass().getResource(img));
			BufferedImage dest = src.getSubimage(startX, startY, width, height);
			return new ImageIcon(dest);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public BufferedImage getFromIcon(ImageIcon img)
	{
		BufferedImage ret = new BufferedImage(img.getIconWidth(), img.getIconHeight(), 2);
		
		Graphics2D g = ret.createGraphics();
		
		g.drawImage(img.getImage(), 0, 0, ret.getWidth(), ret.getHeight(), null);
		
		g.dispose();
		
		return ret;
	}
	
	public BufferedImage getReflectedResource(BufferedImage icon, double scaleX, double scaleY)
	{
		AffineTransform at = new AffineTransform();

		at.translate(icon.getWidth() - (icon.getWidth() + scaleX * icon.getWidth()), 0);

		at.scale(scaleX, scaleY);

		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		icon = op.filter(icon, null);

		return icon;
	}
	
	public ImageIcon getMergedResource(ImageIcon image0, ImageIcon image1)
	{
		if(image0.getIconWidth() == image1.getIconWidth() && image0.getIconHeight() == image1.getIconHeight())
	    {
		    BufferedImage dest = new BufferedImage(image0.getIconWidth(), image0.getIconHeight(), 2);
		    dest.getGraphics().drawImage(image0.getImage(), 0, 0, image0.getIconWidth(), image0.getIconWidth(), null);
		    dest.getGraphics().drawImage(image1.getImage(), 0, 0, image1.getIconWidth(), image1.getIconWidth(), null);
	        return new ImageIcon(dest);
	    }
		return image0;
	}
}
