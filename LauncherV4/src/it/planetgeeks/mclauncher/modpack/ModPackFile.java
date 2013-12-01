package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.DirUtils.OS;

import java.io.File;
import java.util.regex.Pattern;

/**
 * @author PlanetGeeks
 *
 */

public class ModPackFile
{
    private String downloadPath;
    private String savePath;
    private String md5;
    private String size;
    private boolean check;
    private OS os;
    private ModPack parent;
    
    public ModPackFile(String setup, ModPack parent)
    {
    	 String splitted[] = setup.split(":");
    	 
    	 downloadPath = splitted.length > 0 ? splitted[0] : "";
    	 savePath = splitted.length > 1 ? splitted[1] : "";
    	 md5 = splitted.length > 2 ? splitted[2] : "";
    	 size = splitted.length > 3 ? splitted[3] : "";
    	 os = DirUtils.getPlatform(splitted.length > 4 ? splitted[4] : "");
    	 check = splitted.length > 5 ? (splitted[5].contains("check")) : true;
    	 this.parent = parent;
    }
    
    public String getDownloadURL(String setupIndex)
    {
    	return setupIndex + downloadPath;
    }
    
    public String getMD5()
    {
    	return md5;
    }
    
    public String getSize()
    {
    	return size;
    }
    
    public OS getOs()
    {
    	return os;
    }
    
    public boolean check()
    {
    	return check;
    }
    
    public File getSaveFile()
    {
    	String[] str = savePath.split(Pattern.quote("\\"));
    	
    	String path = "";
    	
    	for (int i = 0; i < str.length; i++)
    	{
    	    path += File.separator + str[i];	
    	}
    	
    	return new File(parent.getModPackDir() + path);
    }
}
