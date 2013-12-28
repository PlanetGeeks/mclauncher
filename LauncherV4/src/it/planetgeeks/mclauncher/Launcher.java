package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.frames.ConsoleFrame;
import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.frames.InfoFrame;
import it.planetgeeks.mclauncher.frames.LauncherFrame;
import it.planetgeeks.mclauncher.frames.MPFilterFrame;
import it.planetgeeks.mclauncher.frames.MPInfoFrame;
import it.planetgeeks.mclauncher.frames.MemoryFrame;
import it.planetgeeks.mclauncher.frames.OptionsFrame;
import it.planetgeeks.mclauncher.frames.ProfileFrame;
import it.planetgeeks.mclauncher.frames.SplashFrame;
import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.resources.ResourcesUtils;
import it.planetgeeks.mclauncher.updater.LauncherUpdater;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.EnumBgPos;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.ImageBg;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * @author PlanetGeeks
 * 
 */

public class Launcher
{
	private static LauncherFrame launcherFrame;
	private static ProfileFrame profileFrame;
	private static MemoryFrame memoryFrame;
	private static InfoFrame infoFrame;
	private static ConsoleFrame consoleFrame;
	private static OptionsFrame optionsFrame;
	private static MPFilterFrame filterFrame;
	private static MPInfoFrame mpinfoframe;
	private static ResourcesUtils resources = new ResourcesUtils();
	public static boolean forceUpdate = false;
	private static ArrayList<ImageBg> listBgs = new ArrayList<ImageBg>();
	public static int currentBg = 0;
	public static boolean loaded = false;
	public static SplashFrame splash;

