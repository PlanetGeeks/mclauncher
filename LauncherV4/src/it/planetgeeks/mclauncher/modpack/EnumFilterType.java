package it.planetgeeks.mclauncher.modpack;

/**
 * @author PlanetGeeks
 *
 */

public enum EnumFilterType
{
	ALL("launcher.modpacks.filter.all"),
    MCVERSION("launcher.modpacks.filter.version"),
    PACKNAME("launcher.modpacks.filter.name"),
    PACKOWNER("launcher.modpacks.filter.owner"),
    HASSERVER("launcher.modpacks.filter.server"),
    HASMOD("launcher.modpacks.filter.mod"),
	DOWNLOADED("launcher.modpacks.filter.downloaded");
	
	public String str;
	
	EnumFilterType(String str)
	{
		this.str = str;
	}
	
}
