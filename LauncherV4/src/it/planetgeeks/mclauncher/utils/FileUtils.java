package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils
{

	private static InputStream is;

	public static String generateBufferedHash(File file) throws NoSuchAlgorithmException, FileNotFoundException, IOException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");

		is = new FileInputStream(file);

		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = is.read(buffer)) > 0)
		{
			md.update(buffer, 0, read);
		}
		byte[] md5 = md.digest();
		BigInteger bi = new BigInteger(1, md5);

		return bi.toString(16);
	}

	public static String getFileSize(File file)
	{
		return String.valueOf(file.length());
	}

	public static boolean copyFile(File sourceFile, File destFile)
	{
		FileChannel source = null;
		FileChannel destination = null;
        try
		{
			if (!destFile.exists())
			{
				destFile.createNewFile();
			}
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
			if (source != null)
			{
				source.close();
			}
			if (destination != null)
			{
				destination.close();
			}
		} catch(IOException e)
		{
			e.printStackTrace();
			LauncherLogger.log(LauncherLogger.SEVERE, "Could not save log file!");
			return false;
		}
        return true;
	}
}
