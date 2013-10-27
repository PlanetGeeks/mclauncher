package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.LauncherLogger;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.frames.utils.CustomWindowListener;
import it.planetgeeks.mclauncher.utils.ConsoleOutStream;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ConsoleFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static ConsoleOutStream out;
	public ArrayList<String[]> lines = new ArrayList<String[]>();

	public ConsoleFrame()
	{
		initComponents();
		out = new ConsoleOutStream();
	}

	private void initComponents()
	{
		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);
		
		scrollPanel = new JScrollPane();
		consolePanel = new JTextArea();
		saveLogBtn = new JButton();
		saveLogBtn.setForeground(new Color(Settings.buttonsForeground));
		consoleTypeBox = new JComboBox<Object>();
		clearBtn = new JButton();
		clearBtn.setForeground(new Color(Settings.buttonsForeground));

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		scrollPanel.setViewportView(consolePanel);

		consolePanel.setEditable(false);
		consolePanel.setBackground(new Color(Settings.consoleBackground));
		consolePanel.setFont(new Font("sansserif", 1, 12));
		consolePanel.setForeground(new Color(Settings.consoleForeground));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollPanel).addGroup(layout.createSequentialGroup().addGap(0, 300, Short.MAX_VALUE).addComponent(saveLogBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(consoleTypeBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveLogBtn).addComponent(consoleTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(clearBtn)).addContainerGap()));

		this.addWindowListener(new CustomWindowListener()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				Launcher.openOrCloseConsole();
			}
		});

		saveLogBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				LauncherLogger.saveLog(consolePanel);
			}
		});

		clearBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				consolePanel.setText("");
			}
		});
		
		consoleTypeBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				updateTextArea();
			}
		});
		
		pack();
	}

	public void updateComponents()
	{
		saveLogBtn.setText(LanguageUtils.getTranslated("launcher.console.savelog"));
		clearBtn.setText(LanguageUtils.getTranslated("launcher.console.clear"));
		consoleTypeBox.setModel(new DefaultComboBoxModel<Object>(new String[] { LanguageUtils.getTranslated("launcher.console.box.all"), LanguageUtils.getTranslated("launcher.console.box.launcher"), LanguageUtils.getTranslated("launcher.console.box.minecraft") }));
		setTitle(LanguageUtils.getTranslated("launcher.console.title"));
	}
	
	public void updateTextArea()
	{
		consolePanel.setText("");
		
		for(int i = 0; i < lines.size(); i++)
		{
			if(consoleTypeBox.getSelectedIndex() == 0 || (lines.get(i)[0].equals("LAUNCHER") && consoleTypeBox.getSelectedIndex() == 1) || (lines.get(i)[0].equals("MINECRAFT") && consoleTypeBox.getSelectedIndex() == 2))
			{
				consolePanel.append(lines.get(i)[1]);
			}
		}
	}

	private JButton saveLogBtn;
	private JButton clearBtn;
	private JComboBox<Object> consoleTypeBox;
	private JTextArea consolePanel;
	private JScrollPane scrollPanel;
}
