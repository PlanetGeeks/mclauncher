package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherProperties;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author PlanetGeeks
 *
 */

public class OptionsFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public OptionsFrame()
	{
		initComponents();
	}

	private void initComponents()
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);
		
		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		
		setTitle(LanguageUtils.getTranslated("launcher.options.title"));
		setSize(287, 320);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
		checkbox = new JCheckBox();
		logSpinner = new JSpinner();
		logLbl = new JLabel();
		paramSpinner = new JTextField();
		paramLbl = new JLabel();
		saveBtn = new JButton();
		saveBtn.setForeground(new Color(Settings.buttonsForeground));
		cancelBtn = new JButton();
		cancelBtn.setForeground(new Color(Settings.buttonsForeground));
		gameDirBtn = new JButton();
		gameDirBtn.setForeground(new Color(Settings.buttonsForeground));
		launcherDirBtn = new JButton();
		launcherDirBtn.setForeground(new Color(Settings.buttonsForeground));

		checkbox.setText(LanguageUtils.getTranslated("launcher.options.openlauncherafterexit"));
		checkbox.setHorizontalAlignment(SwingConstants.LEFT);

		logLbl.setHorizontalAlignment(SwingConstants.LEFT);
		logLbl.setText(LanguageUtils.getTranslated("launcher.options.automaticlogs"));
		logLbl.setToolTipText(LanguageUtils.getTranslated("launcher.options.automaticlogsTip"));

		paramLbl.setHorizontalAlignment(SwingConstants.LEFT);
		paramLbl.setText(LanguageUtils.getTranslated("launcher.options.additiveparam"));

		saveBtn.setText(LanguageUtils.getTranslated("launcher.options.save"));

		cancelBtn.setText(LanguageUtils.getTranslated("launcher.options.cancel"));

		gameDirBtn.setText(LanguageUtils.getTranslated("launcher.options.opengamefolder"));

		launcherDirBtn.setText(LanguageUtils.getTranslated("launcher.options.openlauncherfolder"));

		gameDirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopUtils.openFolder(DirUtils.getWorkingDirectory());
			}
		});

		launcherDirBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DesktopUtils.openFolder(DirUtils.getLauncherDirectory());
			}
		});

		saveBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
                LauncherProperties.modifyProperty("openLauncherAfterExit", checkbox.isSelected() ? "true" : "false");
                
                String maxLogs = String.valueOf(logSpinner.getValue());
                int logs = 5;
                
                try
        		{
        			logs = Integer.valueOf(maxLogs);
        		}
        		catch (NumberFormatException e)
        		{
        			logs = 5;
        		}
                
                LauncherProperties.modifyProperty("automaticLogs", String.valueOf(logs));
                Launcher.openOrCloseOptionsFrame();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
                Launcher.openOrCloseOptionsFrame();
			}
		});

		loadComponentsProperties();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(22, 22, 22).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(launcherDirBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(gameDirBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(checkbox, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(logLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(paramLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(logSpinner).addComponent(paramSpinner)))).addGap(22, 22, 22)).addGroup(layout.createSequentialGroup().addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE).addGap(21, 21, 21)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(checkbox).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(logSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(logLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(paramLbl).addComponent(paramSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(gameDirBtn).addGap(18, 18, 18).addComponent(launcherDirBtn).addGap(18, 18, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveBtn).addComponent(cancelBtn)).addGap(18, 18, 18)));
	}

	private void loadComponentsProperties()
	{
		checkbox.setSelected(LauncherProperties.getProperty("openLauncherAfterExit").equals("true") ? true : false);

		int logs;
		String maxLogs = LauncherProperties.getProperty("automaticLogs");

		try
		{
			logs = Integer.valueOf(maxLogs);
		}
		catch (NumberFormatException e)
		{
			LauncherProperties.modifyProperty("automaticLogs", "5");
			logs = 5;
		}

		logSpinner.setValue(logs);
	}

	private JButton saveBtn;
	private JButton cancelBtn;
	private JButton gameDirBtn;
	private JButton launcherDirBtn;
	private JCheckBox checkbox;
	private JLabel logLbl;
	private JLabel paramLbl;
	private JSpinner logSpinner;
	private JTextField paramSpinner;
}
