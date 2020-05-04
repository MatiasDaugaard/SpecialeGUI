package editor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Rail;
import utils.SwitchRail;
import utils.Train;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButtonListener implements ActionListener{

	private EditorDrawingPanel drawingPanel;
	private EditorMenuPanel menuPanel;
	
	public SaveButtonListener(EditorMenuPanel menuPanel, EditorDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.menuPanel = menuPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String title = menuPanel.getFilename();
		try {
		      File myObj = new File(title + ".txt");
		      if (myObj.createNewFile()) {
		        FileWriter writer = new FileWriter(title + ".txt");
		        Set<Integer> locations = new HashSet<Integer>();
		        List<Rail> railList = drawingPanel.getRailList();
		        List<SwitchRail> switchRailList = drawingPanel.getSwitchRailList();
		        for(Rail r : railList) {
		        	locations.add(r.getStart());
		        	locations.add(r.getEnd());
		        }
		        for(SwitchRail r : switchRailList) {
		        	locations.add(r.getStart());
		        	locations.add(r.getEnd1());
		        	locations.add(r.getEnd2());
		        }
		        for(Integer i : locations) {
		        	writer.write(i+"\n");
		        }
		        writer.write(":\n");
		        for(Rail rail : railList) {
		        	writer.write(rail.getStart() + " " + rail.getEnd() +"\n");
		        }
		        writer.write(":\n");
		        for(SwitchRail rail : switchRailList) {
		        	writer.write(rail.getStart() + " " + rail.getEnd1() + " " +rail.getEnd2() + " " + rail.getDirection().toString() +"\n");
		        }
		        writer.write(":\n");
		        int width = drawingPanel.getWIDTH();
				int height = drawingPanel.getHEIGHT();
				int[][] signals = drawingPanel.getSignals();
				for(int i = 0; i < height; i++) {
					for(int j = 0; j < width; j++) {
						int x = i * width + j;
						if(signals[i][j] == 1) {
							writer.write(x + " L\n");
						}
						if(signals[i][j] == 2) {
							writer.write(x + " R\n");
						}
						if(signals[i][j] == 3) {
							writer.write(x + " L\n");
							writer.write(x + " R\n");
						}
					}
				}
				writer.write(":\n");
				List<Train> trainList = drawingPanel.getTrainList();
				int c = 0;
		        for(Train t : trainList) {
		        	writer.write("T" + c + " " + t.getStartLocation() + " " + t.getEndLocation() + " " + t.getDirection() +"\n");
		        	c++;
		        }
		        writer.write(":\n");
		        writer.write(width + "\n");
		        writer.write(height + "\n");
		        writer.close();
		        menuPanel.setLabelMessage("File saved");
		        
		      } else {
		    	  menuPanel.setLabelMessage("File already exists");
		      }
		    } catch (IOException ex) {
		    	menuPanel.setLabelMessage("An error occurred");
		    }
	}
}
