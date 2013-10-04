package it.planetgeeks.mclauncher.frames;

import java.awt.event.WindowEvent;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.frames.utils.CustomWindowListener;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class ConsoleFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public ConsoleFrame()
	{
		initComponents();
	}

	private void initComponents()
	{

		this.setTitle(LanguageUtils.getTranslated("launcher.console.title"));
		
		scrollPanel = new JScrollPane();
		consolePanel = new JEditorPane();
		saveLogBtn = new JButton();
		consoleTypeBox = new JComboBox();
		clearBtn = new JButton();

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		scrollPanel.setViewportView(consolePanel);

		saveLogBtn.setText(LanguageUtils.getTranslated("launcher.console.savelog"));

		consoleTypeBox.setModel(new DefaultComboBoxModel(new String[] { LanguageUtils.getTranslated("launcher.console.box.all"), LanguageUtils.getTranslated("launcher.console.box.launcher"), LanguageUtils.getTranslated("launcher.console.box.minecraft")}));

		clearBtn.setText(LanguageUtils.getTranslated("launcher.console.clear"));

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

	private JButton saveLogBtn;
	private JButton clearBtn;
	private JComboBox consoleTypeBox;
	private JEditorPane consolePanel;
	private JScrollPane scrollPanel;
}
