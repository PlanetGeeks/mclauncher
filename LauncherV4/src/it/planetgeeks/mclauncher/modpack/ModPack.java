package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.utils.DirUtils;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * @author PlanetGeeks
 *
 */

public class ModPack
{
	public String mcVersion;
	public String packName;
	public String packVersion;
	public String packOwner;
	public String packServerLink;
	public boolean directServerDownload;
	public ImageIcon packImage;
	public ArrayList<String> modList = new ArrayList<String>();
	public ArrayList<ModPackFile> setup = new ArrayList<ModPackFile>();
	public String setupIndex;
	public String setupLink;
	public String modpackLink;
	public String packBgLink;
	public String modsListLink;
	public String mainClass;
	public String tweakClass;

    public ModPack(String mcVersion, String packName, String packOwner, String packServerLink)
    {
    	this.mcVersion = mcVersion;
    	this.packName = packName;
    	this.packOwner = packOwner;
    	this.packServerLink = packServerLink;
    }
    
    public ModPack(String mcVersion, String packName, String packOwner)
    {
    	this.mcVersion = mcVersion;
    	this.packName = packName;
    	this.packOwner = packOwner;
    }
    
    public void setPackServerLink(String url)
    {
    	this.packServerLink = url;
    }
    
    public void setPackName(String packName)
    {
    	this.packName = packName;
    }
    
    public void setPackOwner(String packOwner)
    {
    	this.packOwner = packOwner;
    }
    
    public void setMcVersion(String mcVersion)
    {
    	this.mcVersion = mcVersion;
    }
    
    public void setSetupLink(String link)
    {
    	this.setupLink = link;
    }
    
    public boolean containMod(String str)
    {
    	for(int i = 0; i < modList.size(); i++)
    	{
    		if(modList.get(i).contains(str))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public void addMod(String str)
    {
    	modList.add(str);
    }
    
    public void setModList(ArrayList<String> mods)
    {
    	this.modList = mods;
    }
    
    public void setPackImage(ImageIcon img)
    {
    	this.packImage = img;
    }
    
    public void setSetup(ArrayList<String> link)
    {
    	for(int i = 0; i < link.size(); i++)
    	{
    		setup.add(new ModPackFile(link.get(i), this));
    	}
    }
    
    
    public void setSetupIndex(String link)
    {
    	setupIndex = link;
    }
    
    public void setServerLinkDirect(boolean direct)
    {
    	this.directServerDownload = direct;
    }
    
    public File getModPackDir()
    {
    	return new File(DirUtils.getWorkingDirectory() + File.separator + packName);
    }
    
    public void setModPackLink(String url)
    {
    	this.modpackLink = url;
    }
    
    public void setPackBgLink(String url)
    {
    	this.packBgLink = url;
    }
    
    public void setModsListLink(String url)
    {
    	this.modsListLink = url;
    }
    
    public void setMainClass(String mainClass)
    {
    	this.mainClass = mainClass;
    }
    
    public void setTweakClass(String tweakClass)
    {
    	this.tweakClass = tweakClass;
    }

    public void setPackVersion(String packVersion)
    {
    	this.packVersion = packVersion;
    }
}
