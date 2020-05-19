package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.MainFrame;

public class BackButtonListener implements ActionListener{
	
	private JFrame frame;

	public BackButtonListener(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new MainFrame();
		frame.dispose();
		
	}

}
