package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButtonListener implements ActionListener{

	private SolutionDrawingPanel panel;
	
	public RestartButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.restart();
		
	}

}
