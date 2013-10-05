package it.planetgeeks.mclauncher.utils;

/**
 * @author PlanetGeeks
 *
 */

import java.util.HashMap;

public class EncrypterUtils
{

	public static String encrypt(String str, HashMap<String, String> resourcemap)
	{
		try
		{
			String encryptionKey = resourcemap.get("encrypt.key");
			StringEncrypter encrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, encryptionKey);
			String encryptedString = encrypter.encrypt(str);

			return encryptedString;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String str, HashMap<String, String> resourcemap)
	{
		try
		{
			String encryptionKey = resourcemap.get("encrypt.key");
			StringEncrypter decrypter = new StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME, encryptionKey);
			String decryptedString = decrypter.decrypt(str);

			return decryptedString;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
