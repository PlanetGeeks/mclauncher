package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.frames.utils.CustomComponentListener;
import it.planetgeeks.mclauncher.utils.SkinsManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;

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
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(bgPanel)).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bgPanel).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));
		}
		else if (current == EnumLayouts.BG_SKIN_MODPACK)
		{
			loadBgComponents();

			loadNewsComponents();

			loadModPackComponents();

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpFilterBtn, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(bgPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
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
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(jfxPanels[0], GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpFilterBtn, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(jfxPanels[0], GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		else if (current == EnumLayouts.NEWS_MODPACK)
		{
			loadNewsComponents();

			loadModPackComponents();

			jfxPanels[0].setPreferredSize(new Dimension(200, 200));

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jfxPanels[0])).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
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
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(tabbedPanel)).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
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
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpFilterBtn, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(tabbedPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}
		
		this.setBackground(new Color(0,0,0,0));
		
		this.setOpaque(false);

	}

	private void loadSkinComponents()
	{
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

		tabbedPanel.addTab("tab1", jfxPanels[0]);
		tabbedPanel.addTab("tab2", jfxPanels[1]);
		tabbedPanel.addTab("tab3", jfxPanels[2]);

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

			mpList.setModel(new AbstractListModel<Object>()
			{
				private static final long serialVersionUID = 1L;

				String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

				public int getSize()
				{
					return strings.length;
				}

				public Object getElementAt(int i)
				{
					return strings[i];
				}
			});

			mpScrollPanel = new JScrollPane();

			mpScrollPanel.setViewportView(mpList);
		}
		else
		{
			mpCombobox = new JComboBox<String>();
			mpCombobox.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		}

		mpBtn1.setText("Download Server");

		mpBtn2.setText("Mostra informazioni");

		mpFilterBtn.setText("Impostazioni Filtro");

		mpFilterLbl.setText("mpFilterLbl");
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
		if (SkinsManager.originalImage == null)
		{
			if (loadSkin == null)
			{
				loadSkin = new JLabel();
				loadSkin.setIcon(Launcher.resources.getResource("pgLoad.png"));
				loadSkin.setSize(112, 224);
				animLoadSkin = new JLabel();
				animLoadSkin.setSize(45, 35);
				animLoadSkin.setIcon(Launcher.resources.getResource("load.gif"));
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
		}
		else
		{
			this.loadSkin.setVisible(false);
			this.animLoadSkin.setVisible(false);
		}

		for (int i = 0; i < skinPoligon.size(); i++)
		{
			remove(skinPoligon.get(i));
		}

		skinXY[0] = 116;
		skinXY[1] = this.getSize().height - 300;

		skinPoligon = new ArrayList<JButton>();
		SkinsManager.startLoadingThread(this, null, "https://dl.dropboxusercontent.com/u/88221856/skin/Flood.png", null, skinXY[0], skinXY[1], skinPoligon, mode, false);
		revalidate();
		repaint();
	}

	private int skinXY[];
	private ArrayList<JButton> skinPoligon = new ArrayList<JButton>();
	private int mode = 0;
	private JButton mpBtn1;
	private JButton mpBtn2;
	private JButton mpFilterBtn;
	private JComboBox<String> mpCombobox;
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
}
