package editor;


import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.MenuPanel;

public class TextFieldListener implements DocumentListener{

	private MenuPanel menuPanel;
	private JTextField tf;
	
	public TextFieldListener(MenuPanel menuPanel, JTextField tf) {
		this.menuPanel = menuPanel;
		this.tf = tf;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		menuPanel.setFilename(tf.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		menuPanel.setFilename(tf.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		menuPanel.setFilename(tf.getText());
		
	}
	
}
