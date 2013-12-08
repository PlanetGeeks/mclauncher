package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.GameLauncher;
import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherLogger;
import it.planetgeeks.mclauncher.LauncherProperties;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.frames.utils.LinkLabel;
import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.LoginUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 * @author PlanetGeeks
 * 
 */

public class SouthPanel extends CustomJPanel
{
	private static final long serialVersionUID = 1L;

	public SouthPanel()
	{
		super(true, "southBar.png");
		initComponents();
	}

	public void loadTranslations()
	{
		((LinkLabel) linkLbl).setTextLink(LanguageUtils.getTranslated("launcher.websitelink"), Settings.websiteLink);
		launchButton.setText(LanguageUtils.getTranslated("launcher.connectbtn"));
		profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">") ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
		profileLbl.setText(LanguageUtils.getTranslated("launcher.profilelbl") + " :");
		setComboboxProfiles(ProfilesUtils.getSelectedProfile() != null ? ProfilesUtils.getSelectedProfile().profileName : null);
	}

	public void initComponents()
	{
		logo = new JLabel();
		logo.setIcon(Launcher.getResources().getResource("logo.png"));

		controlsPanel = new CustomJPanel(false, "controlsBg.png");
		controlsPanel.setRightAlign();
		profileButton = new JButton();
		profileButton.setForeground(new Color(Settings.buttonsForeground));
		profileComboBox = new JComboBox<String>();
		profileLbl = new JLabel();
		profileLbl.setForeground(new Color(Settings.southBarLabelsForeground));
		launchButton = new JButton();
		launchButton.setForeground(new Color(Settings.buttonsForeground));
		linkLbl = new LinkLabel(LanguageUtils.getTranslated("launcher.websitelink"), Settings.websiteLink);
		linkLbl.setForeground(new Color(Settings.southBarLabelsForeground));
		logo.setBackground(new Color(0, 0, 0, 0));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logo.setOpaque(false);
		setBackground(new Color(153, 153, 153));
		controlsPanel.setBackground(new Color(153, 153, 153, 0));
		controlsPanel.setOpaque(false);
		profileLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		profileLbl.setForeground(new Color(Settings.southBarLabelsForeground));
		linkLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalBar = new JProgressBar();
		totalLbl = new JLabel();
		currentBar = new JProgressBar();
		currentLbl = new JLabel();
		stopLbl = new JLabel();
		pauseLbl = new JLabel();
		totalLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.downloadingMap"));
		totalLbl.setForeground(new Color(Settings.southBarLabelsForeground));
		currentLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentLbl.setForeground(new Color(Settings.southBarLabelsForeground));
		currentLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.downloadingMap"));
		stopLbl.setIcon(Launcher.getResources().getResource("stop.png"));
		pauseLbl.setIcon(Launcher.getResources().getResource("pause.png"));
		launchButton.setEnabled(false);
		launchButton.setText(LanguageUtils.getTranslated("launcher.connectbtn"));
		stopLbl.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				ModPackUtils.updateStopped = true;
			}

			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		pauseLbl.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				ModPackUtils.updatePaused = !ModPackUtils.updatePaused;
				pauseLbl.setIcon(Launcher.getResources().getResource(ModPackUtils.updatePaused ? "play.png" : "pause.png"));
			}

			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		setComboboxProfiles(null);

		launchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				connectionBtnClicked();
			}
		});

		logo.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me)
			{
				DesktopUtils.openWebPage(Settings.websiteLink);
			}

			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		profileComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String s = (String) profileComboBox.getSelectedItem();

				ProfilesUtils.selectedProfile = profileComboBox.getSelectedIndex();

				if (s.equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">"))
				{
					Launcher.openProfileEditor(null);
				}
				else
				{
					Launcher.getLauncherFrame().mainPanel.updateSkin();
				}

				profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">") ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
			}
		});

		profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">") ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));

		profileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (String.valueOf(profileComboBox.getSelectedItem()).equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">"))
				{
					Launcher.openProfileEditor(null);
				}
				else
				{
					String profileName = (String) profileComboBox.getSelectedItem();
					Profile profile = ProfilesUtils.getProfile(profileName);
					Launcher.openProfileEditor(profile);
				}
			}
		});

		profileLbl.setText(LanguageUtils.getTranslated("launcher.profilelbl") + " :");

		GroupLayout controlsPanelLayout = new GroupLayout(controlsPanel);
		controlsPanel.setLayout(controlsPanelLayout);
		controlsPanelLayout.setHorizontalGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(controlsPanelLayout.createSequentialGroup().addContainerGap().addComponent(profileLbl, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup().addComponent(profileComboBox, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(profileButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)).addGroup(GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup().addComponent(linkLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		controlsPanelLayout.setVerticalGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(controlsPanelLayout.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE).addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(profileButton).addComponent(profileComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(profileLbl)).addGap(18, 18, 18).addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(launchButton).addComponent(linkLbl)).addGap(17, 17, 17)));

		setUpdating(false);
	}

	public void setUpdating(boolean update)
	{
		this.removeAll();
		if (update)
		{
			GroupLayout layout = new GroupLayout(this);
			setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(totalBar, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE).addComponent(totalLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(currentBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(currentLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(pauseLbl, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(stopLbl, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(stopLbl).addComponent(pauseLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(currentLbl).addGap(0, 0, 0).addComponent(currentBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(totalLbl).addGap(0, 0, 0).addComponent(totalBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		}
		else
		{
			GroupLayout footerPanelLayout = new GroupLayout(this);
			setLayout(footerPanelLayout);
			footerPanelLayout.setHorizontalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup().addContainerGap().addComponent(logo, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			footerPanelLayout.setVerticalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup().addContainerGap().addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()).addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		}

	}

	public void setComboboxProfiles(String selected)
	{
		ArrayList<String> profileList = new ArrayList<String>();
		for (Profile profile : ProfilesUtils.getProfiles())
		{
			profileList.add(profile.profileName);
		}
		profileList.add("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">");
		String[] str = new String[profileList.size()];
		for (int i = 0; i < str.length; i++)
		{
			str[i] = profileList.get(i);
		}
		profileComboBox.setModel(new DefaultComboBoxModel<String>(str));

		if (selected != null)
		{
			profileComboBox.setSelectedItem(selected);
		}

		profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">") ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));

		if (Launcher.getLauncherFrame() != null)
		{
			Launcher.getLauncherFrame().mainPanel.updateSkin();
		}
	}

	private void connectionBtnClicked()
	{
		if (profileComboBox.getSelectedItem().equals("<" + LanguageUtils.getTranslated("launcher.profile.combobox.create") + ">"))
		{
			JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.login.selectprofile"), LanguageUtils.getTranslated("launcher.login.title"), JOptionPane.ERROR_MESSAGE);

			return;
		}

		Profile profile = ProfilesUtils.getSelectedProfile();

		String error = "";

		boolean playOffline = true;

		if (profile.rememberPsw)
		{
			String result = LoginUtils.login(profile.email != null && !profile.email.equals("") ? profile.email : profile.username, profile.password);

			if (result.contains(":"))
			{
				playOffline = false;
				String[] spt = result.split(":");
				profile.minecraftName = spt[0];
				profile.sessionID = "token:" + spt[1] + ":" + spt[2];
			}
			else
			{
				if (result.equals("NOCONNECTION"))
				{
					error = LanguageUtils.getTranslated("launcher.login.noconnection");
				}
				else if (result.equals("LOGINFAILED"))
				{
					error = LanguageUtils.getTranslated("launcher.login.badlogin");
				}
				else if (result.equals("ERROR"))
				{
					error = LanguageUtils.getTranslated("launcher.login.error");
				}

				error += " ";
			}
		}

		if (playOffline && LauncherProperties.getProperty("hideofflinemessage").equals("false"))
		{
			JCheckBox checkbox = new JCheckBox(LanguageUtils.getTranslated("launcher.login.playofflinebox"));
			Object[] m = new Object[] { error + LanguageUtils.getTranslated("launcher.login.playoffline"), checkbox };
			int result = JOptionPane.showConfirmDialog(null, m, LanguageUtils.getTranslated("launcher.login.title"), JOptionPane.YES_NO_OPTION);
			if (checkbox.isSelected())
			{
				LauncherProperties.modifyProperty("hideofflinemessage", "true");
			}
			if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.NO_OPTION)
			{
				return;
			}

		}

		ModPack selected = ModPackUtils.selected;

		if (selected != null)
		{
			if (FileUtils.internetConnected(selected.setupLink))
			{
				ModPackUtils.setupModPack(selected);
			}
			else
			{
				if (selected.getModPackDir().exists())
				{
					GameLauncher.launchGame();
				}
				else
				{
					JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.login.noconnection.modpack"), LanguageUtils.getTranslated("launcher.login.warning"), JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.login.selectModpack"), LanguageUtils.getTranslated("launcher.login.selectModpack.title"), JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	public void updateStatus(int status, String pathUrl, int current, int index, int max)
	{
		if (status >= 0)
		{
			if (status == 1)
			{
				String str = new String(pathUrl);

				if (str.length() > 41)
				{
					str = pathUrl.substring(0, 19) + "..." + pathUrl.substring(pathUrl.length() - 19, pathUrl.length());
				}
				this.currentLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.downloading") + " - " + str + " - " + current + "%");
				this.currentBar.setValue(current);
				this.currentBar.setIndeterminate(false);
			}
			else
			{
				String str = new String(pathUrl);

				if (str.length() > 41)
				{
					str = pathUrl.substring(0, 19) + "..." + pathUrl.substring(pathUrl.length() - 19, pathUrl.length());
				}
				this.currentLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.checking") + " - " + str);
				this.currentBar.setValue(100);
				this.currentBar.setIndeterminate(true);
			}

			this.totalLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.total") + " - " + (index * 100) / max + "%");
			this.totalBar.setValue((index * 100) / max);
			this.totalBar.setIndeterminate(false);
		}
		else
		{
			this.totalLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.update.downloadingMap"));
			this.totalBar.setValue(100);
			this.totalBar.setIndeterminate(true);
		}
	}

	public boolean setDownloadingFile(String pathUrl, File dest, int index, int max)
	{
		try
		{
			dest.getParentFile().mkdirs();
			dest.createNewFile();
			dest.setWritable(true);
			URL url = new URL(pathUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			float totalDataRead = 0;
			BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(dest);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int i = 0;
			int filesize = connection.getContentLength();
			boolean notBreaked = true;

			while ((i = in.read(data, 0, 1024)) >= 0)
			{
				totalDataRead = totalDataRead + i;
				bout.write(data, 0, i);
				float perc = (totalDataRead * 100) / filesize;
				updateStatus(1, pathUrl, (int) perc, index, max);
				Thread.sleep(1);
				if (ModPackUtils.updateStopped || ModPackUtils.updatePaused)
				{
					notBreaked = false;
					break;
				}
			}
			bout.close();
			fos.close();
			in.close();
			connection.disconnect();

			return notBreaked;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on downloading file " + pathUrl);
		}

		return false;
	}

	public void setLaunchActive()
	{
		this.launchButton.setEnabled(true);
	}

	private CustomJPanel controlsPanel;
	private JButton profileButton;
	private JButton launchButton;
	private JComboBox<String> profileComboBox;
	private JLabel logo;
	private JLabel profileLbl;
	private JLabel linkLbl;
	private JLabel totalLbl;
	private JLabel currentLbl;
	private JLabel stopLbl;
	private JLabel pauseLbl;
	private JProgressBar totalBar;
	private JProgressBar currentBar;
}
