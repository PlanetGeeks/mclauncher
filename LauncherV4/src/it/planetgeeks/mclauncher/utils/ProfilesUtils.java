package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ProfilesUtils
{

	private static ArrayList<Profile> profiles = new ArrayList<Profile>();
	private static File profileFolder = new File(DirUtils.getLauncherDirectory() + File.separator + "profiles");

	public static ArrayList<Profile> getProfiles()
	{
		return profiles;
	}

	public static void loadProfiles()
	{
		File[] profilesList = getProfilesDir().listFiles();

		if (profilesList != null)
		{
			for (int i = 0; i < profilesList.length; i++)
			{
				Profile profile = loadProfile(profilesList[i]);
				if (profile != null)
				{
					profiles.add(profile);
				}
			}
		}
		if (profiles.size() > 0)
		{
			LauncherLogger.log(LauncherLogger.INFO, "Loaded " + profiles.size() + " profiles!");
		}
		else
		{
			LauncherLogger.log(LauncherLogger.INFO, "No profiles loaded!");
		}
	}

	private static Profile loadProfile(File profile)
	{
		if (profile.getName().endsWith(".profile"))
		{
			try
			{
				Profile prof = new Profile("", "", "1024m", profile.getName().substring(0, profile.getName().length() - 8), false);
				BufferedReader br = new BufferedReader(new FileReader(profile));
				String readed = br.readLine();
				boolean errored = false;
				while (readed != null)
				{
					if (readed.startsWith("username") && readed.contains(":"))
					{
						for (int a = 1; a < readed.split(":").length; a++)
						{
							prof.username += readed.split(":")[a];
						}
					}
					if (readed.startsWith("password") && readed.contains(":"))
					{
						for (int a = 1; a < readed.split(":").length; a++)
						{
							prof.password += readed.split(":")[a];
						}
					}
					if (readed.startsWith("rememberPsw") && readed.contains(":"))
					{
						prof.rememberPsw = readed.split(":")[1].equals("true") ? true : false;
					}
					if (readed.startsWith("ram") && readed.contains(":"))
					{
						if (readed.split(":").length != 2)
						{
							LauncherLogger.log(LauncherLogger.GRAVE, "Invalid profile file! '" + profile.getName() + "'");
							errored = true;
						}
						else
						{
							prof.ram = readed.split(":")[1];
						}
					}
					
					readed = br.readLine();
				}
				br.close();
				if (!errored)
				{
					LauncherLogger.log(LauncherLogger.INFO, "Loaded profile '" + prof.profileName + "'!");
					return prof;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean deleteProfile(String profileName, boolean notify)
	{
		for (int i = 0; i < profiles.size(); i++)
		{
			Profile current = profiles.get(i);

			if (current.profileName.equals(profileName))
			{
				profiles.remove(i);
				break;
			}
		}
		File profileFile = new File(getProfilesDir() + File.separator + profileName + ".profile");
		if (profileFile.exists())
		{
			profileFile.delete();
			if(notify)
			{
				LauncherLogger.log(LauncherLogger.INFO, "Profile '" + profileName + "' deleted!");
			}
			return true;
		}
		if(notify)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on deleting '" + profileName + ".profile' from profiles folder!");
		}
	
		return false;
	}

	public static boolean createProfile(Profile profile)
	{
		for (int i = 0; i < profiles.size(); i++)
		{
			Profile current = profiles.get(i);

			if (current.profileName.equals(profile.profileName))
			{
				LauncherLogger.log(LauncherLogger.INFO, "Profile with profileName : '" + profile.profileName + "' already exists!");
				return false;
			}
		}
		File profileFile = new File(getProfilesDir().getAbsolutePath() + File.separator + profile.profileName + ".profile");
		if (!profileFile.exists())
		{
			writeProfile(profileFile, profile);
			profiles.add(profile);
			LauncherLogger.log(LauncherLogger.INFO, "Profile '" + profile.profileName + "' created!");
			return true;
		}
		else
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on deleting '" + profile.profileName + ".profile' from profiles folder!");
			return false;
		}
	}

	public static boolean modifyProfile(String latProfileName, String profileName, Profile profile)
	{
		if(deleteProfile(latProfileName, false))
		{
			profile.profileName = profileName;
			writeProfile(new File(getProfilesDir() + File.separator + profile.profileName + ".profile"), profile);
			profiles.add(profile);
			LauncherLogger.log(LauncherLogger.INFO, "Profile with profileName : '" + profileName + "' modified!");
			return true;
		}
		LauncherLogger.log(LauncherLogger.GRAVE, "Error on modifing profile '" + profileName + "'!");
		return false;
	}

	private static void writeProfile(File file, Profile profile)
	{
		try
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("username:" + profile.username);
			bw.newLine();
			bw.write("password:" + profile.password);
			bw.newLine();
			bw.write("ram:" + profile.ram);
			bw.newLine();
			bw.write("rememberPsw:" + (profile.rememberPsw ? "true" : "false"));
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on saving profile '" + profile.profileName + "'!");
		}
	}

	private static File getProfilesDir()
	{
		if(!profileFolder.exists())
		{
			profileFolder.mkdirs();
		}
		return profileFolder;
	}
	
	public static Profile getProfile(String profileName)
	{
		for(Profile prof : profiles)
		{
			if(prof.profileName.equals(profileName))
			{
				return prof;
			}
		}
		return null;
	}

}
