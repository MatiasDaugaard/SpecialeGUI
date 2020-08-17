package editor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Direction;
import utils.Rail;
import utils.Signal;
import utils.SwitchRail;
import utils.Train;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveButtonListener implements ActionListener{

	private EditorDrawingPanel drawingPanel;
	private EditorMenuPanel menuPanel;
	
	public SaveButtonListener(EditorMenuPanel menuPanel, EditorDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.menuPanel = menuPanel;
	}
	
	// Function to save railway, and find solution
	@Override
	public void actionPerformed(ActionEvent e) {
		String title = menuPanel.getFilename();

		try {
			// Before trying to save check if railway is legal
			railwayIsLegal(drawingPanel.getTrainList(), drawingPanel.getSwitchRailList(), drawingPanel.getSignalList(),drawingPanel.getRailList());
			
			// Create the save file
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString() + "/Railways/";
			File myObj = new File(s + title + ".txt");
			if (myObj.createNewFile()) {
				FileWriter writer = new FileWriter(s + title + ".txt");
				Set<Integer> locations = new HashSet<Integer>();
				List<Rail> railList = drawingPanel.getRailList();
				List<SwitchRail> switchRailList = drawingPanel.getSwitchRailList();
				
				// Get all the locations from the rails and switch-rails
				for (Rail r : railList) {
					locations.add(r.getStart());
					locations.add(r.getEnd());
				}
				for (SwitchRail r : switchRailList) {
					locations.add(r.getStart());
					locations.add(r.getEnd1());
					locations.add(r.getEnd2());
				}
				
				// Sort the locations in order smallest to largest, and write them to the file
				List<Integer> locationList = new ArrayList<>();
				for (Integer i : locations) {
					locationList.add(i);
				}
				locationList.sort(null);
				for (Integer i : locationList) {
					writer.write(i + "\n");
				}
				writer.write(":\n");
				
				// Sort the rails in order smallest to largest, and write them to file
				railList.sort(null);
				for (Rail rail : railList) {
					writer.write(rail.getStart() + " " + rail.getEnd() + "\n");
				}
				writer.write(":\n");
				
				// Sort the switch-rails in order smallest to largest, and write them to file
				switchRailList.sort(null);
				for (SwitchRail rail : switchRailList) {
					writer.write(rail.getStart() + " " + rail.getEnd1() + " " + rail.getEnd2() + " " + rail.getDirection().toString() + "\n");
				}
				writer.write(":\n");
				
				// Sort the signals in order smallest to largest, if same left to right, and write them to file
				List<Signal> signalList = drawingPanel.getSignalList();
				signalList.sort(null);
				for (Signal sig : signalList) {
					writer.write(sig.getLocation() + " " + sig.getDirection().toString() + "\n");
				}
				writer.write(":\n");
				
				// Write the trains to the file
				List<Train> trainList = drawingPanel.getTrainList();
				int c = 0;
				for (Train t : trainList) {
					writer.write("T" + c + " " + t.getStartLocation() + " " + t.getEndLocation() + " " + t.getDirection() + "\n");
					c++;
				}
				
				// Write the height and width to file
				writer.write(":\n");
				writer.write(drawingPanel.getWIDTH() + "\n");
				writer.write(drawingPanel.getHEIGHT() + "\n");
				writer.close();

				
				// Try to find a solution to the railway
				String p = currentRelativePath.toAbsolutePath().toString() + "/Solver/";
				try {
					ProcessBuilder pb = new ProcessBuilder("/Library/Frameworks/Mono.framework/Versions/Current/Commands/mono", 
							p + "/Program.exe",title, s);

					Process process = pb.start();
					int exitValue = process.waitFor();
					if (exitValue != 0) {
						menuPanel.setLabelMessage("File saved - No solution found");
					} else {
						menuPanel.setLabelMessage("File saved - Solution found");
					}
				} catch (IOException | InterruptedException ex) {
					ex.printStackTrace();
				}

			} else {
				menuPanel.setLabelMessage("File already exists");
			}
		} catch (IOException ex) {
			menuPanel.setLabelMessage("An error occurred");
		} catch (Exception ex) {
			menuPanel.setLabelMessage("Railway not constructed correctly");
			ex.printStackTrace();
		}

	}
	// Function to check if a railway is legal. Signals are placed at needed locations.
	private void railwayIsLegal(List<Train> trains, List<SwitchRail> switchrails, List<Signal> signals, List<Rail> rails) throws Exception {
		// Check if a signal is present at the starting location for each train, and if signal is present or is end of rail at end location
		for(Train t : trains) {
			int s = t.getStartLocation();
			int e = t.getEndLocation();
			Direction d = t.getDirection();
			if (!(signals.contains(new Signal(s,d)))) {
				throw new Exception();
			}
			int lx = d == Direction.Left ? e-1 : e+1;
			Rail r = new Rail(e,lx);
			if (!signals.contains(new Signal(e,d))) {
				if (rails.contains(r)) {
					throw new Exception();
				}else {
					boolean b = false;
					for(SwitchRail sr : switchrails) {
						int l1 = sr.getStart();
						int l2 = sr.getEnd1();
						int l3 = sr.getEnd2();
						Direction dx = sr.getDirection();
						if (l1 == e && dx == d) {
							b = true;
							break;
						}else if ((l2 == e || l3 == e) && d != dx) {
							b = true;
							break;
						}
					}
					if (b) {
						throw new Exception();
					}
				}
					
			}
					
		}
		// Checks if signals are present at switch-rails
		for(SwitchRail sr : switchrails) {
			int l1 = sr.getStart();
			int l2 = sr.getEnd1();
			int l3 = sr.getEnd2();
			Direction d = sr.getDirection();
			Direction opd = d == Direction.Left ? Direction.Right : Direction.Left;
			
			if (!(signals.contains(new Signal(l1,d)) && signals.contains(new Signal(l2,opd))&& signals.contains(new Signal(l3,opd)))) {
				throw new Exception();
			}		
		}
		
		
	}
}
