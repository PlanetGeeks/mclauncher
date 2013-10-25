package it.planetgeeks.mclauncher.frames.utils;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

public class CustomJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private int spx = 130;
	private int width;
	private int height;
	private int positionX;
	private int positionY;
	private Point initialClick;
	private JLabel closeBtn;
	private JLabel minimizeBtn;
	private JLabel titleLbl;

	public CustomJFrame(int w, int h)
	{
		this.width = w;
		this.height = h;
		
		if(Settings.customBorder)
		{
			setUndecorated(true);
			
			setModalExclusionType(null);
			setContentPane(new CustomJPanel(false , "corner.png"));
			setPreferredSize(new Dimension(width + 2*spx, height + 2*spx));
			contentPanel = new CustomJPanel(true, "bg.png");
		    contentPanel.setBounds(spx, spx, width, height);
		    super.getContentPane().setLayout(null);
		    closeBtn = new JLabel();
			closeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 0, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 0, 19, 19)));
			closeBtn.setLocation(width + spx - 30, spx - Settings.barHeight/2 - 19/2);
			closeBtn.setSize(19, 19);
			minimizeBtn = new JLabel();
			minimizeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 0, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 19, 19, 19)));
			minimizeBtn.setLocation(width + spx - 19 - 40, spx - Settings.barHeight/2 - 19/2);
			minimizeBtn.setSize(19, 19);
			titleLbl = new JLabel();
			titleLbl.setForeground(new Color(Settings.titleColor));
			titleLbl.setSize(840, Settings.barHeight);
			titleLbl.setLocation(spx + 13, spx - Settings.barHeight/2 - titleLbl.getHeight() / 2);
			
			closeBtn.addMouseListener(new CustomMouseListener()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					closeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 19, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 0, 19, 19)));
				}
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					closeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 0, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 0, 19, 19)));
				    Launcher.closeLauncher();
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			
			minimizeBtn.addMouseListener(new CustomMouseListener()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					minimizeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 19, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 19, 19, 19)));
				}
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					minimizeBtn.setIcon(Launcher.resources.getMergedResource(Launcher.resources.getCroppedResorce("buttons.png", 0, 0, 19, 19), Launcher.resources.getCroppedResorce("buttons.png", 19, 19, 19, 19)));
					setState(Frame.ICONIFIED);
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			
			super.getContentPane().add(titleLbl);
			super.getContentPane().add(closeBtn);
			super.getContentPane().add(minimizeBtn);
			super.getContentPane().add(contentPanel);
			
			addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					initialClick = e.getPoint();
				}
			});

			addMouseMotionListener(new MouseMotionAdapter()
			{
				public void mouseDragged(MouseEvent e)
				{
					if (initialClick.x > spx && initialClick.x < width + spx && initialClick.y <= spx && initialClick.y >= spx - Settings.barHeight)
					{
						positionX += e.getPoint().x - initialClick.x;
						positionY += e.getPoint().y - initialClick.y;
						setLocation(positionX, positionY);
					}
				}
			});
			
			AWTUtilities.setWindowOpaque(this, false);
			
			pack();
		}
		else
		{
			this.setContentPane(new CustomJPanel(true, "bg.png"));
			this.setPreferredSize(new Dimension(width, height + 37));
			this.setMinimumSize(new Dimension(width, height + 37));
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
	}

	@Override
	public Container getContentPane()
	{
		if (Settings.customBorder)
		{
			return contentPanel;
		}
		else
		{
			return super.getContentPane();
		}
	}
	
	@Override
	public void setTitle(String text)
	{
		if(Settings.customBorder)
		{
			this.titleLbl.setText(text);
		}
		else
		{
			super.setTitle(text);
		}
	}

}
