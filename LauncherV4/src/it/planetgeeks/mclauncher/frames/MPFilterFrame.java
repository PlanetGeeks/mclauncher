package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.Settings;
import it.planetgeeks.mclauncher.frames.utils.CustomJPanel;
import it.planetgeeks.mclauncher.modpack.EnumFilterType;
import it.planetgeeks.mclauncher.modpack.ModPackUtils;
import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * @author PlanetGeeks
 *
 */

public class MPFilterFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private EnumFilterType currentFilter;
	private String currentValue;

	public MPFilterFrame()
	{
		initComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);

	}

	private void initComponents()
	{

		currentFilter = ModPackUtils.filter;
		currentValue = ModPackUtils.filterStr;

		CustomJPanel panel = new CustomJPanel(true, "bg.png");
		panel.setOpaque(false);
		setContentPane(panel);

		setIconImage(Launcher.getResources().getResource("icon.png").getImage());
		
		this.setResizable(false);

		setTitle(LanguageUtils.getTranslated("launcher.modpacks.filter.settings"));

		typeLbl = new JLabel();
		comboBoxType = new JComboBox<String>();
		textField = new JTextField();
		valueLbl = new JLabel();
		discardBtn = new JButton();
		applyBtn = new JButton();

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		typeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		typeLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.filterframe.type") + " :");

		String values[] = new String[EnumFilterType.values().length];

		for (int i = 0; i < values.length; i++)
		{
			values[i] = LanguageUtils.getTranslated(EnumFilterType.values()[i].str);
		}

		comboBoxType.setModel(new DefaultComboBoxModel<String>(values));

		comboBoxType.setSelectedItem(LanguageUtils.getTranslated(currentFilter.str));

		valueLbl.setHorizontalAlignment(SwingConstants.RIGHT);

		valueLbl.setText(LanguageUtils.getTranslated("launcher.modpacks.filterframe.value") + " :");

		discardBtn.setText(LanguageUtils.getTranslated("launcher.modpacks.filterframe.discard"));

		applyBtn.setText(LanguageUtils.getTranslated("launcher.modpacks.filterframe.apply"));

		discardBtn.setForeground(new Color(Settings.buttonsForeground));

		applyBtn.setForeground(new Color(Settings.buttonsForeground));

		comboBoxType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				currentFilter = EnumFilterType.values()[comboBoxType.getSelectedIndex()];
				updateComponents();
			}
		});

		if (currentValue != null && !currentValue.equals(""))
		{
			textField.setText(currentValue);
		}
		
		applyBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				close(true);
			}
		});
		
		discardBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				close(false);
			}
		});
		

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(discardBtn, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(applyBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(typeLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(valueLbl, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(comboBoxType, 0, 1, Short.MAX_VALUE).addComponent(textField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))).addContainerGap(14, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeLbl).addComponent(comboBoxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(valueLbl)).addGap(24, 24, 24).addComponent(applyBtn).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(discardBtn).addContainerGap(18, Short.MAX_VALUE)));

		updateComponents();

		pack();
	}

	private void updateComponents()
	{
		if (currentFilter == EnumFilterType.ALL || currentFilter == EnumFilterType.HASSERVER || currentFilter == EnumFilterType.DOWNLOADED)
		{
			valueLbl.setEnabled(false);
			textField.setEnabled(false);
		}
		else
		{
			valueLbl.setEnabled(true);
			textField.setEnabled(true);
		}
	}
	
	private void close(boolean apply)
	{
		if(apply)
		{
		    ModPackUtils.filter = currentFilter;
		    ModPackUtils.filterStr = textField.getText();
		    Launcher.getLauncherFrame().mainPanel.updateModPacks(ModPackUtils.getAllPacks(), Launcher.getLauncherFrame().mainPanel.loadingModPack);
			
		}
		Launcher.openOrCloseFilterFrame();
	}

	private JButton discardBtn;
	private JButton applyBtn;
	private JComboBox<String> comboBoxType;
	private JLabel typeLbl;
	private JLabel valueLbl;
	private JTextField textField;
}
