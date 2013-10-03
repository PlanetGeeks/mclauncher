package it.planetgeeks.mclauncher.utils;

/**
 * @author PlanetGeeks
 *
 */

import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SkinsManager
{

	public static void drawCharacter(JPanel contentPane, ActionListener listener, String url, BufferedImage img, int x, int y, List<JButton> buttons, int mode, boolean forceUrl)
	{
		try
		{
			if (img != null)
			{
				originalImage = img;
			}
			else
			{
				if (originalImage == null)
				{
					try
					{
						originalImage = ImageIO.read(new URL(url));
					}
					catch (Exception e)
					{
						originalImage = ImageIO.read(new URL("http://epicrealm.it/game/skins/char.png"));
					}
				}
			}

			if(mode == 0)
			{
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 40, 8, 48, 16, x - 4, y - 5, 8));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 8, 8, 16, 16, x, y, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 20, 20, 28, 32, x, y + 56, 7));
			    buttons.add(drawCropped(contentPane, listener, originalImage, 2, 44, 20, 48, 32, x - 28, y + 56, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 44, 20, 48, 32, x + 56, y + 56, 7, true));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 4, 20, 8, 32, x, y + 140, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 4, 20, 8, 32, x + 28, y + 140, 7, true));
			}
			else if (mode == 1)
			{
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 32, 8, 40, 16, x - 4, y - 5, 8));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 0, 8, 8, 16, x, y, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 40, 20, 44, 32, x + 14, y + 56, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 0, 20, 4, 32, x + 14, y + 140, 7));
			}
			else if (mode == 2)
			{
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 56, 8, 64, 16, x - 4, y - 5, 8));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 24, 8, 32, 16, x, y, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 32, 20, 40, 32, x, y + 56, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 52, 20, 56, 32, x - 28, y + 56, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 52, 20, 56, 32, x + 56, y + 56, 7, true));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 12, 20, 16, 32, x, y + 140, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 12, 20, 16, 32, x + 28, y + 140, 7, true));
			}
			else if (mode == 3)
			{
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 48, 8, 56, 16, x - 4, y - 5, 8));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 16, 8, 24, 16, x, y, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 52, 20, 56, 32, x + 14, y + 56, 7));
				buttons.add(drawCropped(contentPane, listener, originalImage, 2, 8, 20, 12, 32, x + 14, y + 140, 7));
			}
		  
		}
		 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static JButton drawCropped(JPanel contentPane, ActionListener listener, BufferedImage img, int type, int sx1, int sy1, int sx2, int sy2, int x, int y, int scale)
	{
		return drawCropped(contentPane, listener, img, type, sx1, sy1, sx2, sy2, x, y, scale, false);
	}

	public static JButton drawCropped(JPanel contentPane, ActionListener listener, BufferedImage img, int type, int sx1, int sy1, int sx2, int sy2, int x, int y, int scale, boolean reflect)
	{
		BufferedImage resizedImage = new BufferedImage((sx2 - sx1) * scale, (sy2 - sy1) * scale, type);
		Graphics2D g = resizedImage.createGraphics();
		int asx2 = sx2;
		int asx1 = sx1;
		if (reflect)
		{
			asx2 = sx1;
			asx1 = sx2;
		}
		g.drawImage(img, 0, 0, (sx2 - sx1) * scale, (sy2 - sy1) * scale, asx1, sy1, asx2, sy2, null);
		g.dispose();

		tmp = new JButton(new ImageIcon(resizedImage));
		tmp.setSelectedIcon(tmp.getIcon());
		tmp.setDisabledIcon(tmp.getPressedIcon());
		tmp.setPressedIcon(tmp.getIcon());
		tmp.setOpaque(false);
		tmp.setFocusable(false);

		tmp.setContentAreaFilled(false);
		tmp.setBorderPainted(false);
		tmp.setRolloverEnabled(false);

		tmp.setBounds(x, y, (sx2 - sx1) * scale, (sy2 - sy1) * scale);
		contentPane.add(tmp);
		return tmp;
	}

	public static JButton tmp;
	public static BufferedImage originalImage;
	public static BufferedImage tempSkin;
}
