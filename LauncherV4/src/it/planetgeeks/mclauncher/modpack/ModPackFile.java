package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.utils.DirUtils;

import java.io.File;

public class ModPackFile
{
    private String downloadPath;
    private String savePath;
    private String md5;
    private String size;
    
    public ModPackFile(String setup)
    {
    	 String splitted[] = setup.split(":");
    	 
    	 downloadPath = splitted.length > 0 ? splitted[0] : "";
    	 savePath = splitted.length > 1 ? splitted[1] : "";
    	 md5 = splitted.length > 2 ? splitted[2] : "";
    	 size = splitted.length > 3 ? splitted[3] : "";
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
    
    public File getSaveFile()
    {
    	String[] str = savePath.split("\\");
    	
    	String path = "";
    	
    	for (int i = 0; i < str.length; i++)
    	{
    	    path += File.separator + str[i];	
    	}
    	
    	return new File(DirUtils.getWorkingDirectory() + path);
    }
}
