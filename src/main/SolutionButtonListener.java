package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import solution.SolutionFrame;
//Lister for button to go to solution viewer
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
