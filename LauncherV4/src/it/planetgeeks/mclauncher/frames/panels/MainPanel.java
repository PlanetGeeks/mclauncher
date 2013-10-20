package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.frames.EnumLayouts;
import it.planetgeeks.mclauncher.settings.Settings;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

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
import javax.swing.JEditorPane;
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

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jfxPanels[0])).addGroup(layout.createSequentialGroup().addComponent(mpFilterBtn, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpBtn2))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpFilterBtn).addComponent(mpFilterLbl).addComponent(mpBtn1).addComponent(mpBtn2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jfxPanels[0]).addGroup(layout.createSequentialGroup().addComponent(mpScrollPanel, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE).addContainerGap()))));

		}
		else if (current == EnumLayouts.MULTI_NEWS_MODPACK)
		{
			loadMultiNewsComponents();

			loadModPackComponents();

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

			GroupLayout layout = new GroupLayout(this);
			this.setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(leftSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(138, 138, 138).addComponent(rightSkin, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpFilterBtn, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE).addComponent(mpFilterLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(mpBtn2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(mpBtn1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mpBtn1).addComponent(mpFilterBtn)).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(3, 3, 3).addComponent(mpBtn2)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mpFilterLbl).addGap(1, 1, 1)))).addComponent(mpCombobox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)).addGap(11, 11, 11).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(rightSkin).addComponent(leftSkin)).addContainerGap()).addComponent(tabbedPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))));
		}

	}

	private void loadSkinComponents()
	{
		leftSkin = new JButton();
		rightSkin = new JButton();
		leftSkin.setText("<");
		rightSkin.setText(">");
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

		editorPanel = new JEditorPane();
		Dimension d = new Dimension();
		d.width = 400;
		d.height = 200;
		editorPanel.setPreferredSize(d);

		loadURL(Settings.newsLink1, 0);
	}

	private void loadBgComponents()
	{
		bgPanel = new JPanel();
	}

	private void loadModPackComponents()
	{
		mpBtn1 = new JButton();
		mpBtn2 = new JButton();
		mpFilterBtn = new JButton();
		mpFilterLbl = new JLabel();

		if (Settings.layoutMode == EnumLayouts.MULTI_NEWS_MODPACK || Settings.layoutMode == EnumLayouts.NEWS_MODPACK)
		{
			mpList = new JList();

			mpList.setModel(new AbstractListModel()
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
			mpCombobox = new JComboBox();
			mpCombobox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

	private static String toURL(String str)
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

	private JButton mpBtn1;
	private JButton mpBtn2;
	private JButton mpFilterBtn;
	private JComboBox mpCombobox;
	private JLabel mpFilterLbl;
	private JList mpList;
	private JScrollPane mpScrollPanel;
	private JEditorPane editorPanel;
	private JButton leftSkin;
	private JButton rightSkin;
	private JTabbedPane tabbedPanel;
	final private JFXPanel jfxPanels[] = { new JFXPanel(), new JFXPanel(), new JFXPanel() };
	private WebEngine engines[];
	private int jfxtempvalue = 0;
	private JPanel bgPanel;
}