package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.Profile;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class LoginPanel extends CustomJPanel
{
	private static final long serialVersionUID = 1L;
	
	public LoginPanel()
    {
		super(true, "southBar.png");
		initComponents();
    }
    
    public void initComponents()
    {
    	logo = new JLabel();
    	logo.setIcon(Launcher.resources.getResource("logo.png"));
    	
		controlsPanel = new CustomJPanel(false, "controlsBg.png");
		controlsPanel.setRightAlign();
		profileButton = new JButton();
		profileComboBox = new JComboBox();
		profileLbl = new JLabel();
		launchButton = new JButton();
		linkLbl = new JLabel();
		logo.setBackground(new Color(0,0,0,0));
		logo.setHorizontalAlignment(SwingConstants.LEFT);
		logo.setOpaque(false);
		setBackground(new Color(153, 153, 153));
        controlsPanel.setBackground(new Color(153, 153, 153, 0));
        controlsPanel.setOpaque(false);
		profileLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		launchButton.setText(LanguageUtils.getTranslated("launcher.connectbtn"));
		linkLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		linkLbl.setText(LanguageUtils.getTranslated("launcher.websitelink"));
		
		setComboboxProfiles();
		
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
				if (s.equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")))
				{
					Launcher.openProfileEditor(null);
				}
				profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
			}
		});
		
		profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));

		profileButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (String.valueOf(profileComboBox.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")))
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
	
		GroupLayout footerPanelLayout = new GroupLayout(this);
		setLayout(footerPanelLayout);
		footerPanelLayout.setHorizontalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(footerPanelLayout.createSequentialGroup().addContainerGap().addComponent(logo, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		footerPanelLayout.setVerticalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup().addContainerGap().addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()).addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
    
   
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
		profileComboBox.setModel(new DefaultComboBoxModel(str));
		profileButton.setText(String.valueOf(profileComboBox.getSelectedItem()).equals(LanguageUtils.getTranslated("launcher.profile.combobox.create")) ? LanguageUtils.getTranslated("launcher.createprofilebtn") : LanguageUtils.getTranslated("launcher.modifyprofilebtn"));
	}
    
    private CustomJPanel controlsPanel;
    private JButton profileButton;
	private JButton launchButton;
	private JComboBox profileComboBox;
	private JLabel logo;
	private JLabel profileLbl;
	private JLabel linkLbl;
}
