/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author koumudi
 */
public class JHintTextField extends JTextField implements FocusListener{
	
	private String hint;
	
	public JHintTextField() {
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(this.getText().toString().equals(hint)) {
			this.setText("");
			this.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().equals("")) {
			this.setHintText(this.hint);
		}
	}

	public void setHintText(String hint) {
		this.hint = hint;
		this.setText(hint);
		this.setForeground(Color.LIGHT_GRAY);
	}

    
}