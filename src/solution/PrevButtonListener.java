package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevButtonListener implements ActionListener {

	private SolutionDrawingPanel panel;
	
	public PrevButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panel.prev();
			
		
	}

}
