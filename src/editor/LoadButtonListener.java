package editor;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Direction;
import utils.RailwayLoader;
import utils.Rail;
import utils.SwitchRail;
import utils.Train;




public class LoadButtonListener implements ActionListener{

	private EditorMenuPanel menuPanel;
	private EditorDrawingPanel drawingPanel;
	
	public LoadButtonListener(EditorMenuPanel menuPanel, EditorDrawingPanel drawingPanel) {
		this.menuPanel = menuPanel;
		this.drawingPanel = drawingPanel;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String title = menuPanel.getTitle();
		
		RailwayLoader loader = new RailwayLoader();
		if(loader.loadRailway(drawingPanel, title)) {
			menuPanel.saveLoadLabel.setText("Success");
		}else {
			menuPanel.saveLoadLabel.setText("Failed to load railway");
		}
		
		
		    
		
		
	}

}
