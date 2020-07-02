package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import solution.SolutionDrawingPanel;

public class RailwayLoader {
	
	
	public RailwayLoader() {}
	
	public boolean loadRailway(DrawingPanel drawingPanel, String title, MenuPanel menuPanel) {
		Path currentRelativePath = Paths.get("");
		String p = currentRelativePath.toAbsolutePath().toString()+"/Railways/";
		File file = new File(p+title + ".txt");
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
		    	Direction d = s[3].equals("L") ? Direction.Left : Direction.Right;
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
		    List<Signal> signalList = new ArrayList<>();
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
		    	signalList.add(new Signal(signalLoc.get(a),signalDir.get(a)));
		    	
		    	
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
			drawingPanel.setSignalList(signalList);
			drawingPanel.setRailList(railList);
			drawingPanel.setSwitchRailList(switchRailList);
		    
		    drawingPanel.repaint();
		    reader.close();
		    menuPanel.loaded();
		    return true;
		} catch (Exception ex) {
			System.err.print(ex);
		    return false;
		} 
		
	}
	
	public void loadSolution(String title, SolutionDrawingPanel drawingPanel, MenuPanel menuPanel) {
		
		Path currentRelativePath = Paths.get("");
		String p = currentRelativePath.toAbsolutePath().toString()+"/Railways/";
		File file = new File(p+title + ".sol");
		BufferedReader reader = null;
		
		int length = 1;
		int generated = 1;
		double time = 1.0;
		
		Pattern trainPattern = Pattern.compile("<trains>(.*)");
		Pattern statsPattern = Pattern.compile("<states>(.*)");
		drawingPanel.resetSolution();
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = reader.readLine();
		    while(line != null) {
		    	Matcher tMatcher = trainPattern.matcher(line);
		    	if(tMatcher.find()) {
		    		String trains = line;
	    			line = reader.readLine();
	    			String signals = line;
	    			line = reader.readLine();
	    			String rails = line;
	    			line = reader.readLine();
		    		
		    		trains = trains.substring(9,trains.length()-1).replaceAll("\\s","");
		    		signals = signals.substring(10,signals.length()-1).replaceAll("\\s","");
		    		rails = rails.substring(8,rails.length()-1).replaceAll("\\s","");
		    		
		    		String[] trainArray = trains.split(";");
		    		List<Integer> trainList = new ArrayList<>();
		    		String[] signalArray = signals.split(";");
		    		List<Boolean> signalList = new ArrayList<>();
		    		String[] railArray = rails.split(";");
		    		List<Boolean> railList = new ArrayList<>();
		    		
		    		for(String s : trainArray) {
		    			trainList.add((Integer.parseInt(s)));
		    		}
		    		
		    		for(String s : signalArray) {
		    			if(s.equals("F")) {
		    				signalList.add(false);
		    			}else {
		    				signalList.add(true);
		    			}
		    		}
		    		for(String s : railArray) {
		    			if(s.equals("F")) {
		    				railList.add(false);
		    			}else {
		    				railList.add(true);
		    			}
		    		}
		    		
		    		drawingPanel.addState(signalList, trainList, railList);

		    	}else {
		    		Matcher statsMatcher = statsPattern.matcher(line);
		    		if(statsMatcher.find()) {
		    			String states = line;
		    			line = reader.readLine();
		    			String genStates = line;
		    			line = reader.readLine();
		    			String solTime = line;
		    			line = reader.readLine();
			    		
			    		states = states.substring(9).replaceAll("\\s","");
			    		genStates = genStates.substring(12).replaceAll("\\s","");
			    		solTime = solTime.substring(7).replaceAll("\\s","");
			    		
			    		length = Integer.parseInt(states);
			    		generated = Integer.parseInt(genStates);
			    		time = Double.parseDouble(solTime);
		    		}else {
		    			line = reader.readLine();
		    		}
		    		
		    	}
		    	
		    }
		    menuPanel.loaded(length, generated, time);
		    
		    reader.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		
	}
}
