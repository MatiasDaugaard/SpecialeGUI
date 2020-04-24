package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editor.EditorFrame;

public class EditorButtonListener implements ActionListener{
	
	private MainFrame frame;
	
	public EditorButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new EditorFrame();
		frame.dispose();
		
	}

}
