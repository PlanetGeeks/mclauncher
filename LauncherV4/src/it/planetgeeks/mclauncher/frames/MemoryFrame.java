package it.planetgeeks.mclauncher.frames;

import it.planetgeeks.mclauncher.Launcher;
import it.planetgeeks.mclauncher.utils.LanguageUtils;
import it.planetgeeks.mclauncher.utils.Memory;
import it.planetgeeks.mclauncher.utils.MemoryUtils;
import it.planetgeeks.mclauncher.utils.Profile;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MemoryFrame extends JFrame
{

	private static final long serialVersionUID = 1L;

	private int parentFrame;
	private Object extra;

	public MemoryFrame(int parentFrame, Object extra)
	{
		this.parentFrame = parentFrame;
		initComponents();
		this.setSize(256, 340);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
	}

	private void initComponents()
	{

		textField = new JTextField();
		nameLbl = new JLabel();
		sizeLbl = new JLabel();
		spinnerSize = new JSpinner();
		btn1 = new JButton();
		btn2 = new JButton();
		scrollpane = new JScrollPane();
		list = new JList();
		separator = new JSeparator();
		this.setTitle(LanguageUtils.getTranslated("launcher.memory.title"));

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		this.addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent arg0)
			{
			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
			}

			@Override
			public void windowClosing(WindowEvent arg0)
			{
				if (parentFrame == 1)
				{
					Launcher.openProfileEditor((Profile) extra);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{
			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{
			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{
			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{
			}

		});

		nameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLbl.setText(LanguageUtils.getTranslated("launcher.memory.namelbl") + " :");

		sizeLbl.setText(LanguageUtils.getTranslated("launcher.memory.dimensionlbl") + " :");

		btn1.setText(LanguageUtils.getTranslated("launcher.memory.createbtn"));

		btn2.setText(LanguageUtils.getTranslated("launcher.memory.deletebtn"));

		scrollpane.setViewportView(list);
		
		setList();
		
		list.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{		
				Memory memory = MemoryUtils.getMem((String)list.getSelectedValue());
				textField.setText(memory.name);
				spinnerSize.setValue(memory.size);
			}
			
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(separator).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(nameLbl)).addGroup(layout.createSequentialGroup().addGap(14, 14, 14).addComponent(sizeLbl))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(textField).addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))).addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(btn1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE).addGap(24, 24, 24).addComponent(btn2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))).addGap(0, 12, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollpane).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(nameLbl)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(sizeLbl)).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btn1).addComponent(btn2)).addContainerGap(22, Short.MAX_VALUE)));

		pack();
	}

	private void setList()
	{
		list.setModel(new AbstractListModel()
		{

			private static final long serialVersionUID = 1L;
			String[] strings = MemoryUtils.getMemoryNames();

			public int getSize()
			{
				return strings.length;
			}

			public Object getElementAt(int i)
			{
				return strings[i];
			}
		});
	}

	private JButton btn1;
	private JButton btn2;
	private JLabel nameLbl;
	private JLabel sizeLbl;
	private JList list;
	private JScrollPane scrollpane;
	private JSeparator separator;
	private JSpinner spinnerSize;
	private JTextField textField;

}
