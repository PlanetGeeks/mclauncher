package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.Launcher;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.SwingUtilities;

/**
 * @author PlanetGeeks
 *
 */

public class ConsoleOutStream extends OutputStream
{
	private final StringBuilder sb = new StringBuilder();

	public ConsoleOutStream()
	{
		sb.append("");
	}

	@Override
	public void flush()
	{
	}

	@Override
	public void close()
	{
	}

	@Override
	public void write(int b) throws IOException
	{

		if (b == '\r')
			return;

		if (b == '\n')
		{
			final String text = sb.toString() + "\n";
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					if(text.startsWith("[MINECRAFT]"))
					{
						Launcher.getConsoleFrame().lines.add(new String[] { "MINECRAFT", text.substring(11) });
					}
					else
					{
						Launcher.getConsoleFrame().lines.add(new String[] { "LAUNCHER", text });
					}
					Launcher.getConsoleFrame().updateTextArea();
				}
			});
			sb.setLength(0);
			return;
		}

		sb.append((char) b);
	}
}