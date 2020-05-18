package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;

public class BackButtonListener implements ActionListener{
	
	private EditorFrame frame;

	public BackButtonListener(EditorFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new MainFrame();
		frame.dispose();
		
	}

}
