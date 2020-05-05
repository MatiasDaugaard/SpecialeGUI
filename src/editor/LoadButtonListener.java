package editor;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import solution.SolutionDrawingPanel;
import utils.DrawingPanel;
import utils.MenuPanel;
import utils.RailwayLoader;




public class LoadButtonListener implements ActionListener{

	private MenuPanel menuPanel;
	private DrawingPanel drawingPanel;
	
	public LoadButtonListener(MenuPanel menuPanel, DrawingPanel drawingPanel) {
		this.menuPanel = menuPanel;
		this.drawingPanel = drawingPanel;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String title = menuPanel.getFilename();
		
		RailwayLoader loader = new RailwayLoader();
		if(loader.loadRailway(drawingPanel, title, menuPanel)) {
			if(drawingPanel instanceof SolutionDrawingPanel) {
				
				loader.loadSolution(title, (SolutionDrawingPanel)drawingPanel);
			}
			menuPanel.setLabelMessage("Success");
		}else {
			menuPanel.setLabelMessage("Failed to load railway");
		}
		
		
		    
		
		
	}

}
