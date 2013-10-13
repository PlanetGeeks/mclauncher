package it.planetgeeks.mclauncher.settings;

public class Settings
{
	
	/** Launcher info **/
	public static String launcherName = "Launcher v4";
	public static String launcherVersion = "1.0.0";
	public static String launcherOwner = "Flood2d";
	
	/** Launcher Updater Settings **/
	
	public static String launcherLink = "null"; //Link di download del launcher
    public static String launcherMD5 = "null"; //Link di download del file che contiene l'md5 del launcher
    public static boolean showUpdateConfirm = true; //Se c'è un update del launcher fai apparire il box per la conferma
    
    /** Launcher Settings **/ 
    public static String gameDir = "testv4"; //La cartella in %APPDATA% dove vengono salvati i file di minecraft e del launcher
    public static int colorProfileFrameLabel = 0x000000;
    public static int colorProfileFrameLabelErrored = 0xFF0000;
     
   
}
