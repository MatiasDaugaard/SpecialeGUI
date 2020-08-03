package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Listener for restart button in solution viewer
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
