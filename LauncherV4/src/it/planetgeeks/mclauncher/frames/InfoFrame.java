package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.utils.LanguageUtils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class InfoFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public InfoFrame()
	{
		initComponents();
		
	}

	private void initComponents()
	{
		this.setSize(300, 320);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
		this.setTitle(LanguageUtils.getTranslated("launcher.bar.info.info"));
		jSeparator1 = new JSeparator();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();

		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setText("Launcher sviluppato da 'Flood' di PlanetGeeks ");

		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setText("http://youtube.com/planetgeeks");

		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setText("label3");

		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setText("label4");

		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setText("label5");

		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setText("label6");

		label7.setHorizontalAlignment(SwingConstants.CENTER);
		label7.setText("label7");

		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setText("label8");

		label9.setHorizontalAlignment(SwingConstants.CENTER);
		label9.setText("label9");

		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jSeparator1).addComponent(label1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label6, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(17, 17, 17).addComponent(label3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(label4).addGap(18, 18, 18).addComponent(label5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(label6).addGap(18, 18, 18).addComponent(label7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(label8).addGap(18, 18, 18).addComponent(label9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(label1).addGap(18, 18, 18).addComponent(label2).addGap(12, 12, 12)));
	}

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JSeparator jSeparator1;
}
