package it.planetgeeks.mclauncher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

	private static InputStream is;

	public static String generateBufferedHash(File file) throws NoSuchAlgorithmException, FileNotFoundException, IOException 
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		 
		is = new FileInputStream(file);
		 
		byte[] buffer=new byte[8192];
		int read=0;
		while( (read = is.read(buffer)) > 0)
		{
			md.update(buffer, 0, read);	
		}
		byte[] md5 = md.digest();
		BigInteger bi=new BigInteger(1, md5);
		 
		return bi.toString(16);
	}	
	
	public static String getFileSize(File file)
	{
		return String.valueOf(file.length());
	}	
}
