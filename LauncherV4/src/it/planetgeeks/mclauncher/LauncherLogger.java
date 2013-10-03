package it.planetgeeks.mclauncher;

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
	
    public static void log(LauncherLogger log, String str)
    {
    	System.out.println(log.type + " : " + str);
    }
}
