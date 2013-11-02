package it.planetgeeks.mclauncher.modpack;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ModPack
{
	public String mcVersion;
	public String packName;
	public String packOwner;
	public String packServerLink;
	public boolean directServerDownload;
	public ImageIcon packImage;
	public ArrayList<String> modList = new ArrayList<String>();
	public ArrayList<ModPackFile> setup = new ArrayList<ModPackFile>();
	public String setupIndex;
	public boolean useForge;

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
    
    public ModPack clone()
    {	
    	ModPack cloned = new ModPack(this.mcVersion, this.packName, this.packOwner, this.packServerLink);
    	cloned.setModList(this.modList);
    	cloned.setPackImage(this.packImage);
    	cloned.setup = this.setup;
    	cloned.setSetupIndex(this.setupIndex);
    	cloned.setUseForge(this.useForge);
    	cloned.setServerLinkDirect(this.directServerDownload);
    	
    	return cloned;
    }
    
    public void setSetup(ArrayList<String> link)
    {
    	for(int i = 0; i < link.size(); i++)
    	{
    		setup.add(new ModPackFile(link.get(i)));
    	}
    }
    
    public void setSetupIndex(String link)
    {
    	setupIndex = link;
    }
    
    public void setUseForge(boolean useForge)
    {
    	this.useForge = useForge;
    }
    
    public void setServerLinkDirect(boolean direct)
    {
    	this.directServerDownload = direct;
    }
    
}
