package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.frames.MPServerFrame;
import it.planetgeeks.mclauncher.frames.utils.CustomComponentListener;
import it.planetgeeks.mclauncher.frames.utils.CustomMouseListener;
import it.planetgeeks.mclauncher.modpack.EnumFilterType;
import it.planetgeeks.mclauncher.modpack.ModPack;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.DesktopUtils;
import it.planetgeeks.mclauncher.utils.DirUtils;
import it.planetgeeks.mclauncher.utils.FileUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.ProfilesUtils;
import it.planetgeeks.mclauncher.utils.SkinsManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

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

			loadNewsComponents();

			loadModPackComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(leftSkin).addComponent(rightSkin)).addContainerGap()).addComponent(bgPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		else if (current == EnumLayouts.NEWS)
		{
			loadNewsComponents();

			GroupLayout mainPanelLayout = new GroupLayout(this);
			setLayout(mainPanelLayout);
			mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(jfxPanels[0]).addContainerGap()));
			mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(jfxPanels[0], GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));

		}
		else if (current == EnumLayouts.NEWS_SKIN)
		{
			loadNewsComponents();

			loadSkinComponents();
			jfxPanels[0].setPreferredSize(new Dimension(200, 200));
			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27).addComponent(jfxPanels[0], GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jfxPanels[0], GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 327, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addGap(30, 30, 30)))));

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
				jfxPanels[i].setPreferredSize(new Dimension(200, 200));
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
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(jfxPanels[0], GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(leftSkin).addComponent(rightSkin)).addContainerGap()).addComponent(jfxPanels[0], GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		else if (current == EnumLayouts.NEWS_MODPACK)
		{
			loadNewsComponents();

			loadModPackComponents();

			jfxPanels[0].setPreferredSize(new Dimension(200, 200));

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jfxPanels[0])).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jfxPanels[0]).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));
		}
		else if (current == EnumLayouts.MULTI_NEWS_MODPACK)
		{
			loadMultiNewsComponents();

			loadModPackComponents();

			for (int i = 0; i < 3; i++)
			{
				jfxPanels[i].setPreferredSize(new Dimension(200, 200));
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
				jfxPanels[i].setPreferredSize(new Dimension(200, 200));
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
		createScene();

		tabbedPanel = new JTabbedPane();

		tabbedPanel.addTab(LanguageUtils.getTranslated("launcher.newstab1.title"), jfxPanels[0]);
		tabbedPanel.addTab(LanguageUtils.getTranslated("launcher.newstab2.title"), jfxPanels[1]);
		tabbedPanel.addTab(LanguageUtils.getTranslated("launcher.newstab3.title"), jfxPanels[2]);

		loadURL(Settings.newsLink1, 0);
		loadURL(Settings.newsLink2, 1);
		loadURL(Settings.newsLink3, 2);
	}

	private void loadNewsComponents()
	{
		createScene();

		loadURL(Settings.newsLink1, 0);
	}

	private void loadBgComponents()
	{
		bgPanel = new JPanel();
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
					ModPackUtils.selected = ModPackUtils.filteredList.get(mpList.getSelectedIndex());
					if (ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
					{
						mpBtn1.setEnabled(true);
					}
					else
					{
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
					ModPackUtils.selected = ModPackUtils.filteredList.get(mpCombobox.getSelectedIndex());
					if (ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
					{
						mpBtn1.setEnabled(true);
					}
					else
					{
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
				Launcher.openOrCloseMPInfoFrame(ModPackUtils.selected);
			}
		});

	}

	private void createScene()
	{
		EnumLayouts current = Settings.layoutMode;
		jfxtempvalue = current == EnumLayouts.MULTI_NEWS || current == EnumLayouts.MULTI_NEWS_MODPACK || current == EnumLayouts.MULTI_NEWS_SKIN || current == EnumLayouts.MULTI_NEWS_SKIN_MODPACK ? 3 : 1;

		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				if (jfxtempvalue == 1)
				{
					engines = new WebEngine[1];
				}
				else
				{
					engines = new WebEngine[3];
				}

				for (int i = 0; i < engines.length; i++)
				{
					WebView view = new WebView();
					engines[i] = view.getEngine();
					jfxPanels[i].setScene(new Scene(view));
				}
			}
		});
	}

	private void loadURL(final String url, final int engine)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				String tmp = toURL(url);

				if (tmp == null)
				{
					tmp = toURL("http://" + url);
				}

				engines[engine].load(tmp);
			}
		});
	}

	private String toURL(String str)
	{
		try
		{
			return new URL(str).toExternalForm();
		}
		catch (MalformedURLException exception)
		{
			return null;
		}
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
		                remove(skinPoligon[i]);
					}
				}
				
				if (ProfilesUtils.getSelectedProfile() != null)
				{
					SkinsManager.startLoadingThread(this, null, "", null, skinXY[0], skinXY[1], skinPoligon, mode, false);
				}
			}
		}
	}

	public void setSkinPoligon(JButton[] buttons)
	{
		for (int i = 0; i < skinPoligon.length; i++)
		{
			if (skinPoligon[i] != null)
			{
                remove(skinPoligon[i]);
			}
		}
		
		for (int i = 0; i < skinPoligon.length; i++)
		{
			skinPoligon[i] = buttons[i];
			if(skinPoligon[i] != null)
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

		ArrayList<ModPack> filtered = ModPackUtils.getFilteredList(ModPackUtils.getClonedList(modpacks), ModPackUtils.filter, ModPackUtils.filterStr);

		ModPackUtils.filteredList = filtered;

		ModPackUtils.selected = filtered.size() > 0 ? filtered.get(0) : null;

		if (ModPackUtils.selected.packServerLink != null && DesktopUtils.checkLink(ModPackUtils.selected.packServerLink))
		{
			mpBtn1.setEnabled(true);
		}
		else
		{
			mpBtn1.setEnabled(false);
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
				mpCombobox.setModel(new DefaultComboBoxModel<Object>(obj));
			}
			else
			{
				mpList.setModel(new DefaultComboBoxModel<Object>(obj));
			}

			if (!completed)
			{
				mpFilterLbl.setText(getModPacksFilter() + " - " + LanguageUtils.getTranslated("launcher.modpacks.loading"));
			}
			else
			{
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
	private JTabbedPane tabbedPanel;
	final private JFXPanel jfxPanels[] = { new JFXPanel(), new JFXPanel(), new JFXPanel() };
	private WebEngine engines[];
	private int jfxtempvalue = 0;
	private JPanel bgPanel;
	private JLabel loadSkin;
	private JLabel animLoadSkin;
	public boolean loadingModPack = true;
	public boolean skinLayout = false;
}
