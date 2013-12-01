package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author PlanetGeeks
 *
 */

public class Profile
{
    public String username;
    public String password;
    public String profileName;
    public boolean rememberPsw;
    public String ram;
    public String sessionID;
    public BufferedImage skin;
    
    public Profile(String username, String password, String ram, String profileName, boolean rememberPsw)
    {
    	this.username = username;
    	this.password = password;
    	this.ram = ram;
    	this.profileName = profileName;
    	this.rememberPsw = rememberPsw;
    }
    
    public void setSessionID(String str)
    {
    	this.sessionID = str;	
    }
    
    public BufferedImage getSkin()
    {
    	if(skin != null)
    	{
    		return skin;
    	}
    	else
    	{
    		return loadSkin(Settings.skinIndex + username + ".png");    		
    	}
    }
    
    public BufferedImage loadSkin(String url)
    {
    	try
		{
			skin = ImageIO.read(new URL(url));
			
			return skin;
		}
		catch (Exception e)
		{
		}
		
    	skin = Launcher.getResources().getResourceBuffered("char.png");
        
    	return skin;
    }
}
