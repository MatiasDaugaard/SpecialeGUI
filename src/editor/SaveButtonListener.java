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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String title = menuPanel.getFilename();
		
		try {
			  Path currentRelativePath = Paths.get("");
			  String s = currentRelativePath.toAbsolutePath().toString()+"/Railways/";
		      File myObj = new File(s+title + ".txt");
		      if (myObj.createNewFile()) {
		        FileWriter writer = new FileWriter(s+title + ".txt");
		        Set<Integer> locations = new HashSet<Integer>();
		        List<Rail> railList = drawingPanel.getRailList();
		        List<SwitchRail> switchRailList = drawingPanel.getSwitchRailList();
		        railwayIsLegal(drawingPanel.getTrainList(),switchRailList,drawingPanel.getSignalList(),railList);
		        for(Rail r : railList) {
		        	locations.add(r.getStart());
		        	locations.add(r.getEnd());
		        }
		        for(SwitchRail r : switchRailList) {
		        	locations.add(r.getStart());
		        	locations.add(r.getEnd1());
		        	locations.add(r.getEnd2());
		        }
		        List<Integer> locationList = new ArrayList<>();
		        for(Integer i : locations) {
		        	locationList.add(i);
		        }
		        locationList.sort(null);
		        for(Integer i : locationList) {
		        	writer.write(i+"\n");
		        }
		        writer.write(":\n");
		        railList.sort(null);
		        for(Rail rail : railList) {
		        	writer.write(rail.getStart() + " " + rail.getEnd() +"\n");
		        }
		        writer.write(":\n");
		        switchRailList.sort(null);
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
		        
		        String p = currentRelativePath.toAbsolutePath().toString()+"/Solver/";
				try {
					ProcessBuilder pb = new ProcessBuilder("/Library/Frameworks/Mono.framework/Versions/Current/Commands/mono", p+"/Program.exe",title,s);
					
					Process process = pb.start();
					int exitValue = process.waitFor();
					if (exitValue != 0) {
						menuPanel.setLabelMessage("File saved - No solution found");
					}else {
						menuPanel.setLabelMessage("File saved - Solution found");
					}
				} catch (IOException | InterruptedException  ex) {
					ex.printStackTrace();
				} 
		        
		        
		      } else {
		    	  menuPanel.setLabelMessage("File already exists");
		      }
		    } catch (IOException ex) {
		    	menuPanel.setLabelMessage("An error occurred");
		    } catch (Exception ex) {
		    	menuPanel.setLabelMessage("Railway not constructed correctly");
		    }
		
		
	}

	private void railwayIsLegal(List<Train> trains, List<SwitchRail> switchrails, List<Signal> signals, List<Rail> rails) throws Exception {
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
