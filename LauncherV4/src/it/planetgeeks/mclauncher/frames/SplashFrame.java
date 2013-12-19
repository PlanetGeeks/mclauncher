package it.planetgeeks.mclauncher.frames;

import java.awt.Dimension;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

public class SplashFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
 
	public SplashFrame()
	{
		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		ImageIcon img = Launcher.getResources().getResource("splash.png");
	    this.setResizable(false);
		this.setMinimumSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		this.setMaximumSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
		this.setContentPane(new CustomJPanel(false, "splash.png"));
	    setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);
		this.setLocationRelativeTo(null);
	}
	
}
