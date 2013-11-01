package it.planetgeeks.mclauncher.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class LoginUtils
{
	private static String excutePost(String targetURL, String urlParameters)
	{
		HttpsURLConnection connection = null;
		try
		{
			URL url = new URL(targetURL);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.connect();

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			StringBuffer response = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null)
			{
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String str1 = response.toString();
			return str1;
		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			if (connection != null)
				connection.disconnect();
		}
	}

	public static String login(String userName, String password)
	{
		try
		{
			String parameters = "user=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&version=" + 13;
			String result = excutePost("https://login.minecraft.net/", parameters);
			if (result == null)
			{
				return "NOCONNECTION";
			}
			if (!result.contains(":"))
			{
				if (result.trim().equals("Bad login"))
				{
					return "LOGINFAILED";
				}
				else
				{
					return "ERROR";
				}
			}

			String splitted[] = result.split(":");
			return splitted[2] + ":" + splitted[3];
		}
		catch (Exception e)
		{
			return "ERROR";
		}
	}
}
