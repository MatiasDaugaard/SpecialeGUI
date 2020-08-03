package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
//Listener for start button in solution viewer
public class StartButtonListener implements ActionListener{
	
	private SolutionDrawingPanel panel;
	
	public StartButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		panel.start();
			
		
	}

}
