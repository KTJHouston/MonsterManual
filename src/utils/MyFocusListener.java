package utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class MyFocusListener implements FocusListener {

	private JTextField jtf;
	
	public MyFocusListener(JTextField jtf) {
		super();
		this.jtf = jtf;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		jtf.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		//ignore
	}
	
}
