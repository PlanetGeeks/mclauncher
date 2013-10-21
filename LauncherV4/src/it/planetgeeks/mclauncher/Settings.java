package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.frames.EnumLayouts;

public class Settings
{
	/** Questo è un launcher di minecraft opensource!
	 *  Il codice è stato scritto interamente da Flood del team di PlanetGeeks
	 *  Visita il canale YT : http://www.youtube.com/PlanetGeeks/
	 *  Visita il sito Web  : http://www.planetgeeks.org/
	 *  
	 *  Spero che il launcher risponda alle vostre richieste! Questo è un lavoro fatto per passione
	 *  quindi nulla esclude che ci possono essere bugs! In ogni caso segnalateli se li trovate!
	 */
	
	/** Quasi tutte le scritte presenti nel launcher sono personalizzabili!
	 *  Le scritte sono presenti nei file .lang nel package it.planetgeeks.mclauncher.resources.languages
	 *  Per modificare una scritta trovarla nella lista e sostituire quello che c'è dopo = con il nuovo testo
	 *  Ad esempio per cambiare il titolo del launcher nella lingua italiana basta trovare il file it_IT.lang
	 *  aprirlo ( potete farlo da eclipse ) , trovare la riga con scritto launcher.title=    e dopo l'uguale 
	 *  scrivere il titolo che volete voi. Niente di più semplice.
	 **/
	
	// INFORMAZIONI SUL LAUNCHER //  
	
	/** Nome del launcher **/
	public static String launcherName = "Launcher v4";
	
	/** Versione del launcher **/
	public static String launcherVersion = "1.0.0";
	
	/** Proprietario del launcher **/
	public static String launcherOwner = "Flood2d";
	
	/** Link del pannello informazioni **/
	public static String linkInfo = "http://www.google.it/";
	
	// IMPOSTAZIONI LAUNCHER UPDATER //  
	
	/** Link di download del launcher **/
	public static String launcherLink = "null";
	
	/** Link md5 del launcher **/
    public static String launcherMD5 = "null";
    
    /** Se c'è un update del launcher fa apparire il box per la conferma altrimenti procede forzatamente **/
    public static boolean showUpdateConfirm = true;
    
    
    // IMPOSTAZIONI LAUNCHER //   
    
    /** Cartella del gioco **/
    public static String gameDir = "testv4"; //La cartella in %APPDATA% dove vengono salvati i file di minecraft e del launcher
   
    /** Colore iniziale del label "profile" nel profile editor **/
    public static int colorProfileFrameLabel = 0xFF0000; 
    
    /** Colore di errore del label "profile" nel profile editor **/
    public static int colorProfileFrameLabelErrored = 0xFF0000;
    
    /** Colore label di collegamento ( hyperlinks ) **/
    public static int colorHyperLinks = 0x0000CF;
    
    /** link news 1 **/
    public static String newsLink1 = "http://mcupdate.tumblr.com/‎";
    
    /** link news 2 **/ 
    public static String newsLink2 = "http://www.google.it/"; 
    
    /** link news 3 **/
    public static String newsLink3 = "http://relyze.com/planetgeeks/";
    
    /** Launcher Frame layout mode 
    Questo parametro permette di scegliere un layout ( disposizione dei componenti ) per il launcher, Qui la lista dei layout
    - BG                          -> backgroud
    - BG_SKIN                     -> background + skin
    - BG_MODPACK                  -> background + modpack
    - BG_SKIN_MODPACK             -> background + skin + modpack
    - NEWS,                       -> News
    - NEWS_SKIN,                  -> News + Skin
    - MULTI_NEWS,                 -> 3 News
    - MULTI_NEWS_SKIN,            -> 3 News + Skin
    - NEWS_MODPACK,               -> News + Modpack
    - NEWS_SKIN_MODPACK,          -> News + Skin + Modpack
    - MULTI_NEWS_MODPACK,         -> 3 News + Modpack
    - MULTI_NEWS_SKIN_MODPACK;    -> 3 News + Skin + Modpack
    **/
    public static EnumLayouts layoutMode = EnumLayouts.MULTI_NEWS;
}
