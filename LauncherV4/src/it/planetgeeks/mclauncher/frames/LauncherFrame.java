package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * 
 * @author PlanetGeeks
 **/

public class LauncherFrame extends JFrame
{

	public static ArrayList<JButton> skinsButton = new ArrayList<JButton>();
	private static final long serialVersionUID = 1L;
	private static int skinMode = 0;

	public LauncherFrame()
	{
		initComponents();
		// SkinsManager.drawCharacter((JPanel) getContentPane(),
		// (ActionListener)this,
		// "https://dl.dropboxusercontent.com/u/88221856/skin/Flood.png", null,
		// 108, 50, skinsButton, skinMode, false);
		validate();
		repaint();
		JEditorPane panel = new JEditorPane();
		try
		{
			panel.setPage("http://mcupdate.tumblr.com/");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.scrollPane1.setViewportView(panel);
		this.scrollPane2.setViewportView(panel);
		this.scrollPane3.setViewportView(panel);
	}

	private void initComponents()
	{

		this.setTitle(LanguageUtils.getTranslated("launcher.title"));
        
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		connectBtn = new JButton();
		comboBoxProfile = new JComboBox();
		profilesBtn = new JButton();
		profileLabel = new JLabel();
		comboBoxRam = new JComboBox();
		memoryLabel = new JLabel();
		websiteLabel = new JLabel();
		tabbedPane = new JTabbedPane();
		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();
		scrollPane3 = new JScrollPane();
		skinRightBtn = new JButton();

		skinRightBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (skinMode < 3)
				{
					skinMode++;
				}
				else
				{
					skinMode = 0;
				}
				refreshSkin();
			}

		});
		skinLeftBtn = new JButton();
		skinLeftBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (skinMode > 0)
				{
					skinMode--;
				}
				else
				{
					skinMode = 3;
				}
				refreshSkin();
			}

		});
		menuBar = new JMenuBar();
		menu1 = new JMenu();
		menu2 = new JMenu();
		
		setComboboxRam();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new java.awt.Dimension(840, 530));
		setMinimumSize(new java.awt.Dimension(840, 530));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - 840/2, screenSize.height / 2 - 530/2);
		jPanel1.setBackground(new java.awt.Color(153, 153, 153));

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		connectBtn.setText("Connettiti");

		connectBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}

		});

		setComboboxProfiles();

		comboBoxProfile.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
                String s = (String) comboBoxProfile.getSelectedItem();
                if(s.equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")))
                {
                	Launcher.openProfileEditor(null);
                }
                profilesBtn.setText(String.valueOf(comboBoxProfile.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
			}
		});
		
		comboBoxRam.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
                MemoryUtils.setCurrentMem((String)comboBoxRam.getSelectedItem());
			}
		});

		profilesBtn.setText(String.valueOf(comboBoxProfile.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
		
		profilesBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(String.valueOf(comboBoxProfile.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")))
				{
					Launcher.openProfileEditor(null);
				}
				else
				{
				    String profileName = (String)comboBoxProfile.getSelectedItem();
				    Profile profile = ProfilesUtils.getProfile(profileName);
				    Launcher.openProfileEditor(profile);
				}
			}	
		});

		profileLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		profileLabel.setText(LanguageUtils.getTranslated("launcher.profilelbl") + " :");

		memoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		memoryLabel.setText(LanguageUtils.getTranslated("launcher.memorylbl") + " :");

		websiteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		websiteLabel.setText(LanguageUtils.getTranslated("launcher.websitelink"));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(profileLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(memoryLabel, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(comboBoxProfile, 0, 130, Short.MAX_VALUE).addComponent(comboBoxRam, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(websiteLabel, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(connectBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE).addComponent(profilesBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(comboBoxProfile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(profileLabel)).addComponent(profilesBtn)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(connectBtn).addComponent(comboBoxRam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(memoryLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(websiteLabel).addGap(0, 14, Short.MAX_VALUE))).addContainerGap()));

		tabbedPane.addTab("tab1", scrollPane1);
		tabbedPane.addTab("tab2", scrollPane2);
		tabbedPane.addTab("tab3", scrollPane3);

		skinRightBtn.setText(">");

		skinLeftBtn.setText("<");

		menu1.setText("File");
		menuBar.add(menu1);

		menu2.setText("Edit");
		menuBar.add(menu2);

		setJMenuBar(menuBar);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(18, 18, 18).addComponent(skinLeftBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE).addComponent(skinRightBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(skinRightBtn).addComponent(skinLeftBtn)).addGap(32, 32, 32))).addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		pack();
	}

	public void setComboboxProfiles()
	{
		ArrayList<String> profileList = new ArrayList<String>();
		for (Profile profile : ProfilesUtils.getProfiles())
		{
			profileList.add(profile.profileName);
		}
		profileList.add(LanguageUtils.getTranslated("launcher.profile.combobox.create"));
		String[] str = new String[profileList.size()];
		for (int i = 0; i < str.length; i++)
		{
			str[i] = profileList.get(i);
		}
		comboBoxProfile.setModel(new DefaultComboBoxModel(str));
		profilesBtn.setText(String.valueOf(comboBoxProfile.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
	}
	
	public void setComboboxRam()
	{
		comboBoxRam.setModel(new DefaultComboBoxModel(MemoryUtils.getMemoryNames()));
	}

	private void refreshSkin()
	{
		for (int i = 0; i < skinsButton.size(); i++)
		{
			getContentPane().remove(skinsButton.get(i));
		}
		// SkinsManager.drawCharacter((JPanel) getContentPane(),
		// (ActionListener)this,
		// "https://dl.dropboxusercontent.com/u/88221856/skin/Flood.png", null,
		// 108, 50, skinsButton, skinMode, false);
		validate();
		repaint();
	}

	private JButton connectBtn;
	private JButton profilesBtn;
	private JButton skinRightBtn;
	private JButton skinLeftBtn;
	private JComboBox comboBoxProfile;
	private JComboBox comboBoxRam;
	private JLabel profileLabel;
	private JLabel memoryLabel;
	private JLabel websiteLabel;
	private JMenu menu1;
	private JMenu menu2;
	private JMenuBar menuBar;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JTabbedPane tabbedPane;
}
