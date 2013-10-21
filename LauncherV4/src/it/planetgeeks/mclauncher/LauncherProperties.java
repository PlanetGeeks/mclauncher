package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.utils.DirUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LauncherProperties
{
    private static HashMap<String, String> properties = new HashMap<String,String>();
    private static File propertyFile = new File(DirUtils.getLauncherDirectory().getAbsolutePath() + File.separator + "launcher.properties");
    
    public static void loadProperties()
    {
    	if(propertyFile.exists())
    	{
    		readPropertyFile();
    		
    		registerNecessaryKeys();
    	}
    	else
    	{
    		LauncherLogger.log(LauncherLogger.INFO, "No launcher property loaded! Creating defaults!");
    		
    		registerNecessaryKeys();
    	}
    }
    
    private static void registerNecessaryKeys()
    {
    	ArrayList<String[]> necessary = new ArrayList<String[]>();
		necessary.add( new String[] { "language", "" });
		necessary.add( new String[] { "automaticLogs", "5" });
		necessary.add( new String[] { "openLauncherAfterExit", "false" });
	
        Set<String> keys = properties.keySet();
        for(String key : keys)
        {
        	for(int i = 0; i < necessary.size(); i++)
        	{
        		if(necessary.get(i)[0].equals(key))
        		{
        			necessary.remove(i);
        		}
        	}
        }
        
        for(int i = 0; i < necessary.size(); i++)
        {
        	registerProperty(necessary.get(i)[0], necessary.get(i)[1]);
        }
        
        writePropertyFile();
    }
    
    private static void readPropertyFile()
    {
    	BufferedReader br;
    	try
		{
			br = new BufferedReader(new FileReader(propertyFile));
			String readed = br.readLine();
			int loaded = 0;
			while(readed != null)
			{
				if(readed.contains("="))
				{
					String[] splitted = readed.split("=");
					String key = splitted[0];
		            String value = "";
		            for(int i = 1; i < splitted.length; i++)
		            {
		            	value += splitted[i];
		            }
		            registerProperty(key, value);
		            loaded++;
				}	
				readed = br.readLine();
			}
			br.close();
			LauncherLogger.log(LauncherLogger.INFO, "Loaded " + loaded + " launcher properties!");
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on loading launcher properties!");
			e.printStackTrace();
		}
    }    
    
    private static void writePropertyFile()
    {
    	BufferedWriter bw;
		try
		{
			bw = new BufferedWriter(new FileWriter(propertyFile));
			Set<String> keys = properties.keySet();
			boolean first = true;
			for(String key : keys)
			{
				if(!first)
				{
					bw.newLine();
				}
				else
				{
					first = false;
				}	
				bw.write(key + "=" + properties.get(key));
			}
			bw.close();
			
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on writing launcher properties");
			e.printStackTrace();
		}
    }
    
    public static String getProperty(String key)
    {
    	if(!properties.containsKey(key))
    	{
    		return null;
    	}
    	
    	return properties.get(key);
    }
    
    private static void registerProperty(String key, String value)
    {
    	properties.put(key, value);
    }
    
    @SuppressWarnings("unused")
	private static void removeProperty(String str, boolean key)
    {
    	if(key)
    	{
    		if(properties.containsKey(str))
    			properties.remove(str);
    	}
    	else
    	{
    		Set<String> keys = properties.keySet();
    		for(String currentKey : keys)
    		{
    			if(properties.get(currentKey).equals(str))
    				properties.remove(currentKey);
    		}
    	}
    }
    
    public static void modifyProperty(String key, String value)
    {
    	if(properties.containsKey(key))
    	{
    		properties.put(key, value);
    	}
    	writePropertyFile();
    }
    
}
