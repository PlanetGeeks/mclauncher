package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.frames.utils.CustomMouseListener;
import it.planetgeeks.mclauncher.frames.utils.HintTextField;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @author PlanetGeeks
 * 
 */

public class ProfileFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private Profile profile;
	private String latestProfName;

	public ProfileFrame(Profile profile)
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);
		this.profile = profile;
		this.setResizable(false);
		initComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
	}

	private void initComponents()
	{
		setIconImage(Launcher.getResources().getResource("icon.png").getImage());

		usernameLbl = new JLabel();
		usernameLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		usernameField = new JTextField();
		passwordLbl = new JLabel();
		passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		passwordField = new JPasswordField();
		comboBoxRam = new JComboBox<Object>();
		ramLbl = new JLabel();
		ramLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		checkBoxPsw = new JCheckBox();
		checkBoxPsw.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		checkBoxPsw.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		profileNameField = new JTextField();
		profileNameLbl = new JLabel();
		profileNameLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		emailLbl = new JLabel();
		emailLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		emailField = new HintTextField(LanguageUtils.getTranslated("launcher.profile.emailtip"));
		cancelBtn = new JButton();
		cancelBtn.setForeground(new Color(Settings.buttonsForeground));
		createBtn = new JButton();
		createBtn.setForeground(new Color(Settings.buttonsForeground));
		emailField.setMaximumSize(new Dimension(150, 28));
		usernameField.setMaximumSize(new Dimension(150, 28));
		passwordField.setMaximumSize(new Dimension(145, 28));
		profileNameField.setMaximumSize(new Dimension(150, 28));
		comboBoxRam.setMaximumSize(new Dimension(145, 28));
		emailField.setMinimumSize(new Dimension(145, 28));
		usernameField.setMinimumSize(new Dimension(145, 28));
		passwordField.setMinimumSize(new Dimension(145, 28));
		profileNameField.setMinimumSize(new Dimension(145, 28));
		comboBoxRam.setMinimumSize(new Dimension(145, 28));

		if (profile == null)
			cancelBtn.setEnabled(false);

		usernameField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
				profileNameField.setText(usernameField.getText());
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{
			}
		});

		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				onButtonClicked(0);
			}
		});

		createBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				onButtonClicked(profile == null ? 2 : 1);
			}
		});

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		checkBoxPsw.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (checkBoxPsw.isSelected())
				{
					passwordField.setEnabled(true);
				}
				else
				{
					passwordField.setEnabled(false);
				}
			}
		});

		setComboboxRam();

		comboBoxRam.addMouseListener(new CustomMouseListener()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (String.valueOf(comboBoxRam.getSelectedItem()).equals("< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >"))
				{
					Launcher.openOrCloseMemoryEditor(1, profile);
					setVisible(false);
				}
			}
		});

		setLayout();

		pack();

		passwordField.setEnabled(checkBoxPsw.isSelected() ? true : false);

		if (profile != null)
		{
			this.latestProfName = profile.profileName;
			usernameField.setText(profile.username);
			passwordField.setText(profile.password);
			if (profile.email != null && !profile.email.equals(""))
				emailField.setText(profile.email);
			profileNameField.setText(profile.profileName);
			comboBoxRam.setSelectedItem(profile.ram);
			if (!comboBoxRam.getSelectedItem().equals(profile.ram))
			{
				if (comboBoxRam.getItemAt(1) != null)
				{
					comboBoxRam.setSelectedIndex(1);
				}
			}
			checkBoxPsw.setSelected(profile.rememberPsw);
		}

		loadTranslations();

	}

	private void onButtonClicked(int btn)
	{
		switch (btn)
		{
			case 0:
			{
				if (profile != null)
				{
					ProfilesUtils.deleteProfile(this.latestProfName, true);
					profile = null;
					this.setVisible(false);
					Launcher.getLauncherFrame().southPanel.setComboboxProfiles(null);
				}
				break;
			}

			case 1:
			{
				if (profile != null)
				{
					if (checkInsertedData())
					{
						profile.username = usernameField.getText();
						profile.password = String.valueOf(passwordField.getPassword());
						profile.email = emailField.getText();
						if (!String.valueOf(comboBoxRam.getSelectedItem()).equals("< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >"))
						{
							profile.ram = (String) comboBoxRam.getSelectedItem();
						}
						else
						{
							profile.ram = "null";
						}
						profile.rememberPsw = checkBoxPsw.isSelected();
						ProfilesUtils.modifyProfile(this.latestProfName, profileNameField.getText(), profile);
						profile = null;
						this.setVisible(false);
						Launcher.getLauncherFrame().southPanel.setComboboxProfiles(ProfilesUtils.getSelectedProfile().profileName);
					}
				}
				break;
			}

			case 2:
			{
				if (checkInsertedData())
				{
					if (!ProfilesUtils.createProfile(new Profile(usernameField.getText(), String.valueOf(passwordField.getPassword()), comboBoxRam.getSelectedItem().toString(), profileNameField.getText(), checkBoxPsw.isSelected())))
					{
						JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.profile.profilenotcreate.message"), LanguageUtils.getTranslated("launcher.profile.profilenotcreate.title"), JOptionPane.OK_CANCEL_OPTION);
					}
					else
					{
						profile = null;
						this.setVisible(false);
						Launcher.getLauncherFrame().southPanel.setComboboxProfiles(ProfilesUtils.getSelectedProfile().profileName);
					}
				}
				break;
			}
		}
	}

	private boolean checkInsertedData()
	{
		boolean create = true;
		if (!(this.usernameField.getText().length() > 0))
		{
			this.usernameLbl.setForeground(new Color(Settings.colorLabelErrored));
			create = false;
		}
		else
		{
			this.usernameLbl.setForeground(new Color(Settings.textDefault));
		}

		if (this.checkBoxPsw.isSelected() && !(this.passwordField.getPassword().length > 0))
		{
			this.passwordLbl.setForeground(new Color(Settings.colorLabelErrored));
			create = false;
		}
		else
		{
			this.passwordLbl.setForeground(new Color(Settings.textDefault));
		}

		if (!(this.profileNameField.getText().length() > 0))
		{
			this.profileNameLbl.setForeground(new Color(Settings.colorLabelErrored));
			create = false;
		}
		else
		{
			this.profileNameLbl.setForeground(new Color(Settings.textDefault));
		}

		if (create)
		{
			return true;
		}
		return false;
	}

	private void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(checkBoxPsw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(ramLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE).addComponent(profileNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(passwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(emailLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(usernameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE).addComponent(usernameField).addComponent(comboBoxRam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(profileNameField).addComponent(emailField))))).addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(23, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(29, 29, 29).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(usernameLbl).addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(emailLbl).addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(passwordLbl).addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(profileNameLbl).addComponent(profileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(ramLbl).addComponent(comboBoxRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(checkBoxPsw).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(createBtn).addComponent(cancelBtn)).addContainerGap(25, Short.MAX_VALUE)));
	}

	private void setComboboxRam()
	{
		String[] items = MemoryUtils.getMemoryNames();
		String[] withCreateItems = new String[items.length + 1];
		for (int i = 0; i < items.length; i++)
		{
			withCreateItems[i + 1] = items[i];
		}

		withCreateItems[0] = "< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >";
		comboBoxRam.setModel(new DefaultComboBoxModel<Object>(withCreateItems));
		comboBoxRam.setSelectedIndex(1);
	}

	public void loadTranslations()
	{
		this.setTitle(LanguageUtils.getTranslated("launcher.profile.title"));

		usernameLbl.setText(LanguageUtils.getTranslated("launcher.profile.usernamelbl") + " :");

		passwordLbl.setText(LanguageUtils.getTranslated("launcher.profile.passwordlbl") + " :");

		ramLbl.setText(LanguageUtils.getTranslated("launcher.profile.ramlbl") + " :");

		checkBoxPsw.setText(LanguageUtils.getTranslated("launcher.profile.rememberPsw"));

		createBtn.setText(LanguageUtils.getTranslated(profile == null ? "launcher.profile.create" : "launcher.profile.modify"));

		cancelBtn.setText(LanguageUtils.getTranslated("launcher.profile.delete"));

		profileNameLbl.setText(LanguageUtils.getTranslated("launcher.profile.profilenamelbl") + " :");

		emailField.setTip(LanguageUtils.getTranslated("launcher.profile.emailtip"));

		emailLbl.setText(LanguageUtils.getTranslated("launcher.profile.email") + " :");
	}

	private JButton createBtn;
	private JCheckBox checkBoxPsw;
	private JComboBox<Object> comboBoxRam;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JLabel ramLbl;
	private JLabel profileNameLbl;
	private JLabel emailLbl;
	private HintTextField emailField;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JTextField profileNameField;
	private JButton cancelBtn;
}
