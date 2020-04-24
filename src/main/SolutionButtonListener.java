package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import editor.EditorFrame;
import solution.SolutionFrame;

public class SolutionButtonListener implements ActionListener{

	private MainFrame frame;
	
	
	public SolutionButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new SolutionFrame();
		frame.dispose();
		
	}

}
