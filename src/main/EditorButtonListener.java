package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import editor.EditorFrame;

public class EditorButtonListener implements ActionListener{
	
	private MainFrame frame;
	
	public EditorButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int height = 0;
		int width = 0;
		
		while(height <= 4) {
			try {
				height = Integer.parseInt(JOptionPane.showInputDialog("Height of railway (minimum 5): "));
			}catch(Exception ex){
				JOptionPane.showMessageDialog(frame,"Please enter valid number");  
			}
		}
		
		while(width <= 4) {
			try {
				width = Integer.parseInt(JOptionPane.showInputDialog("Width of railway (minimum 5): "));
			}catch(Exception ex){
				JOptionPane.showMessageDialog(frame,"Please enter valid number");  
			}
		}

		new EditorFrame(height,width);
		frame.dispose();
		
	}

}
