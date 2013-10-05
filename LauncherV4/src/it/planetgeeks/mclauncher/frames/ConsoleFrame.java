package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.ConsoleOutput;
import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.frames.utils.CustomWindowListener;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

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

	public ConsoleFrame(ConsoleOutput outConsole)
	{
		initComponents();
		outConsole = new ConsoleOutput(consolePanel);
		System.setOut(new PrintStream(outConsole));
	}

	private void initComponents()
	{

		this.setTitle(LanguageUtils.getTranslated("launcher.console.title"));

		scrollPanel = new JScrollPane();
		consolePanel = new JTextArea();
		saveLogBtn = new JButton();
		consoleTypeBox = new JComboBox();
		clearBtn = new JButton();

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		scrollPanel.setViewportView(consolePanel);

		consolePanel.setEditable(false);
		consolePanel.setBackground(new Color(0, 0, 0));
		consolePanel.setFont(new Font("sansserif", 1, 12));
		consolePanel.setForeground(new Color(0x45CE06));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollPanel).addGroup(layout.createSequentialGroup().addGap(0, 300, Short.MAX_VALUE).addComponent(saveLogBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(consoleTypeBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPanel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveLogBtn).addComponent(consoleTypeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(clearBtn)).addContainerGap()));

		this.addWindowListener(new CustomWindowListener()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				Launcher.openOrCloseConsole(false);
			}
		});
		pack();
	}
	
	public void updateComponents()
	{
		saveLogBtn.setText(LanguageUtils.getTranslated("launcher.console.savelog"));
		clearBtn.setText(LanguageUtils.getTranslated("launcher.console.clear"));
		consoleTypeBox.setModel(new DefaultComboBoxModel(new String[] { LanguageUtils.getTranslated("launcher.console.box.all"), LanguageUtils.getTranslated("launcher.console.box.launcher"), LanguageUtils.getTranslated("launcher.console.box.minecraft") }));
	}

	private JButton saveLogBtn;
	private JButton clearBtn;
	private JComboBox consoleTypeBox;
	private JTextArea consolePanel;
	private JScrollPane scrollPanel;
}
