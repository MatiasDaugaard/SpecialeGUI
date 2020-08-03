package editor;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import solution.SolutionDrawingPanel;
import utils.DrawingPanel;
import utils.MenuPanel;
import utils.RailwayLoader;



//Listener for load railway button
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
		if(menuPanel instanceof EditorMenuPanel) {
			title = menuPanel.getFilenameLoad();
		}
		
		RailwayLoader loader = new RailwayLoader();
		if(loader.loadRailway(drawingPanel, title, menuPanel)) {
			if(drawingPanel instanceof SolutionDrawingPanel) {
				
				loader.loadSolution(title, (SolutionDrawingPanel)drawingPanel, menuPanel);
				
			}
			menuPanel.setLabelMessage("Success");
		}else {
			menuPanel.setLabelMessage("Failed to load railway");
		}
		
		
		    
		
		
	}

}
