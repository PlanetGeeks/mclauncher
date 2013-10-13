package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LanguageUtils
{ 
	
	private static ArrayList<LanguagePack> langs = new ArrayList<LanguagePack>();
	private static LanguagePack currentPack;
	
    public static void loadLanguages()
    {
    	try
    	{
    		InputStream input = Launcher.resources.getInputStream("languages/languages.list");
    		if(input != null)
    		{
    			BufferedReader br = new BufferedReader(new InputStreamReader(input));
        		
        		String readed = br.readLine();
        		while(readed != null)
        		{
        			if(readed.contains("="))
        			{
        				LanguagePack pack = loadLangPack(readed.split("=")[0], readed.split("=")[1]);
        			    langs.add(pack);
        			}
        			readed = br.readLine();
        		}
        		br.close();
        		
        		for(int i = 0; i < langs.size(); i++)
        		{
        			LanguagePack current = langs.get(i);
        			if(current.isDefault)
        			{
        				currentPack = current;
        				break;
        			}
        		}
        		if(currentPack == null && langs.size() > 0)
        		{
        			currentPack = langs.get(0);
        		}
    		}
    		else
    		{
    			LauncherLogger.log(LauncherLogger.SEVERE, "Error on loading languages list! Does languages.list file exist in launcher.jar?");
    		}
    		input.close();   		
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    private static LanguagePack loadLangPack(String languageName, String fileName)
    {
    	try
    	{
    		InputStream input = Launcher.resources.getInputStream("languages/" + fileName);
            if(input != null)
            {
            	  BufferedReader br = new BufferedReader(new InputStreamReader(input));
            	  LanguagePack langPack = new LanguagePack(languageName);
            	  String readed = br.readLine();
            	  while(readed != null)
            	  {
            		  if(readed.startsWith("!"))
            		  {
            			  if(readed.contains("isDefault"))
            			  {
            				  langPack.isDefault = readed.split("=")[1].equals("true") ? true : false;
            			  }
            		  }
            		  else if(readed.contains("="))
            		  {
            			  langPack.translations.put(readed.split("=")[0], readed.split("=")[1]);
            		  }
            		  readed = br.readLine();
            	  }
            	  LauncherLogger.log(LauncherLogger.INFO, "Loaded languagePack '" + languageName + "'");
            	  return langPack;
            }
            else
            {
            	LauncherLogger.log(LauncherLogger.SEVERE, "Could not load '" + fileName + "' languagePack!");
            }
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
        
        return null;
    }
    
    public static String getTranslated(String key)
    {
    	if(currentPack != null && currentPack.translations != null && currentPack.translations.containsKey(key))
    	{
    		return currentPack.translations.get(key);
    	}
    	return key;
    }
    
    public static String getKey(String translated)
    {
    	
    	if(currentPack != null && currentPack.translations != null && currentPack.translations.containsValue(translated))
    	{
    		Iterator<String> it = currentPack.translations.keySet().iterator();
    		while(it.hasNext())
    		{
    			String key = it.next();
    			if(currentPack.translations.get(key).equals(translated))
    			{
    				return key;
    			}
    		}
    	}
    	return translated;
    }
}
