package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.utils.EnumBgPos;

/**
 * @author PlanetGeeks
 * 
 */

public class Settings
{
	/**
	 * Questo è un launcher di minecraft opensource!
	 * 
	 *  Il codice e' stato scritto interamente da Flood del team di PlanetGeeks 
	 *  ( a eccezione del package ..uncher.utils.process preso dal launcher originale) 
	 * 
	 * Visita il canale YT :
	 * http://www.youtube.com/PlanetGeeks/ 
	 * 
	 * Visita il sito Web :
	 * http://www.planetgeeks.org/
	 * 
	 * Spero che il launcher risponda alle vostre richieste! Questo e' un lavoro
	 * fatto per passione quindi nulla esclude che ci possano essere bugs! In
	 * ogni caso , se li trovate , segnalateli!
	 */

	/** IMPOSTAZIONI LAUNCHER UPDATER **/

	/**
	 * Link per il controllo della versione del launcher ( che contiene link
	 * download - md5 - size )
	 **/
	public static String launcherLink = "https://dl.dropboxusercontent.com/u/88221856/launcherv4test/launcher.settings";

	/**
	 * Se c'e' un update del launcher fa apparire il box per la conferma
	 * altrimenti procede forzatamente
	 **/
	public static boolean showUpdateConfirm = true;

	/** IMPOSTAZIONI GENERALI **/

	/** Nome del launcher **/
	public static String launcherName = "Launcher v4";

	/** Versione del launcher **/
	public static String launcherVersion = "1.0.0";

	/** Proprietario del launcher **/
	public static String launcherOwner = "Flood2d";

	/** Cartella del gioco **/
	public static String gameDir = "testv4"; // La cartella in %APPDATA% dove
												// vengono salvati i file di
												// minecraft e del launcher

	/** link news 1 **/
	public static String newsLink1 = "http://mcupdate.tumblr.com/‎";

	/** link news 2 **/
	public static String newsLink2 = "http://mcupdate.tumblr.com/";

	/** link news 3 **/
	public static String newsLink3 = "https://dl.dropboxusercontent.com/u/88221856/launcherv4test/t.html";

	/** link per il label vicino al bottone di connessione **/
	public static String websiteLink = "http://www.youtube.com/PlanetGeeks/";

	/** Link del pannello informazioni **/
	public static String linkInfo = "http://www.google.it/";

	/** link per il logo in basso a sinistra nella schermata principale **/
	public static String logoLink = "http://www.youtube.com/PlanetGeeks/";

	/** link per il caricamento dei modpack **/
	public static String modpacks = "https://dl.dropboxusercontent.com/u/88221856/launcherv4test/modpacks/modpacks.list";

	/** link da dove prendere le skins **/
	public static String skinIndex = "https://s3.amazonaws.com/MinecraftSkins/";

	/** se impostato su true appare la funzione "Mostra Console" in Opzioni **/
	public static boolean activateConsole = true;

	/** IMPOSTAZIONI GRAFICHE **/

	/**
	 * Launcher Frame layout mode 
	 * Questo parametro permette di scegliere un layout ( disposizione dei componenti ) per il launcher
	 * Qui la lista dei layout : 
	 * - BG -> backgroud 
	 * - BG_SKIN -> background + skin 
	 * - BG_MODPACK -> background + modpack 
	 * - BG_SKIN_MODPACK -> background + skin + modpack 
	 * - NEWS -> News 
	 * - NEWS_SKIN -> News + Skin 
	 * - MULTI_NEWS -> 3 News 
	 * - MULTI_NEWS_SKIN -> 3 News + Skin 
	 * - NEWS_MODPACK -> News + Modpack 
	 * - NEWS_SKIN_MODPACK -> News + Skin + Modpack 
	 * - MULTI_NEWS_MODPACK -> 3 News + Modpack 
	 * - MULTI_NEWS_SKIN_MODPACK -> 3 News + Skin + Modpack
	 **/
	
	public static EnumLayouts layoutMode = EnumLayouts.BG_SKIN_MODPACK;
	
	/** carica i background del layout BG .. **/
    public static void initializeBackgrounds()
    {
    	Launcher.registerBg("img1.png", EnumBgPos.CENTERED, true);
    	Launcher.setBgDesc("Questo e' lo slideshow del launcher v4! Un'anteprima a solo scopo dimostrativo! E' possibile cambiare questa scritta dal file Settings.java");
    	Launcher.registerBg("img2.png", EnumBgPos.CENTERED, true);
    	Launcher.registerBg("img3.png", EnumBgPos.CENTERED, true);
    	Launcher.setBgDesc("La descrizione non e' obbligatoria! Puo essere omessa e questo rettangolo non apparira'");
    }
    
    /** tempo in secondi tra un background e il successivo **/
    public static int bgTimer = 6;
    
    /** colore del testo di descrizione **/
    public static int descTextColor = 0xDADADA;
    
    /** colore dello sfondo della descrizione **/
    public static int descBgColor = 0x000000;

	/** Per usare bordi della finestra personalizzati **/
	public static boolean customBorder = false;

	/**
	 * Dimensione della barra superiore ( utile per lo spostamento della
	 * finestra sullo schermo )
	 **/
	public static int barHeight = 28;
	
	/** Mostra lo splash Screen (l'immagine da cambiare è splash.png)**/
	public static boolean splashScreen = true;

	/** Colore titolo finestra solo per bordo personalizzato **/
	public static int titleColor = 0xFFFFFF;

	/** Colore di sfondo dei bottoni **/
	public static int buttonsBackground = 0x37363C;

	/** Colore del testo dei bottoni **/
	public static int buttonsForeground = 0xEAEAEB;

	/** Colore dei label nel pannello inferiore **/
	public static int southBarLabelsForeground = 0xEAEAEB;

	/** Colore principale delle scritte **/
	public static int textDefault = 0x000000;

	/** Colore dell'alone di selezione **/
	public static int focus = 0xD0CFD2;

	/**
	 * Colore di sfondo di menu a tendina ,liste, combobox e altri componenti
	 * simili
	 **/
	public static int lightBackground = 0xC1C0C3;

	/** Colore di sfondo dei pannelli **/
	public static int control = 0xD0CFD2;

	/** Colore di base dei componenti **/
	public static int colorBase = 0x504D5C;

	/** Colore di errore del label "profile" nel profile editor **/
	public static int colorLabelErrored = 0xFF0000;

	/** Colore label di collegamento ( hyperlinks ) **/
	public static int colorHyperLinks = 0x006FFF;

	/** Colore background console **/
	public static int consoleBackground = 0x000000;

	/** Colore delle scritte della console **/
	public static int consoleForeground = 0xFFFFFF;
	
	/** Colore delle progress bar **/
	public static int progressBarColor = 0x9198FF;

}
