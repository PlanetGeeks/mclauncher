package it.planetgeeks.mclauncher;

import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.LogPrintStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author PlanetGeeks
 *
 */

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

	private static boolean initialized = false;
	
	private static File logFolder = new File(DirUtils.getLauncherDirectory() + File.separator + "logs");

	private static File currentLog;

	private static String getDate(String regex)
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(regex);
		return sdf.format(cal.getTime());
	}

	public static void log(LauncherLogger log, String str)
	{
		if(initialized)
		{
			System.out.println("[" + getDate("HH:mm:ss") + "]" + "[LAUNCHER]" + log.type + " : " + str);
		}
	}

	public static void saveLog(JTextArea consolePanel)
	{
		try
		{
			JFileChooser saveFile = new JFileChooser();
			if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				File toSave = saveFile.getSelectedFile();
				if (toSave.exists())
				{
					if (JOptionPane.showConfirmDialog(null, LanguageUtils.getTranslated("launcher.saveoverwritemessage"), LanguageUtils.getTranslated("launcher.saveoverwritetitle"), JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
					{
						return;
					}
				}
				else
				{
					toSave.createNewFile();
				}

				BufferedWriter bw = new BufferedWriter(new FileWriter(toSave));
				bw.write(consolePanel.getText());
				bw.close();

				JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.savelogmessage"), LanguageUtils.getTranslated("launcher.savelogtitle"), JOptionPane.OK_CANCEL_OPTION);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			LauncherLogger.log(LauncherLogger.SEVERE, "Error on saving log!");
		}
	}

	private static File getLogFolder()
	{
		if (!logFolder.exists())
		{
			logFolder.mkdirs();
		}
		return logFolder;
	}
	
	public static void loadLogger()
	{
		initialized = true;
		
		try
		{
			currentLog = new File(getLogFolder() + File.separator + "log-" + getDate("yyyy.MM.dd.HH.mm.ss"));
			File[] logs = getLogFolder().listFiles();
			
			int logsMax;
			
			try
			{
				String maxLogs = LauncherProperties.getProperty("automaticLogs");
				logsMax = Integer.valueOf(maxLogs);
			}
			catch (NumberFormatException e)
			{
				LauncherProperties.modifyProperty("automaticLogs", "5");
				logsMax = 5;
			}
			
			int logsLenght = logs.length;
			
			if (logs != null && logsLenght >= logsMax)
			{
				for(int i = 0; i < logsLenght - (logsMax - 1); i++)
				{
					deleteOldestLog(logs);
					logs = getLogFolder().listFiles();
				}
			}
			
			FileOutputStream file = new FileOutputStream(currentLog.getAbsolutePath());
			LogPrintStream lpr = new LogPrintStream(file, System.out, new PrintStream(Launcher.getConsoleFrame().out));
			System.setOut(lpr);
			System.setErr(lpr);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void deleteOldestLog(File[] logs)
	{
		File toDelete = null;
		for (int a = 0; a < 6; a++)
		{
			boolean breaked = false;
			for (int i = 0; i < logs.length; i++)
			{
				String[] date = logs[i].getName().substring(4).split(Pattern.quote("."));
				if (date.length != 6)
				{
					toDelete = logs[i];
					break;
				}
				int first = 0;
				try
				{
					first = Integer.valueOf(date[a]);
				}
				catch (NumberFormatException e)
				{
					toDelete = logs[i];
					break;
				}
				if (toDelete != null)
				{
					String[] current = logs[i].getName().substring(4).split(Pattern.quote("."));

					if (first < Integer.valueOf(current[a]))
					{
						toDelete = logs[i];
					}
				}
				else
				{
					toDelete = logs[i];
				}
			}
			if (breaked)
			{
				break;
			}
		}
		toDelete.delete();
	}
}
