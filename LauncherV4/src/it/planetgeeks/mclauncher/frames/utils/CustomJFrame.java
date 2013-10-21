package it.planetgeeks.mclauncher.frames.utils;

import it.planetgeeks.mclauncher.Settings;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

public class CustomJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
	private int spx = 30;
	private int width = 400;
	private int height = 400;
    
	public CustomJFrame(int w, int h)
	{
		this.width = w;
		this.height = h;
		
		if(Settings.customBorder)
		{
			this.setPreferredSize(new Dimension(width + 2*spx, height + 2*spx));
			this.setUndecorated(true);
			AWTUtilities.setWindowOpaque(this, false);
			contentPanel = new JPanel();
		    contentPanel.setBounds(spx, spx, width, height);
			super.getContentPane().setLayout(null);
			super.getContentPane().add(contentPanel);
			pack();
		}
		else
		{
			this.setPreferredSize(new Dimension(width, height));
			this.setMinimumSize(new Dimension(width, height));
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
		
	}
	
	@Override
	public Container getContentPane()
	{
		if(Settings.customBorder)
		{
			return contentPanel;
		}
		else
		{
			return super.getContentPane();
		}
	}
}
