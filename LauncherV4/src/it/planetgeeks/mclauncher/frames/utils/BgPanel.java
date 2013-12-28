package it.planetgeeks.mclauncher.frames.utils;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.utils.EnumBgPos;
import it.planetgeeks.mclauncher.utils.ImageBg;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author PlanetGeeks
 * 
 */

public class BgPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private ImageBg[] imgs;
	public ImageBg img;
	public ImageBg newest;
	private float alpha = 0f;
	private float alpha_1 = 1f;
	private int posX_0 = 0;
	private int posX_1 = 0;
	private Timer timer;
	private boolean fades[] = new boolean[4];
	private int nextFade = Settings.bgTimer * 25;
	private int ticks = 0;
	private boolean right = true;
	private int currentBg = 0;

	public BgPanel(ImageBg[] imgs)
	{
		super();
		this.imgs = imgs;
		this.img = this.imgs[currentBg];
		this.timer = new Timer(40, this);
		this.timer.start();
		final BgPanel instance = this;
		this.addMouseListener(new CustomMouseListener()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				int x = e.getPoint().x;
				int y = e.getPoint().y;
				if (x >= 0 && x <= instance.getWidth() && y >= 0 && y <= instance.getHeight())
				{
					fades[0] = true;
					fades[1] = false;
				}
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				int x = e.getPoint().x;
				int y = e.getPoint().y;
				if (!(x >= 0 && x <= instance.getWidth() && y >= 0 && y <= instance.getHeight()))
				{
					fades[0] = false;
					fades[1] = true;
				}
			}
		});
	}

	public void setBg(boolean right)
	{
		fades[3] = true;
		this.ticks = 0;
		this.nextFade = Settings.bgTimer * 25 + 100;
		this.right = right;
		changeBg();
		this.newest = this.imgs[currentBg];
	}
	
	private void changeBg()
	{
		if(this.right)
		{
			currentBg = currentBg == this.imgs.length - 1 ? 0 : currentBg + 1;
		}
		else
		{
			currentBg = currentBg == 0 ? this.imgs.length - 1 : currentBg - 1;
		}
	
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon imgIcon = new ImageIcon(this.img.img);
		if (imgIcon != null && imgIcon.getImage() != null)
		{
			Image image = imgIcon.getImage();

			draw(image, img.enumPos, g, img.resizable);

			if (newest != null && newest.img != null)
			{
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

				ImageIcon imgLat = new ImageIcon(newest.img);
				Image imageLat = imgLat.getImage();

				draw(imageLat, newest.enumPos, g, newest.resizable);
			}

			ImageBg[] bgs = new ImageBg[2];
			bgs[0] = img;
			bgs[1] = newest;

			for (int i = 0; i < 2; i++)
			{
				ImageBg current = bgs[i];

				if (current != null && current.desc != null)
				{
					float fade = i == 0 ? -alpha : alpha;

					int posX = i == 0 ? posX_0 : posX_1;

					((Graphics2D) g).setColor(new Color(Settings.descBgColor));
					((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (i == 0 ? 0.5F : 0.0F) + fade / 2));
					((Graphics2D) g).fillRect(0, 0, this.getWidth(), 20);

					if (bgs[0] != null && bgs[0].desc != null && bgs[1] != null && bgs[1].desc != null)
					{
						((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (i == 0 ? (1.0F + fade * 2 > 0 ? 1.0F + fade * 2 : 0.0F) : (1.0F - fade * 2 > 0 ? 0.0F : fade * 2 - 1.0F))));
					}
					else
					{
						((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (i == 0 ? 1.0F : 0.0F) + fade));
					}
					((Graphics2D) g).setColor(new Color(Settings.descTextColor));
					((Graphics2D) g).setFont(new Font("arial", Font.PLAIN, 12));

					double width = ((Graphics2D) g).getFontMetrics().getStringBounds(current.desc, g).getWidth();

					int num = (int) ((this.getWidth() + width) / width);

					for (int a = 0; a < num + 1; a++)
					{
						((Graphics2D) g).drawString(current.desc, posX + 50 * a + ((int) width) * a, 15);
					}

					if (Math.abs(posX) == (int) width)
					{
						posX += (int) width + 50;
					}
				}
			}

			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_1));
		}
	}

	private void draw(Image img, EnumBgPos pos, Graphics g, boolean resize)
	{
		int x = 0;
		int y = 0;
		int width = img.getWidth(null);
		int height = img.getHeight(null);

		if (resize)
		{

			float scaleX = (float) this.getWidth() / (float) width;

			float scaleY = (float) this.getHeight() / (float) height;

			float scale = scaleX > scaleY ? scaleX : scaleY;

			if (scale == 0)
			{
				scale = 1.0F;
			}

			BufferedImage image = Launcher.getResources().getScaledImage(Launcher.getResources().getFromIcon(new ImageIcon(img)), scale, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

			img = new ImageIcon(image).getImage();
		}

		if (pos == EnumBgPos.CENTERED)
		{
			x = this.getWidth() / 2 - img.getWidth(null) / 2;
			y = this.getHeight() / 2 - img.getHeight(null) / 2;
		}
		else if (pos == EnumBgPos.CENTERED_DOWN)
		{
			x = this.getWidth() / 2 - img.getWidth(null) / 2;
			y = this.getHeight() - img.getHeight(null);
		}
		else if (pos == EnumBgPos.CENTERED_UP)
		{
			x = this.getWidth() / 2 - img.getWidth(null) / 2;
		}
		else if (pos == EnumBgPos.LEFT_CENTERED)
		{
			y = this.getHeight() / 2 - img.getHeight(null) / 2;
		}
		else if (pos == EnumBgPos.RIGHT_CENTERED)
		{
			x = this.getWidth() - img.getWidth(null);
			y = this.getHeight() / 2 - img.getHeight(null) / 2;
		}
		else if (pos == EnumBgPos.LEFT_DOWN)
		{
			y = this.getHeight() - img.getHeight(null);
		}
		else if (pos == EnumBgPos.RIGHT_DOWN)
		{
			x = this.getWidth() - img.getWidth(null);
			y = this.getHeight() - img.getHeight(null);
		}
		else if (pos == EnumBgPos.RIGHT_UP)
		{
			x = this.getWidth() - img.getWidth(null);
		}

		g.drawImage(img, x, y, img.getWidth(null), img.getHeight(null), null);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof Timer)
		{
			if ((Timer) e.getSource() == timer)
			{
				if (!fades[3])
				{
					if (ticks == nextFade)
					{
						ticks = 0;
						nextFade = Settings.bgTimer * 25;
						fades[3] = true;
						changeBg();
						newest = this.imgs[currentBg];
					}
					else
					{
						ticks++;
					}
				}
				else
				{
					alpha += 0.050f;

					if (alpha >= 1)
					{
						alpha = 0;
						fades[3] = false;
						img = getCopy(newest);
						newest = null;
						posX_0 = posX_1;
						posX_1 = 0;
					}
				}

				if (newest != null)
				{
					posX_1 -= 1;
				}
				posX_0 -= 1;

				if (fades[0] || fades[1])
				{
					alpha_1 = fades[0] ? alpha_1 + 0.1f : alpha_1 - 0.1f;
					if (alpha_1 > 1 || alpha_1 < 0)
					{
						alpha_1 = alpha_1 > 1 ? 1.0F : 0.0F;
						fades[0] = false;
						fades[1] = false;
					}
				}
			}
		}

		repaint();
	}

	public ImageBg getCopy(ImageBg bg)
	{
		return bg;
	}

}