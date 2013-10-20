package it.planetgeeks.mclauncher.settings;

import it.planetgeeks.mclauncher.frames.EnumLayouts;

public class Settings
{
	
	/** Launcher info **/
	public static String launcherName = "Launcher v4"; //nome del launcher
	public static String launcherVersion = "1.0.0"; //versione del launcher
	public static String launcherOwner = "Flood2d"; //nome del proprietario del launcher
	
	/** Launcher Updater Settings **/
	
	public static String launcherLink = "null"; //Link di download del launcher
    public static String launcherMD5 = "null"; //Link di download del file che contiene l'md5 del launcher
    public static boolean showUpdateConfirm = true; //Se c'è un update del launcher fai apparire il box per la conferma
    
    /** Launcher Settings **/ 
    public static String gameDir = "testv4"; //La cartella in %APPDATA% dove vengono salvati i file di minecraft e del launcher
    public static int colorProfileFrameLabel = 0x000000; 
    public static int colorProfileFrameLabelErrored = 0xFF0000;
    
    public static String newsLink1 = "http://mcupdate.tumblr.com/‎"; //link news 1
    public static String newsLink2 = ""; //link news 2 ( solo con multinews layout )
    public static String newsLink3 = ""; //link news 3 ( solo con multinews layout )
    
    /** Launcher Frame layout mode 
    Questo parametro permette di scegliere un layout ( disposizione dei componenti ) per il launcher, Qui sotto una lista dei layout
    - NEWS           -> Solo news
    - NEWSSKIN       -> news + skin
    - MULTINEWS      -> 3 news
    - MULTINEWSSKIN  -> 3 news + skin
    **/
    public static EnumLayouts layoutMode = EnumLayouts.NEWS_SKIN_MODPACK; //Layout del launcher 
}
