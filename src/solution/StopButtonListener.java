package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Listener for stop button in solution viewer
public class StopButtonListener implements ActionListener {

	private SolutionDrawingPanel panel;
	
	public StopButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.stop();
		
	}

}
