package it.planetgeeks.mclauncher.frames.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * @author PlanetGeeks
 * 
 */

public class HintTextField extends JTextField implements FocusListener
{

	private static final long serialVersionUID = 1L;
	
	private String hint;
	private boolean showingHint;

	public HintTextField(final String hint)
	{
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText("");
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText(hint);
			showingHint = true;
		}
	}

	@Override
	public String getText()
	{
		return showingHint ? "" : super.getText();
	}
	
	public void setText(String str)
	{
		this.showingHint = false;
		super.setText(str);
	}
	
	public void setTip(String tip)
	{
		this.hint = tip;
	}
}