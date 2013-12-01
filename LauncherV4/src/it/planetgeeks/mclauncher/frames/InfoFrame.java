package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.frames.utils.LinkLabel;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 * @author PlanetGeeks
 *
 */

public class InfoFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public InfoFrame()
	{
		initComponents();
	}

	private void initComponents()
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);
		
		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		
		this.setSize(300, 320);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
		this.setTitle(LanguageUtils.getTranslated("launcher.bar.info.info"));
		separator = new JSeparator();

		creditsLabels = new JLabel[2];
		
		infoLabels = new JLabel[7];
		
		for (int i = 0; i < 2; i++)
		{
			creditsLabels[i] = new JLabel();
			creditsLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
		}

		for (int i = 0; i < 7; i++)
		{
			infoLabels[i] = new JLabel();
			infoLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
		}

		creditsLabels[0].setText(LanguageUtils.getTranslated("launcher.info.credits"));
		creditsLabels[1] = new LinkLabel(LanguageUtils.getTranslated("launcher.info.creditslink"),LanguageUtils.getTranslated("launcher.info.creditslink"));
		creditsLabels[1].setHorizontalAlignment(SwingConstants.CENTER);

		infoLabels[0].setText(LanguageUtils.getTranslated("launcher.info.launcherName"));

		infoLabels[2].setText(LanguageUtils.getTranslated("launcher.info.launcherVersion"));

		infoLabels[4].setText(LanguageUtils.getTranslated("launcher.info.ownedBy"));

		infoLabels[1].setText(Settings.launcherName);

		infoLabels[3].setText(Settings.launcherVersion);

		infoLabels[5].setText(Settings.launcherOwner);

		infoLabels[6] = new LinkLabel(Settings.linkInfo,Settings.linkInfo);
		infoLabels[6].setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(separator).addComponent(creditsLabels[0], GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addComponent(creditsLabels[1], GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[3], GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(infoLabels[6], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(17, 17, 17).addComponent(infoLabels[0]).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(infoLabels[1]).addGap(18, 18, 18).addComponent(infoLabels[2]).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(infoLabels[3]).addGap(18, 18, 18).addComponent(infoLabels[4]).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(infoLabels[5]).addGap(18, 18, 18).addComponent(infoLabels[6]).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(creditsLabels[0]).addGap(18, 18, 18).addComponent(creditsLabels[1]).addGap(12, 12, 12)));
	}

	private JLabel infoLabels[];

	private JLabel creditsLabels[];
	private JSeparator separator;
}
