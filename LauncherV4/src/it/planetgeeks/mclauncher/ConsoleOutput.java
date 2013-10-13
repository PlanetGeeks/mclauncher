package it.planetgeeks.mclauncher;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.SwingUtilities;

public class ConsoleOutput extends OutputStream
{
	private final StringBuilder sb = new StringBuilder();

	public ConsoleOutput()
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
					Launcher.consoleFrame.lines.add(new String[] { "LAUNCHER", text });
					Launcher.consoleFrame.updateTextArea();
				}
			});
			sb.setLength(0);
			return;
		}

		sb.append((char) b);
	}
}