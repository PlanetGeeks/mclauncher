package it.planetgeeks.mclauncher.frames.utils;

import it.planetgeeks.mclauncher.Launcher;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CustomJPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private String imageFile;
	public ImageIcon imageIcon;

	public CustomJPanel()
	{
		super();
	}

	public CustomJPanel(String image)
	{
		super();
		this.imageFile = image;
		this.imageIcon = Launcher.resources.getResource(imageFile);
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
		if (imageIcon != null)
		{
			Image image = imageIcon.getImage();
			if (image != null)
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}

	}
}