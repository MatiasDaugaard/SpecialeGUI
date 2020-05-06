package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class StartButtonListener implements ActionListener{
	
	private SolutionDrawingPanel panel;
	
	public StartButtonListener(SolutionDrawingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO : Should run automatic 
		panel.start();
			
		
	}

}
