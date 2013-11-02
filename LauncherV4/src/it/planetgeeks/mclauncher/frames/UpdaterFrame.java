package it.planetgeeks.mclauncher.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.updater.LauncherUpdater;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;

public class UpdaterFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private String pathUrl;
	private File dest;

	public UpdaterFrame(String pathUrl, File dest)
	{
		this.pathUrl = pathUrl;
		this.dest = dest;
		initComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
	}

	private void initComponents()
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);

		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		
		setTitle(LanguageUtils.getTranslated("updater.dialogTitle"));
		
		progressBar = new JProgressBar();
		lbl1 = new JLabel();
		lbl2 = new JLabel();

		this.setResizable(false);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setText(LanguageUtils.getTranslated("updater.startingDownload"));

		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		

		String str = new String(pathUrl);
	    
	    if(str.length() > 51)
	    {
	    	str = pathUrl.substring(0,24) + "..." + pathUrl.substring(pathUrl.length() - 24, pathUrl.length());
	    }
	    
	    lbl2.setText(str);
	    
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE).addComponent(lbl1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lbl2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(14, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(14, 14, 14).addComponent(lbl1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE).addComponent(lbl2).addGap(14, 14, 14).addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE).addGap(14, 14, 14)));

		pack();
	}

	public void startDownload()
	{
		class Download implements Runnable
		{
			@Override
			public void run()
			{
				try
				{
					URL url = new URL(pathUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();

					float totalDataRead = 0;
					BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
					FileOutputStream fos = new FileOutputStream(dest);
					BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
					byte[] data = new byte[1024];
					int i = 0;
					int filesize = connection.getContentLength();
			    	
					while ((i = in.read(data, 0, 1024)) >= 0)
					{
						totalDataRead = totalDataRead + i;
						bout.write(data, 0, i);
						float perc = (totalDataRead * 100) / filesize;
						updateStatus((int)perc);
						Thread.sleep(1);
					}
					bout.close();
					fos.close();
					in.close();
					connection.disconnect();
					updateStatus(-1);
				}
				catch (Exception e)
				{
					updateStatus(-2);
					e.printStackTrace();
				}
			}
		}
		
		Thread thread = new Thread(new Download());
		
		thread.start();
	}
	
	private void updateStatus(int percent)
	{
		if(percent >= 0 && percent <= 100)
		{
			progressBar.setValue(percent);
			
		    lbl1.setText(LanguageUtils.getTranslated("updater.downloading") + " " + percent + "%");
		    
		    String str = new String(pathUrl);
		    
		    if(str.length() > 51)
		    {
		    	str = pathUrl.substring(0,24) + "..." + pathUrl.substring(pathUrl.length() - 24, pathUrl.length());
		    }
		    
		    lbl2.setText(str);
		}
		else
		{
			if(percent == -1)
			{
				LauncherUpdater.openLauncher(null);
			}
			else if(percent == -2)
			{
				JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("updater.downloading.error"), LanguageUtils.getTranslated("updater.dialogTitle"), JOptionPane.ERROR_MESSAGE);
			
				LauncherUpdater.openLauncher("ERROR1");
			}
			this.setVisible(false);
		}
	}

	private JLabel lbl1;
	private JLabel lbl2;
	private JProgressBar progressBar;
}
