package it.planetgeeks.mclauncher.frames.utils;

import it.planetgeeks.mclauncher.Launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

enum Side
{
	RIGHT,
	LEFT,
	UP,
	DOWN;
}

public class CustomJPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private String imageFile;
	public ImageIcon imageIcon;
	private boolean repeat;
	private Side side;

	public CustomJPanel(boolean repeat)
	{
		super();
		this.repeat = repeat;
	}

	public CustomJPanel(boolean repeat, String image)
	{
		super();
		this.repeat = repeat;
		this.imageFile = image;
		this.imageIcon = Launcher.getResources().getResource(imageFile);
	}
	
	public CustomJPanel(boolean repeat, ImageIcon image)
	{
		super();
		this.repeat = repeat;
		this.imageFile = "";
		this.imageIcon = image;
	}

	public CustomJPanel(LayoutManager layout)
	{
		super(layout);
	}

	public void setTexture(ImageIcon image)
	{
		this.imageIcon = image;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (imageIcon != null && imageIcon.getImage() != null)
		{
			Image image = imageIcon.getImage();
			
			if(repeat)
			{
				int imageX = image.getWidth(null);
				int imageY = image.getHeight(null);
				
                int picsX = this.getWidth()/imageX + 1;
                int picsY = this.getHeight()/imageY + 1;
                
                for(int y = 0; y < picsY; y++)
                {
                	for(int x = 0; x < picsX; x++)
                	{
                		g.drawImage(image, x*imageX, y*imageY, imageX, imageY, this);
                	}
                }   
			}
			else if(side != null)
			{
				if(side == Side.RIGHT)
				{
					g.drawImage(image, this.getWidth() - image.getWidth(null), 0, image.getWidth(null), image.getHeight(this), this);
				}
			}		
			else
			{
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}
	
	public void setRightAlign()
	{
		this.side = Side.RIGHT;
	}
	
	public void setLeftAlign()
	{
		this.side = Side.LEFT;
	}
	
	public void setUpAlign()
	{
	    this.side = Side.UP;
	}
	
	public void setDownAlign()
	{
		this.side = Side.DOWN;
	}
}