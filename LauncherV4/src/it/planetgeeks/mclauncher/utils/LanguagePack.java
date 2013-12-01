package it.planetgeeks.mclauncher.utils;

import java.util.HashMap;

/**
 * @author PlanetGeeks
 *
 */

public class LanguagePack
{
    public String packName;
    public HashMap<String, String> translations = new HashMap<String, String>();
    public boolean isDefault = false;
    
    public LanguagePack(String packName)
    {
    	this.packName = packName;
    }
}
