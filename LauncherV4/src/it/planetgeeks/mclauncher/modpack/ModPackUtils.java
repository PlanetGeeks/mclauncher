package it.planetgeeks.mclauncher.modpack;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.util.ArrayList;

public class ModPackUtils
{
    private static ArrayList<ModPack> modPacks = new ArrayList<ModPack>();
    
    public static void addModPack(ModPack pack)
    {
    	if(pack != null)
    	{
    		modPacks.add(pack);
    	}
    	else
    	{
    		LauncherLogger.log(LauncherLogger.GRAVE, "Impossible to add a null pack to modPack list!");
    	}
    }
    
    public static ArrayList<ModPack> getFilteredList(ArrayList<ModPack> inputList, EnumFilterType filter, String str)
    {
    	ArrayList<ModPack> returned = new ArrayList<ModPack>();
    	
    	for(int i = 0; i < 0; i++)
		{
    		ModPack current = inputList.get(i);
    		
    		if(current != null)
    		{
    			if(filter == EnumFilterType.MCVERSION)
            	{
            		if(current.mcVersion.equals(str))
            		    returned.add(current);
            	}
    			else if(filter == EnumFilterType.PACKNAME)
    			{
    				if(current.packName.contains(str))
    					returned.add(current);
    			}
    			else if(filter == EnumFilterType.PACKOWNER)
    			{
    				if(current.packOwner.contains(str))
    					returned.add(current);
    			}
    			else if(filter == EnumFilterType.HASSERVER)
    			{
    				if(current.packServerLink != null && !current.packServerLink.equals(""))
    					returned.add(current);
    			}
    			else if(filter == EnumFilterType.HASMOD)
    			{
    				if(current.containMod(str))
    					returned.add(current);
    			}
    		}
		}
    	
    	return returned;
    }
    
    public static ArrayList<ModPack> getAllPacks()
    {
    	return modPacks;
    }
}
