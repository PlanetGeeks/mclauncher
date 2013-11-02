package it.planetgeeks.mclauncher.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MPInfoFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private ModPack modpack;
	
	public MPInfoFrame(ModPack modpack)
	{
		this.modpack = modpack;
		initComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
	}

	private void initComponents()
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);
	
		this.setTitle(LanguageUtils.getTranslated("launcher.modpacks.infoframe.title"));
		this.setResizable(false);
		
		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		
		detailsPanel = new JPanel();
		mpnameLbl = new JLabel();
		nameField = new JTextField();
		mpversionLbl = new JLabel();
		versionField = new JTextField();
		mpownerLbl = new JLabel();
		ownerField = new JTextField();
		hasServer = new JCheckBox();
		mpversionmcLbl = new JLabel();
		versionmcField = new JTextField();
		modsPanel = new JScrollPane();
		modList = new JList<String>();
		logoPanel = new JPanel();
		logo = new CustomJPanel(false, modpack.packImage);

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		detailsPanel.setBorder(BorderFactory.createTitledBorder(LanguageUtils.getTranslated("launcher.modpacks.infoframe.details")));
		detailsPanel.setOpaque(false);

		mpnameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mpnameLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.infoframe.name") + " :");

		mpversionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mpversionLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.infoframe.version") + " :");

		mpownerLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mpownerLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.infoframe.owner") + " :");

		hasServer.setText(LanguageUtils.getTranslated("launcher.modpacks.infoframe.hasserver"));
		hasServer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		hasServer.setHorizontalAlignment(SwingConstants.CENTER);

		mpversionmcLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mpversionmcLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.infoframe.versionmc"));

		GroupLayout detailsPanelLayout = new GroupLayout(detailsPanel);
		detailsPanel.setLayout(detailsPanelLayout);
		detailsPanelLayout.setHorizontalGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(detailsPanelLayout.createSequentialGroup().addContainerGap().addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hasServer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(detailsPanelLayout.createSequentialGroup().addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(mpversionmcLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(detailsPanelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpownerLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpversionLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpnameLbl, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(versionField).addComponent(ownerField, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE).addComponent(versionmcField).addComponent(nameField)))).addContainerGap()));
		detailsPanelLayout.setVerticalGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(detailsPanelLayout.createSequentialGroup().addContainerGap().addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpnameLbl).addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12).addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpversionLbl).addComponent(versionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12).addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpownerLbl).addComponent(ownerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12).addGroup(detailsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpversionmcLbl).addComponent(versionmcField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12).addComponent(hasServer).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		modsPanel.setBorder(BorderFactory.createTitledBorder(LanguageUtils.getTranslated("launcher.modpacks.infoframe.mods")));

		final String[] mods = new String[modpack.modList.size()];
		
		for(int i = 0; i < modpack.modList.size(); i++)
		{
			mods[i] = modpack.modList.get(i);
		}
		
		modList.setModel(new AbstractListModel<String>()
		{
			private static final long serialVersionUID = 1L;
			
			String[] strings = mods;
			
			public int getSize()
			{
				return strings.length;
			}

			public String getElementAt(int i)
			{
				return strings[i];
			}
		});
		
		nameField.setText(modpack.packName);
		nameField.setEditable(false);
		versionmcField.setText(modpack.mcVersion);
		versionmcField.setEditable(false);
		ownerField.setText(modpack.packOwner);
		ownerField.setEditable(false);
		hasServer.setSelected(modpack.packServerLink != null && DesktopUtils.checkLink(modpack.packServerLink) ? true : false);
		hasServer.setFocusable(false);
		versionField.setEditable(false);
		
		hasServer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				hasServer.setSelected(modpack.packServerLink != null && DesktopUtils.checkLink(modpack.packServerLink) ? true : false);	
			}			
		});
		
		
		modList.setOpaque(false);
		modList.setBackground(new Color(0,0,0,0));
		modsPanel.setViewportView(modList);
        modsPanel.getViewport().setOpaque(false);
        modList.setBorder(null);
        modsPanel.setOpaque(false);
        modsPanel.setViewportBorder(null);
        
		logoPanel.setBorder(BorderFactory.createTitledBorder(LanguageUtils.getTranslated("launcher.modpacks.infoframe.logo")));
		logoPanel.setOpaque(false);

		logo.setBackground(new java.awt.Color(204, 204, 204));

		GroupLayout logoLayout = new GroupLayout(logo);
		logo.setLayout(logoLayout);
		logoLayout.setHorizontalGroup(logoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 245, Short.MAX_VALUE));
		logoLayout.setVerticalGroup(logoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 53, Short.MAX_VALUE));

		GroupLayout logoPanelLayout = new GroupLayout(logoPanel);
		logoPanel.setLayout(logoPanelLayout);
		logoPanelLayout.setHorizontalGroup(logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(logoPanelLayout.createSequentialGroup().addGap(27, 27, 27).addComponent(logo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(28, Short.MAX_VALUE)));
		logoPanelLayout.setVerticalGroup(logoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(logo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(detailsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(logoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(modsPanel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(modsPanel).addGroup(layout.createSequentialGroup().addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(11, 11, 11).addComponent(detailsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))).addGap(6, 6, 6)));

		pack();
	}

	private JCheckBox hasServer;
	private JLabel mpnameLbl;
	private JLabel mpversionLbl;
	private JLabel mpownerLbl;
	private JLabel mpversionmcLbl;
	private JList<String> modList;
	private JPanel detailsPanel;
	private JPanel logoPanel;
	private JPanel logo;
	private JScrollPane modsPanel;
	private JTextField nameField;
	private JTextField versionField;
	private JTextField ownerField;
	private JTextField versionmcField;
}