	public static void main(String[] args)
	{
		if (args.length > 0 && args[0].equals("start"))
		{
			loadLookAndFeel();
			consoleFrame = new ConsoleFrame();
			LauncherLogger.loadLogger();
			printLauncherInfo();
			LauncherProperties.loadProperties();
			dropUpdaterErrors(args);
			LanguageUtils.loadLanguages();
			consoleFrame.updateComponents();
			ProfilesUtils.loadProfiles();
			MemoryUtils.loadMemories();
			Settings.initializeBackgrounds();
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					launcherFrame = new LauncherFrame();
					launcherFrame.setVisible(true);
					loaded = true;
				}
			});
			ModPackUtils.startLoading();
			if (isNewsLayout())
				loadNews();
			if (splash != null)
				splash.setVisible(false);
		}
		else
		{
			if (Settings.splashScreen)
				displaySplash();
			loadLookAndFeel();
			LauncherProperties.loadProperties();
			LanguageUtils.loadLanguages();
			checkForCodeUpdated();
			LauncherUpdater.startCheck();
		}

	}

	private static void checkForCodeUpdated()
	{
		if(Settings.devMode)
		{
			File dev = new File(DirUtils.getLauncherDirectory() + File.separator + "dev");
			
		    String latestVersion = null;
			
			if(dev.exists())
			{
				ArrayList<String> readed = FileUtils.readFileContent(true, dev.getAbsolutePath());
				
				for(String line : readed)
				{
					if(line.contains("version="))
					{
				        latestVersion = line.split("=")[1];
					}
				}
			}
			
			if(FileUtils.downloadFile("https://dl.dropboxusercontent.com/u/88221856/PlanetGeeks/launcherV4Utils/updates", dev))
			{
				ArrayList<String> update = FileUtils.readFileContent(true, dev.getAbsolutePath());
		        
				if(latestVersion != null)
				{
					for(String line : update)
					{
						if(line.contains("version="))
						{
					       String version = line.split("=")[1];
					       
					       if(!version.equals(latestVersion))
					       {
					    	   JScrollPane scroll = new JScrollPane();
					    	   scroll.setPreferredSize(new Dimension(300, 200));
					    	   JTextArea textArea = new JTextArea();  
					    	   for(int i = 1; i < update.size(); i++)
					    	   {
					    		   textArea.append(update.get(i) + "\n");
					    	   }
					    	   scroll.setViewportView(textArea);
					    	   JOptionPane.showMessageDialog(null, scroll);
					       }
						}
					}
				}
			}
		}
	}

	public static void displaySplash()
	{
		Launcher.splash = new SplashFrame();
		Launcher.splash.setVisible(true);
	}

	private static boolean isNewsLayout()
	{
		EnumLayouts l = Settings.layoutMode;

		if (l != EnumLayouts.BG && l != EnumLayouts.BG_MODPACK && l != EnumLayouts.BG_SKIN && l != EnumLayouts.BG_SKIN_MODPACK)
		{
			return true;
		}

		else
			return false;
	}

	/**
	 * @param img
	 *            la path dell'immagine
	 * @param bg
	 *            posizione dell'immagine
	 * @param resizable
	 *            Ridimensionare immagine ?
	 **/
	public static void registerBg(String img, EnumBgPos bg, boolean resizable)
	{
		listBgs.add(new ImageBg(img, bg, resizable));
	}

	public static void setBgDesc(String desc)
	{
		listBgs.get(listBgs.size() - 1).setDesc(desc);
	}

	private static void loadLookAndFeel()
	{
		try
		{
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{

					UIManager.setLookAndFeel(info.getClassName());
					UIManager.getLookAndFeelDefaults().put("nimbusOrange", (new Color(Settings.progressBarColor)));
					UIManager.put("nimbusBlueGrey", (new Color(Settings.buttonsBackground)));
					UIManager.put("nimbusBase", (new Color(Settings.colorBase)));
					UIManager.put("control", (new Color(Settings.control)));
					UIManager.put("nimbusLightBackground", (new Color(Settings.lightBackground)));
					UIManager.put("nimbusFocus", (new Color(Settings.focus)));
					UIManager.put("text", (new Color(Settings.textDefault)));
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(LauncherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public static void openProfileEditor(Profile profile)
	{
		profileFrame = new ProfileFrame(profile);
		profileFrame.setVisible(true);
	}

	public static void openOrCloseMemoryEditor(int parent, Object extra)
	{
		if (memoryFrame == null || !memoryFrame.isVisible())
		{
			memoryFrame = new MemoryFrame(parent, extra);
			memoryFrame.setVisible(true);
		}
		else
		{
			memoryFrame.close();
		}
	}

	public static void openOrCloseFilterFrame()
	{
		if (filterFrame == null || !filterFrame.isVisible())
		{
			filterFrame = new MPFilterFrame();
			filterFrame.setVisible(true);
		}
		else
		{
			filterFrame.setVisible(false);
		}
	}

	public static void openOrCloseMPInfoFrame(ModPack modpack)
	{
		if (mpinfoframe == null || !mpinfoframe.isVisible())
		{
			if (modpack != null)
			{
				mpinfoframe = new MPInfoFrame(modpack);
				mpinfoframe.setVisible(true);
			}
		}
		else
		{
			mpinfoframe.setVisible(false);
		}
	}

	public static void openOrCloseInfoFrame()
	{
		if (isInfoOpened())
		{
			infoFrame.setVisible(false);
		}
		else
		{
			infoFrame = new InfoFrame();
			infoFrame.setVisible(true);
		}
	}

	public static void openOrCloseConsole()
	{
		if (isConsoleOpened())
		{
			consoleFrame.setVisible(false);
		}
		else
		{
			consoleFrame.setVisible(true);
		}
	}

	public static void openOrCloseOptionsFrame()
	{
		if (isAdvOptionsOpened())
		{
			optionsFrame.setVisible(false);
		}
		else
		{
			optionsFrame = new OptionsFrame();
			optionsFrame.setVisible(true);
		}
	}

	public static boolean isMemoryFrameOpened()
	{
		return memoryFrame != null ? (memoryFrame.isVisible() ? true : false) : false;
	}

	public static boolean isConsoleOpened()
	{
		return consoleFrame != null ? (consoleFrame.isVisible() ? true : false) : false;
	}

	public static boolean isAdvOptionsOpened()
	{
		return optionsFrame != null ? (optionsFrame.isVisible() ? true : false) : false;
	}

	public static boolean isInfoOpened()
	{
		return infoFrame != null ? (infoFrame.isVisible() ? true : false) : false;
	}

	public static ConsoleFrame getConsoleFrame()
	{
		return consoleFrame;
	}

	public static LauncherFrame getLauncherFrame()
	{
		return launcherFrame;
	}

	public static ResourcesUtils getResources()
	{
		return resources;
	}

	public static OptionsFrame getOptions()
	{
		return optionsFrame;
	}

	private static void dropUpdaterErrors(String[] args)
	{
		if (args.length > 1)
		{
			for (int i = 1; i < args.length; i++)
			{
				if (args[i].equals("ERROR0"))
				{
					LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading launcher check file!");
				}
				else if (args[i].equals("ERROR1"))
				{
					LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading launcher!");
				}
			}
		}
	}

	public static void setUpdatingModPack(boolean updating)
	{
		launcherFrame.southPanel.setUpdating(updating);
	}

	private static void printLauncherInfo()
	{
		LauncherLogger.log(LauncherLogger.INFO, Settings.launcherName + " | " + "Version " + Settings.launcherVersion + " | " + "Owned by " + Settings.launcherOwner);
	}

	public static void hideOrShowWindows(boolean hide)
	{
		if (hide)
		{
			if (launcherFrame != null)
				launcherFrame.setVisible(hide ? false : true);
			if (profileFrame != null)
				profileFrame.setVisible(false);
			if (memoryFrame != null)
				memoryFrame.setVisible(false);
			if (infoFrame != null)
				infoFrame.setVisible(false);
			if (optionsFrame != null)
				optionsFrame.setVisible(false);
			if (filterFrame != null)
				filterFrame.setVisible(false);
			if (mpinfoframe != null)
				mpinfoframe.setVisible(false);
		}
		else
		{
			if (launcherFrame != null)
				launcherFrame.setVisible(hide ? false : true);
		}
	}

	public static void loadNews()
	{
		Thread th = new Thread()
		{
			@Override
			public void run()
			{
				EnumLayouts layout = Settings.layoutMode;

				JEditorPane[] panels = new JEditorPane[3];
				for (int i = 0; i < 3; i++)
				{
					String link = null;
					if (i == 0)
						link = Settings.newsLink1;
					if (i == 1)
						link = Settings.newsLink2;
					if (i == 2)
						link = Settings.newsLink3;

					JEditorPane pane = null;
					try
					{
						pane = new JEditorPane(link);
						pane.setContentType("text/html");
						pane.addHyperlinkListener(new HyperlinkListener()
						{
							public void hyperlinkUpdate(HyperlinkEvent he)
							{
								if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
								{
									try

									{
										Desktop.getDesktop().browse(he.getURL().toURI());
									}
									catch (Exception e)
									{
										e.printStackTrace();
									}
								}
							}
						});
					}
					catch (IOException e)
					{
						pane = new JEditorPane();
						pane.setText(LanguageUtils.getTranslated("launcher.newsError"));
					}
					pane.setEditable(false);
					pane.setBackground(Color.BLACK);
					pane.setBorder(null);
					panels[i] = pane;
				}

				int max = 1;
				if (layout == EnumLayouts.MULTI_NEWS || layout == EnumLayouts.MULTI_NEWS_MODPACK || layout == EnumLayouts.MULTI_NEWS_SKIN || layout == EnumLayouts.MULTI_NEWS_SKIN_MODPACK)
					max = 3;

				for (int i = 0; i < max; i++)
				{
					try
					{
						while (launcherFrame == null || launcherFrame.mainPanel == null || launcherFrame.mainPanel.jfxScrollPanels[i] == null)
						{
							Thread.sleep(200);
						}

						JScrollPane j = new JScrollPane();
						j.setViewportView(panels[i]);

						launcherFrame.mainPanel.jfxScrollPanels[i] = j;

					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				launcherFrame.mainPanel.updateNews();
			}
		};

		th.start();
	}

	public static void changeBg(boolean right)
	{
		if (right)
		{
			if (currentBg == listBgs.size() - 1)
			{
				currentBg = 0;
				return;
			}
			else
			{
				currentBg++;
				return;
			}
		}
		else
		{
			if (currentBg == 0)
			{
				currentBg = listBgs.size() - 1;
				return;
			}
			else
			{
				currentBg--;
				return;
			}
		}
	}

	public static ImageBg[] getBgArray()
	{
		ImageBg[] imgs = new ImageBg[listBgs.size()];
		for (int i = 0; i < imgs.length; i++)
		{
			imgs[i] = listBgs.get(i);
		}

		return imgs;
	}

	public static int getBgLength()
	{
		return listBgs.size();
	}

	public static void languageChanged()
	{
		if (launcherFrame != null)
			launcherFrame.loadTranslations();
		if (launcherFrame.mainPanel != null)
			launcherFrame.mainPanel.loadTranslations();
		if (launcherFrame.southPanel != null)
			launcherFrame.southPanel.loadTranslations();
		if (profileFrame != null)
			profileFrame.loadTranslations();
		if (memoryFrame != null)
			memoryFrame.loadTranslations();
		if (infoFrame != null)
			infoFrame.loadTranslations();
		if (consoleFrame != null)
			consoleFrame.loadTranslations();
		if (optionsFrame != null)
			optionsFrame.loadTranslations();
		if (filterFrame != null)
			filterFrame.loadTranslations();
		if (mpinfoframe != null)
			mpinfoframe.loadTranslations();
	}

	public static void closeLauncher()
	{
		System.exit(0);
	}

}
