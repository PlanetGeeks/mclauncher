package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.frames.MPServerFrame;
import it.planetgeeks.mclauncher.frames.utils.BgPanel;
import it.planetgeeks.mclauncher.frames.utils.CustomComponentListener;
import it.planetgeeks.mclauncher.frames.utils.CustomMouseListener;
import it.planetgeeks.mclauncher.frames.utils.DraggableTabbedPane;
import it.planetgeeks.mclauncher.modpack.EnumFilterType;
import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.resources.ResourcesUtils;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;
import it.planetgeeks.mclauncher.utils.SkinsManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

/**
 * @author PlanetGeeks
 * 
 */

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public MainPanel()
	{
		initComponents();
	}

	private void initComponents()
	{
		EnumLayouts current = Settings.layoutMode;

		if (current == EnumLayouts.BG)
		{
			loadBgComponents();

			GroupLayout mainPanelLayout = new GroupLayout(this);
			setLayout(mainPanelLayout);
			mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(bgPanel).addContainerGap()));
			mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));

		}
		else if (current == EnumLayouts.BG_SKIN)
		{
			loadBgComponents();

			loadSkinComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27).addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bgPanel, GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 327, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addGap(30, 30, 30)))));
		}
		else if (current == EnumLayouts.BG_MODPACK)
		{
			loadBgComponents();

			loadModPackComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(bgPanel)).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bgPanel).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));
		}
		else if (current == EnumLayouts.BG_SKIN_MODPACK)
		{
			loadBgComponents();

			loadSkinComponents();

			loadModPackComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(bgPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		else if (current == EnumLayouts.NEWS)
		{
			loadNewsComponents();

			GroupLayout mainPanelLayout = new GroupLayout(this);
			setLayout(mainPanelLayout);
			mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(newsPanel).addContainerGap()));
			mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(newsPanel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));

		}
		else if (current == EnumLayouts.NEWS_SKIN)
		{
			loadNewsComponents();

			loadSkinComponents();
			newsPanel.setPreferredSize(new Dimension(200, 200));
			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27).addComponent(newsPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(newsPanel, GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 327, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addGap(30, 30, 30)))));

		}
		else if (current == EnumLayouts.MULTI_NEWS)
		{

			loadMultiNewsComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));

		}
		else if (current == EnumLayouts.MULTI_NEWS_SKIN)
		{
			loadSkinComponents();

			loadMultiNewsComponents();

			for (int i = 0; i < 3; i++)
			{
				jfxScrollPanels[i].setPreferredSize(new Dimension(200, 200));
			}

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27).addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tabbedPanel, GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 327, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addGap(30, 30, 30)))));

		}
		else if (current == EnumLayouts.NEWS_SKIN_MODPACK)
		{
			loadSkinComponents();

			loadNewsComponents();

			loadModPackComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(newsPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(newsPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		else if (current == EnumLayouts.NEWS_MODPACK)
		{
			loadNewsComponents();

			loadModPackComponents();

			newsPanel.setPreferredSize(new Dimension(200, 200));

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(newsPanel)).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(newsPanel).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));
		}
		else if (current == EnumLayouts.MULTI_NEWS_MODPACK)
		{
			loadMultiNewsComponents();

			loadModPackComponents();

			for (int i = 0; i < 3; i++)
			{
				jfxScrollPanels[i].setPreferredSize(new Dimension(200, 200));
			}

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(tabbedPanel)).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tabbedPanel).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));
		}
		else if (current == EnumLayouts.MULTI_NEWS_SKIN_MODPACK)
		{
			loadSkinComponents();

			loadMultiNewsComponents();

			loadModPackComponents();

			for (int i = 0; i < 3; i++)
			{
				jfxScrollPanels[i].setPreferredSize(new Dimension(200, 200));
			}

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(tabbedPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}

		this.setBackground(new Color(0, 0, 0, 0));

		this.setOpaque(false);

	}

	private void loadSkinComponents()
	{
		skinLayout = true;
		leftSkin = new JButton();
		leftSkin.setForeground(new Color(Settings.buttonsForeground));
		rightSkin = new JButton();
		rightSkin.setForeground(new Color(Settings.buttonsForeground));
		leftSkin.setText("<");
		rightSkin.setText(">");

		rightSkin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (!loadSkin.isVisible())
				{
					mode = mode < 3 ? ++mode : 0;
					updateSkin();
				}
			}
		});

		leftSkin.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (!loadSkin.isVisible())
				{
					mode = mode > 0 ? --mode : 3;
					updateSkin();
				}
			}
		});

		skinXY = new int[2];

		this.addComponentListener(new CustomComponentListener()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				updateSkin();
			}
		});

	}

	private void loadMultiNewsComponents()
	{
		tabbedPanel = new DraggableTabbedPane();

		for (int i = 0; i < 3; i++)
		{
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			JLabel tempLoad = new JLabel();
			tempLoad.setSize(45, 45);
			tempLoad.setIcon(Launcher.getResources().getResource("newsLoad.gif"));
			p.add(tempLoad);
			jfxScrollPanels[i].setViewportView(p);

			tabbedPanel.addTab(LanguageUtils.getTranslated("launcher.newstab" + (i + 1) + ".title"), jfxScrollPanels[i]);
		}
	}

	public void loadTranslations()
	{
		if (tabbedPanel != null)
		{
			for (int i = 0; i < 3; i++)
			{
				String key = LanguageUtils.getKey(tabbedPanel.getTitleAt(i), LanguageUtils.getLatest());
				tabbedPanel.setTitleAt(i, LanguageUtils.getTranslated(key));
			}
		}

		if (mpBtn1 != null)
			mpBtn1.setText(LanguageUtils.getTranslated("launcher.modpacks.downloadserver"));

		if (mpBtn2 != null)
			mpBtn2.setText(LanguageUtils.getTranslated("launcher.modpacks.showinfo"));

		if (mpFilterBtn != null)
			mpFilterBtn.setText(LanguageUtils.getTranslated("launcher.modpacks.filter.settings"));

		if (mpFilterLbl != null)
			mpFilterLbl.setText(getModPacksFilter());
	}

	private void loadNewsComponents()
	{
		newsPanel = new JPanel();
		newsPanel.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		JLabel tempLoad = new JLabel();
		tempLoad.setSize(45, 45);
		tempLoad.setIcon(Launcher.getResources().getResource("newsLoad.gif"));
		p.add(tempLoad);
		jfxScrollPanels[0].setViewportView(p);
		newsPanel.add(jfxScrollPanels[0]);
	}

	private void loadBgComponents()
	{
		bgPanel = new BgPanel(Launcher.getBgArray());

		final JLabel arrowLeft = new JLabel();
		final JLabel arrowRight = new JLabel();

		if (Launcher.getBgLength() > 1)
		{
			ResourcesUtils res = Launcher.getResources();
			ImageIcon icon = res.getCroppedResorce("arrows.png", 0, 0, 55, 55);
			BufferedImage buf = res.getFromIcon(icon);

			arrowLeft.setIcon(icon);
			arrowRight.setIcon(new ImageIcon(Launcher.getResources().getReflectedResource(buf, -1.0D, 1.0D)));

			arrowLeft.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent me)
				{
					Launcher.changeBg(false);
					bgPanel.setBg(false);
					bgPanel.repaint();
				}

				public void mouseEntered(MouseEvent me)
				{
					ResourcesUtils res = Launcher.getResources();
					ImageIcon icon = res.getCroppedResorce("arrows.png", 55, 0, 55, 55);
					arrowLeft.setIcon(icon);
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				public void mouseExited(MouseEvent me)
				{
					ResourcesUtils res = Launcher.getResources();
					ImageIcon icon = res.getCroppedResorce("arrows.png", 0, 0, 55, 55);
					arrowLeft.setIcon(icon);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});

			arrowRight.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent me)
				{
					Launcher.changeBg(true);
					bgPanel.setBg(true);
					bgPanel.repaint();
				}

				public void mouseEntered(MouseEvent me)
				{
					ResourcesUtils res = Launcher.getResources();
					ImageIcon icon = res.getCroppedResorce("arrows.png", 55, 0, 55, 55);
					BufferedImage buf = res.getFromIcon(icon);
					arrowRight.setIcon(new ImageIcon(Launcher.getResources().getReflectedResource(buf, -1.0D, 1.0D)));
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				public void mouseExited(MouseEvent me)
				{
					ResourcesUtils res = Launcher.getResources();
					ImageIcon icon = res.getCroppedResorce("arrows.png", 0, 0, 55, 55);
					BufferedImage buf = res.getFromIcon(icon);
					arrowRight.setIcon(new ImageIcon(Launcher.getResources().getReflectedResource(buf, -1.0D, 1.0D)));

					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
		}

		GroupLayout layout = new GroupLayout(bgPanel);
		bgPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(arrowLeft, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 643, Short.MAX_VALUE).addComponent(arrowRight, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(210, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(arrowLeft, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addComponent(arrowRight, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)).addContainerGap(210, Short.MAX_VALUE)));
	}

	private void loadModPackComponents()
	{
		mpBtn1 = new JButton();
		mpBtn1.setForeground(new Color(Settings.buttonsForeground));
		mpBtn2 = new JButton();
		mpBtn2.setForeground(new Color(Settings.buttonsForeground));
		mpFilterBtn = new JButton();
		mpFilterBtn.setForeground(new Color(Settings.buttonsForeground));
		mpFilterLbl = new JLabel();

		if (Settings.layoutMode == EnumLayouts.MULTI_NEWS_MODPACK || Settings.layoutMode == EnumLayouts.NEWS_MODPACK || Settings.layoutMode == EnumLayouts.BG_MODPACK)
		{
			mpList = new JList<Object>();

			mpScrollPanel = new JScrollPane();

			mpScrollPanel.setViewportView(mpList);

			mpList.setBackground(new Color(0, 0, 0, 0));

			mpScrollPanel.setBackground(new Color(0, 0, 0, 0));

			mpScrollPanel.setOpaque(false);

			mpList.setOpaque(false);

			mpScrollPanel.getViewport().setOpaque(false);

			mpScrollPanel.setViewportBorder(null);

			mpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			mpList.addMouseListener(new CustomMouseListener()
			{
				@Override
				public void mousePressed(MouseEvent e)
				{
					
					if (mpList.getSelectedIndex() >= 0 && mpList.getSelectedIndex() < ModPackUtils.filteredList.size())
					{
						ModPackUtils.selected = ModPackUtils.filteredList.get(mpList.getSelectedIndex());
					}
					else
					{
						ModPackUtils.selected = null;
					}

					if (ModPackUtils.selected != null)
					{
						mpBtn2.setEnabled(true);
						if (ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
						{
							mpBtn1.setEnabled(true);
						}
						else
						{
							mpBtn1.setEnabled(false);

						}
					}
					else
					{
						mpBtn2.setEnabled(false);
						mpBtn1.setEnabled(false);
					}
				}
			});

		}
		else
		{
			mpCombobox = new JComboBox<Object>();

			mpCombobox.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					if (mpCombobox.getSelectedIndex() >= 0 && mpCombobox.getSelectedIndex() < ModPackUtils.filteredList.size())
					{
						ModPackUtils.selected = ModPackUtils.filteredList.get(mpCombobox.getSelectedIndex());
					}
					else
					{
						ModPackUtils.selected = null;
						mpCombobox.setSelectedIndex(-1);

					}

					if (ModPackUtils.selected != null)
					{
						mpBtn2.setEnabled(true);
						if (ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
						{
							mpBtn1.setEnabled(true);
						}
						else
						{
							mpBtn1.setEnabled(false);

						}
					}
					else
					{
						mpBtn2.setEnabled(false);
						mpBtn1.setEnabled(false);
					}
				}
			});

		}

		mpBtn1.setText(LanguageUtils.getTranslated("launcher.modpacks.downloadserver"));

		mpBtn2.setText(LanguageUtils.getTranslated("launcher.modpacks.showinfo"));

		mpFilterBtn.setText(LanguageUtils.getTranslated("launcher.modpacks.filter.settings"));

		mpFilterLbl.setText(getModPacksFilter());

		mpFilterBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Launcher.openOrCloseFilterFrame();
			}
		});

		mpBtn1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (ModPackUtils.selected.directServerDownload)
				{

					JFileChooser saveFile = new JFileChooser();
					saveFile.setSelectedFile(new File(DirUtils.getLauncherDirectory() + File.separator + "server.zip"));
					if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						File toSave = saveFile.getSelectedFile();

						String extension = FileUtils.getExtension(ModPackUtils.selected.packServerLink);

						if (!toSave.getName().endsWith(extension))
						{
							toSave = new File(toSave.getAbsolutePath() + extension);
						}

						if (toSave.exists())
						{
							if (JOptionPane.showConfirmDialog(null, LanguageUtils.getTranslated("launcher.saveoverwritemessage"), LanguageUtils.getTranslated("launcher.saveoverwritetitle"), JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
							{
								return;
							}
						}
						MPServerFrame server = new MPServerFrame(ModPackUtils.selected.packServerLink, toSave);
						server.setVisible(true);
						server.startDownload();
					}
				}
				else
				{
					DesktopUtils.openWebPage(ModPackUtils.selected.packServerLink);
				}
			}
		});

		mpBtn2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (ModPackUtils.selected != null)
				{
					Launcher.openOrCloseMPInfoFrame(ModPackUtils.selected);
				}
			}
		});

	}

	public void updateSkin()
	{
		if (skinLayout)
		{
			if (ProfilesUtils.getSelectedProfile() == null || ProfilesUtils.getSelectedProfile().skin == null)
			{
				if (loadSkin == null)
				{
					loadSkin = new JLabel();
					loadSkin.setIcon(Launcher.getResources().getResource("pgLoad.png"));
					loadSkin.setSize(112, 224);
					animLoadSkin = new JLabel();
					animLoadSkin.setSize(45, 35);
					animLoadSkin.setIcon(Launcher.getResources().getResource("load.gif"));
				}
				else
				{
					this.remove(loadSkin);
					this.remove(animLoadSkin);
				}

				int tempXY[] = { 88, 300 };

				loadSkin.setBounds(tempXY[0], this.getSize().height - tempXY[1], 112, 224);
				animLoadSkin.setBounds(tempXY[0] + 33, this.getSize().height - tempXY[1] + 80, 45, 35);
				this.add(loadSkin);
				this.add(animLoadSkin);

				for (int i = 0; i < skinPoligon.length; i++)
				{
					if (skinPoligon[i] != null)
					{
						skinPoligon[i].setVisible(false);
					}
				}

				this.loadSkin.setVisible(true);
				if (ProfilesUtils.getSelectedProfile() != null)
				{
					this.animLoadSkin.setVisible(true);
				}
				else
				{
					this.animLoadSkin.setVisible(false);
				}
			}
			else
			{
				this.loadSkin.setVisible(false);
				this.animLoadSkin.setVisible(false);
			}

			if (!SkinsManager.operating)
			{
				skinXY[0] = 116;
				skinXY[1] = this.getSize().height - 300;

				for (int i = 0; i < skinPoligon.length; i++)
				{
					if (skinPoligon[i] != null)
					{
						skinPoligon[i].setVisible(false);
					}
				}

				if (ProfilesUtils.getSelectedProfile() != null)
				{
					SkinsManager.startLoadingThread(this, null, "", null, skinXY[0], skinXY[1], new JButton[7], mode, false);
				}
			}
		}
	}

	public void setSkinPoligon(JButton[] buttons)
	{
		for (int i = 0; i < skinPoligon.length; i++)
		{

			if (skinPoligon[i] == null)
			{
				skinPoligon[i] = new JButton();
			}

			if (buttons[i] != null)
			{
				skinPoligon[i].setIcon(buttons[i].getIcon());
				skinPoligon[i].setSelectedIcon(buttons[i].getIcon());
				skinPoligon[i].setDisabledIcon(buttons[i].getPressedIcon());
				skinPoligon[i].setPressedIcon(buttons[i].getIcon());
				skinPoligon[i].setOpaque(false);
				skinPoligon[i].setFocusable(false);
				skinPoligon[i].setContentAreaFilled(false);
				skinPoligon[i].setBorderPainted(false);
				skinPoligon[i].setRolloverEnabled(false);
				skinPoligon[i].setBounds(buttons[i].getBounds());
				skinPoligon[i].setVisible(true);
			}
			else
			{
				skinPoligon[i].setVisible(false);
			}

			if (skinPoligon[i].getParent() == null)
			{
				add(skinPoligon[i]);
			}

		}

		revalidate();
		repaint();
	}

	public void updateModPacks(ArrayList<ModPack> modpacks, boolean completed)
	{
		loadingModPack = completed;

		Launcher.getLauncherFrame().southPanel.setLaunchActive();

		ArrayList<ModPack> filtered = ModPackUtils.getFilteredList(modpacks, ModPackUtils.filter, ModPackUtils.filterStr);

		ModPackUtils.filteredList = filtered;

		ModPackUtils.selected = filtered.size() > 0 ? filtered.get(0) : null;

		if (ModPackUtils.selected != null && ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
		{
			if (mpBtn1 != null)
			{
				mpBtn1.setEnabled(true);
				mpBtn2.setEnabled(true);
			}
		}
		else
		{
			if (mpBtn1 != null)
			{
				mpBtn1.setEnabled(false);
			}
			if (mpBtn2 != null)
			{
				mpBtn2.setEnabled(false);
			}
		}

		String layoutType = getModPackLayoutType();
		if (!layoutType.equals("NULL"))
		{
			Object obj[] = new Object[filtered.size()];

			for (int i = 0; i < filtered.size(); i++)
			{
				if (filtered.get(i).packImage != null)
				{
					obj[i] = filtered.get(i).packImage;
				}
				else
				{
					obj[i] = filtered.get(i).packName;
				}
			}

			if (layoutType.equals("COMBOBOX"))
			{
				if (mpCombobox != null)
				{

					mpCombobox.setModel(new DefaultComboBoxModel<Object>(obj));
					if (obj.length == 0)
					{
						System.out.println("-1");
						mpCombobox.setSelectedIndex(-1);
					}
				}
			}
			else
			{
				if (mpList != null)
				{
					mpList.setModel(new DefaultComboBoxModel<Object>(obj));
					if (obj.length == 0)
					{
						mpList.setSelectedValue(null, false);
					}
				}
			}

			if (!completed)
			{
				if (mpFilterLbl != null)
					mpFilterLbl.setText(getModPacksFilter() + " - " + LanguageUtils.getTranslated("launcher.modpacks.loading"));
			}
			else
			{
				if (mpFilterLbl != null)
					mpFilterLbl.setText(getModPacksFilter());
			}
		}
	}

	private String getModPacksFilter()
	{
		String returned;

		if (ModPackUtils.filter == EnumFilterType.ALL || ModPackUtils.filter == EnumFilterType.HASSERVER)
		{
			returned = LanguageUtils.getTranslated("launcher.modpacks.filter") + " / " + LanguageUtils.getTranslated(ModPackUtils.filter.str);
		}
		else
		{
			returned = LanguageUtils.getTranslated("launcher.modpacks.filter") + " / " + LanguageUtils.getTranslated(ModPackUtils.filter.str) + " ('" + ModPackUtils.filterStr + "')";
		}
		return returned;
	}

	private String getModPackLayoutType()
	{
		if (Settings.layoutMode == EnumLayouts.BG_MODPACK || Settings.layoutMode == EnumLayouts.MULTI_NEWS_MODPACK || Settings.layoutMode == EnumLayouts.NEWS_MODPACK)
		{
			return "LIST";
		}
		else if (Settings.layoutMode == EnumLayouts.BG_SKIN_MODPACK || Settings.layoutMode == EnumLayouts.MULTI_NEWS_SKIN_MODPACK || Settings.layoutMode == EnumLayouts.NEWS_SKIN_MODPACK)
		{
			return "COMBOBOX";
		}

		return "NULL";
	}

	public void updateNews()
	{
		if (tabbedPanel != null)
		{
			for (int i = 0; i < 3; i++)
			{
				tabbedPanel.setComponentAt(i, jfxScrollPanels[i]);
			}
		}
		else
		{
			newsPanel.removeAll();
			newsPanel.add(jfxScrollPanels[0]);
			newsPanel.repaint();
			newsPanel.revalidate();
		}
	}

	private int skinXY[];
	private JButton[] skinPoligon = new JButton[7];
	private int mode = 0;
	private JButton mpBtn1;
	private JButton mpBtn2;
	private JButton mpFilterBtn;
	private JComboBox<Object> mpCombobox;
	private JLabel mpFilterLbl;
	private JList<Object> mpList;
	private JScrollPane mpScrollPanel;
	private JButton leftSkin;
	private JButton rightSkin;
	public DraggableTabbedPane tabbedPanel;
	public JScrollPane jfxScrollPanels[] = { new JScrollPane(), new JScrollPane(), new JScrollPane() };
	public JPanel newsPanel;
	private BgPanel bgPanel;
	private JLabel loadSkin;
	private JLabel animLoadSkin;
	public boolean loadingModPack = true;
	public boolean skinLayout = false;

}
