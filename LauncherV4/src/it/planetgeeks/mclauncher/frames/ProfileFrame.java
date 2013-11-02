package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.frames.utils.CustomMouseListener;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

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
		usernameField = new JTextField();
		passwordLbl = new JLabel();
		passwordField = new JPasswordField();
		comboBoxRam = new JComboBox<Object>();
		ramLbl = new JLabel();
		checkBoxPsw = new JCheckBox();
		profileNameField = new JTextField();
		profileNameLbl = new JLabel();
		cancelBtn = new JButton();
		cancelBtn.setForeground(new Color(Settings.buttonsForeground));
		modifyBtn = new JButton();
		modifyBtn.setForeground(new Color(Settings.buttonsForeground));
		createBtn = new JButton();
		createBtn.setForeground(new Color(Settings.buttonsForeground));
	    this.setTitle(LanguageUtils.getTranslated("launcher.profile.title"));

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

		modifyBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				onButtonClicked(1);
			}
		});

		createBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				onButtonClicked(2);
			}
		});

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		usernameLbl.setText(LanguageUtils.getTranslated("launcher.profile.usernamelbl") + " :");

		passwordLbl.setText(LanguageUtils.getTranslated("launcher.profile.passwordlbl") + " :");

		ramLbl.setText(LanguageUtils.getTranslated("launcher.profile.ramlbl") + " :");

		checkBoxPsw.setText(LanguageUtils.getTranslated("launcher.profile.rememberPsw"));

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

		createBtn.setText(LanguageUtils.getTranslated("launcher.profile.create"));

		cancelBtn.setText(LanguageUtils.getTranslated("launcher.profile.delete"));

		setComboboxRam();
		
		comboBoxRam.addMouseListener(new CustomMouseListener()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(String.valueOf(comboBoxRam.getSelectedItem()).equals("< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >"))
                {
                	Launcher.openOrCloseMemoryEditor(1, profile);
                	setVisible(false);
                }
			}
		});
		
		profileNameLbl.setText(LanguageUtils.getTranslated("launcher.profile.profilenamelbl") + " :");

		modifyBtn.setText(LanguageUtils.getTranslated("launcher.profile.modify"));

		if (profile != null)
		{
			this.latestProfName = profile.profileName;
			usernameField.setText(profile.username);
			passwordField.setText(profile.password);
			profileNameField.setText(profile.profileName);
		    comboBoxRam.setSelectedItem(profile.ram);
		    if(!comboBoxRam.getSelectedItem().equals(profile.ram))
		    {
		    	if(comboBoxRam.getItemAt(1) != null)
		    	{
		    		comboBoxRam.setSelectedIndex(1);
		    	}
		    }
			checkBoxPsw.setSelected(profile.rememberPsw);
			setModifyLayout();
		}
		else
		{
			setCreateLayout();
		}

		passwordField.setEnabled(checkBoxPsw.isSelected() ? true : false);

		pack();
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
					Launcher.getLauncherFrame().loginPanel.setComboboxProfiles(null);
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
						if(!String.valueOf(comboBoxRam.getSelectedItem()).equals("< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >"))
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
						Launcher.getLauncherFrame().loginPanel.setComboboxProfiles(ProfilesUtils.getSelectedProfile().profileName);
					}
				}
				break;
			}

			case 2:
			{
				if (checkInsertedData())
				{
					if(!ProfilesUtils.createProfile(new Profile(usernameField.getText(), String.valueOf(passwordField.getPassword()), comboBoxRam.getSelectedItem().toString(), profileNameField.getText(), checkBoxPsw.isSelected())))
					{
				        JOptionPane.showMessageDialog(null, LanguageUtils.getTranslated("launcher.profile.profilenotcreate.message"), LanguageUtils.getTranslated("launcher.profile.profilenotcreate.title"), JOptionPane.OK_CANCEL_OPTION);
					}
					else
					{
						profile = null;
						this.setVisible(false);
						Launcher.getLauncherFrame().loginPanel.setComboboxProfiles(ProfilesUtils.getSelectedProfile().profileName);
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
			this.usernameLbl.setForeground(new Color(Settings.colorProfileFrameLabelErrored));
			create = false;
		}
		else
		{
			this.usernameLbl.setForeground(new Color(Settings.colorProfileFrameLabel));
		}
		
		if (this.checkBoxPsw.isSelected() && !(this.passwordField.getPassword().length > 0))
		{
			this.passwordLbl.setForeground(new Color(Settings.colorProfileFrameLabelErrored));
			create = false;
		}
		else
		{
			this.passwordLbl.setForeground(new Color(Settings.colorProfileFrameLabel));
		}
		
		if (!(this.profileNameField.getText().length() > 0))
		{
			this.profileNameLbl.setForeground(new Color(Settings.colorProfileFrameLabelErrored));
			create = false;
		}
		else
		{
			this.profileNameLbl.setForeground(new Color(Settings.colorProfileFrameLabel));
		}
		
		if (create)
		{
			return true;
		}
		return false;
	}

	private void setCreateLayout()
	{
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(passwordLbl).addComponent(usernameLbl).addComponent(ramLbl).addComponent(profileNameLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(checkBoxPsw).addComponent(comboBoxRam, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(usernameField).addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE).addComponent(profileNameField, GroupLayout.Alignment.TRAILING)).addContainerGap(28, Short.MAX_VALUE)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE).addGap(80, 80, 80)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(usernameLbl).addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordLbl).addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(profileNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(profileNameLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(comboBoxRam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(ramLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(checkBoxPsw).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE).addComponent(createBtn).addGap(14, 14, 14)));
	}

	private void setModifyLayout()
	{
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(passwordLbl).addComponent(usernameLbl).addComponent(ramLbl).addComponent(profileNameLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(checkBoxPsw).addComponent(comboBoxRam, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(usernameField).addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE).addComponent(profileNameField, GroupLayout.Alignment.TRAILING)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap(16, Short.MAX_VALUE).addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(modifyBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(28, 28, 28).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(usernameLbl).addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(passwordLbl).addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(profileNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(profileNameLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(comboBoxRam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(ramLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(checkBoxPsw).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(cancelBtn).addComponent(modifyBtn)).addGap(14, 14, 14)));
	}

	private void setComboboxRam()
	{
		
		String[] items = MemoryUtils.getMemoryNames();
		String[] withCreateItems = new String[items.length + 1];
		for(int i = 0; i < items.length; i++)
		{
			withCreateItems[i + 1] = items[i];
		}
		
		withCreateItems[0] = "< " + LanguageUtils.getTranslated("launcher.memorybox.createMem") + " >";
		comboBoxRam.setModel(new DefaultComboBoxModel<Object>(withCreateItems));
		comboBoxRam.setSelectedIndex(1);
	}
	
	private JButton createBtn;
	private JCheckBox checkBoxPsw;
	private JComboBox<Object> comboBoxRam;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JLabel ramLbl;
	private JLabel profileNameLbl;
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JTextField profileNameField;
	private JButton cancelBtn;
	private JButton modifyBtn;
}
