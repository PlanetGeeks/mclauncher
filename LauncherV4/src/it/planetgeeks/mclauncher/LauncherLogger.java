package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.utils.DirUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public enum LauncherLogger
{
	
	INFO("[INFO]"),
	GRAVE("[GRAVE]"),
	SEVERE("[SEVERE]");
	
	String type;
	
	LauncherLogger(String type)
	{
	    this.type = type;	
	}
	
	public static File logFolder = new File(DirUtils.getLauncherDirectory() + File.separator + "logs");
	
	private static String getDate(String regex)
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(regex);
		return sdf.format(cal.getTime());
	}
	
    public static void log(LauncherLogger log, String str)
    {
    	System.out.println("[" + getDate("HH:mm:ss") + "]" + log.type + " : " + str);
    }
    
    public static void saveLog(File toSave)
    {
    	//File toSave = new File(getLogFolder() + File.separator + "log-" + getDate("yyyy.MM.dd.HH.mm.ss") + ".log");
    	try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(toSave));
			
			
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
        
    }
    	
    
    public static File getLogFolder()
    {
    	if(!logFolder.exists())
    	{
    		logFolder.mkdirs();
    	}
    	return logFolder;
    }
}
