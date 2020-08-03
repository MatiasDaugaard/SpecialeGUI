package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Listener for next step button in solution viewer
public class NextButtonListener implements ActionListener {

	private SolutionDrawingPanel panel;
	
	public NextButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panel.next();
			
		
	}

}
