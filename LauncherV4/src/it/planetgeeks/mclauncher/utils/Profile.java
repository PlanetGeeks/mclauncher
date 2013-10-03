package it.planetgeeks.mclauncher.utils;

public class Profile
{
    public String username;
    public String password;
    public String profileName;
    public boolean rememberPsw;
    public String ram;
    
    public Profile(String username, String password, String ram, String profileName, boolean rememberPsw)
    {
    	this.username = username;
    	this.password = password;
    	this.ram = ram;
    	this.profileName = profileName;
    	this.rememberPsw = rememberPsw;
    }
}
