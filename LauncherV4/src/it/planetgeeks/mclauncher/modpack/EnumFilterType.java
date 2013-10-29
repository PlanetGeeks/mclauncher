package it.planetgeeks.mclauncher.modpack;

public enum EnumFilterType
{
	ALL("launcher.modpacks.filter.all"),
    MCVERSION("launcher.modpacks.filter.version"),
    PACKNAME("launcher.modpacks.filter.name"),
    PACKOWNER("launcher.modpacks.filter.owner"),
    HASSERVER("launcher.modpacks.filter.server"),
    HASMOD("launcher.modpacks.filter.mod");
	
	public String str;
	
	EnumFilterType(String str)
	{
		this.str = str;
	}
	
}
