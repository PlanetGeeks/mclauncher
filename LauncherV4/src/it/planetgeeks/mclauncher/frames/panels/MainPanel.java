package it.planetgeeks.mclauncher.frames.panels;

import it.planetgeeks.mclauncher.settings.Settings;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public MainPanel()
	{
		initComponents();

	}

	public void initComponents()
	{
		scrollPanel = new JScrollPane();
		editorPanel = new JEditorPane();
		Dimension d = new Dimension();
		d.width = 400;
		d.height = 200;
		editorPanel.setPreferredSize(d);
		scrollPanel.setViewportView(editorPanel);
		
		try
		{
			editorPanel.setBorder(null);
			editorPanel.setEditable(false);
			editorPanel.setPage(new URL(Settings.newsLink1));
			editorPanel.setBackground(Color.WHITE);
		}
		catch (IOException e)
		{
			editorPanel.setText("Error");
			e.printStackTrace();

		}
		editorPanel.addHyperlinkListener(new HyperlinkListener()
		{
			public void hyperlinkUpdate(HyperlinkEvent he)
			{
				if (he.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				{
					try
					{
						Desktop.getDesktop().browse(he.getURL().toURI());
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});

		GroupLayout mainPanelLayout = new GroupLayout(this);
		setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(scrollPanel).addContainerGap()));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)));
	}

	private JScrollPane scrollPanel;
	private JEditorPane editorPanel;
}
