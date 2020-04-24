package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RailwayLoader {
	
	
	public RailwayLoader() {}
	
	public boolean loadRailway(DrawingPanel drawingPanel, String title) {
		
		File file = new File(title + ".txt");
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = reader.readLine();
		    //Load in locations
		    while (!line.equals(":")) {
		    	line = reader.readLine();
		    	
		    }
		    //Load in rails
		    List<Rail> railList = new ArrayList<>();
		    line = reader.readLine();
		    while (!line.equals(":")) {
		    	String[] s = line.split(" ");
		    	int start = Integer.parseInt(s[0]);
		    	int end = Integer.parseInt(s[1]);
		    	Rail r = new Rail(start,end);
		    	railList.add(r);
		    	line = reader.readLine();
		    }
		    //Load in switch rails
		    List<SwitchRail> switchRailList = new ArrayList<>();
		    line = reader.readLine();
		    while (!line.equals(":")) {
		    	String[] s = line.split(" ");
		    	int start = Integer.parseInt(s[0]);
		    	int end1 = Integer.parseInt(s[1]);
		    	int end2 = Integer.parseInt(s[2]);
		    	Direction d = s[3] == "L" ? Direction.Left : Direction.Right;
		    	SwitchRail r = new SwitchRail(start,end1, end2, d);
		    	switchRailList.add(r);
		    	line = reader.readLine();
		    }
		    //Load in signals
		    List<Integer> signalLoc = new ArrayList<>();
		    List<Direction> signalDir = new ArrayList<>();
		    line = reader.readLine();
		    while (!line.equals(":")) {
		    	String[] s = line.split(" ");
		    	int loc = Integer.parseInt(s[0]);
		    	Direction d = s[1].equals("L") ? Direction.Left : Direction.Right;
		    	signalLoc.add(loc);
		    	signalDir.add(d);
		    	line = reader.readLine();
		    }
		    //Load in trains
		    List<Train> trainList = new ArrayList<>();
		    line = reader.readLine();
		    while (!line.equals(":")) {
		    	String[] s = line.split(" ");
		    	int start = Integer.parseInt(s[1]);
		    	int end = Integer.parseInt(s[2]);
		    	Direction d = s[3].equals("L") ? Direction.Left : Direction.Right;
		    	Train t = new Train(start,end,d);
		    	trainList.add(t);
		    	line = reader.readLine();
		    }
		    line = reader.readLine();
		    int width = Integer.parseInt(line);
		    line = reader.readLine();
		    int height = Integer.parseInt(line);
		    int[][] signals = new int[height][width];
		    for(int a = 0; a < signalLoc.size(); a++) {
		    	int x = signalLoc.get(a);
		    	int i = Math.floorDiv(x, width);
		    	int j = x%width;
		    	if(signals[i][j] != 0) {
		    		signals[i][j] = 3;
		    	}else {
		    		if(signalDir.get(a) == Direction.Left) {
		    			signals[i][j] = 1;
		    		}else {
		    			signals[i][j] = 2;
		    		}
		    	}
		    	
		    }
		    int[][] rails = new int[height][width];
		    for(Rail r : railList) {
		    	int i = Math.floorDiv(r.getStart(), width);
		    	int j = r.getStart()%width;
		    	rails[i][j]++;
		    	i = Math.floorDiv(r.getEnd(), width);
		    	j = r.getEnd()%width;
		    	rails[i][j]++;
		    }
		    
		    for(SwitchRail r : switchRailList) {
		    	int i = Math.floorDiv(r.getStart(), width);
		    	int j = r.getStart()%width;
		    	rails[i][j] = 3;
		    	i = Math.floorDiv(r.getEnd1(), width);
		    	j = r.getEnd1()%width;
		    	rails[i][j] = 4;
		    	i = Math.floorDiv(r.getEnd2(), width);
		    	j = r.getEnd2()%width;
		    	if(r.getDirection() == Direction.Left) {
		    		rails[i][j] = 5;
		    	}else {
		    		rails[i][j] = 6;
		    	}
		    }
		    
		    drawingPanel.reset();
		    drawingPanel.setWIDTH(width);
		    drawingPanel.setHEIGHT(height);
		    drawingPanel.setRails(rails);
			drawingPanel.setSignals(signals);
			drawingPanel.setTrainList(trainList);
			drawingPanel.setRailList(railList);
			drawingPanel.setSwitchRailList(switchRailList);
		    
		    drawingPanel.repaint();
		    reader.close();
		    return true;
		} catch (Exception ex) {
		    return false;
		} 
		
	}
	
	public Map<List<Train>,Integer> loadSolution(String title) {
		return null;
		
	}
}
