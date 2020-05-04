package solution;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Direction;
import utils.DrawingPanel;
import utils.Signal;
import utils.SwitchRail;
import utils.Train;

public class SolutionDrawingPanel extends DrawingPanel{
	
	private List<Map<Signal,Boolean>> signalsSolution;
	private List<Map<Train,Integer>> trainsSolution;
	private List<Map<SwitchRail,Boolean>> switchRailsSolution;
	
	private int counter;
	
	public SolutionDrawingPanel() {
		super();
		signalsSolution = new ArrayList<>();
		trainsSolution = new ArrayList<>();
		switchRailsSolution = new ArrayList<>();
		counter = 0;
		//TODO : remove this below when loading in solution
		Map<Signal,Boolean> sigMap1 = new HashMap<Signal,Boolean>();
		sigMap1.put(new Signal(82,Direction.Right), false);
		sigMap1.put(new Signal(83,Direction.Right), false);
		signalsSolution.add(sigMap1);
		Map<SwitchRail,Boolean> srMap1 = new HashMap<SwitchRail,Boolean>();
		srMap1.put(new SwitchRail(83,84,64,Direction.Right), true);
		switchRailsSolution.add(srMap1);
		Map<Train,Integer> trainMap1 = new HashMap<Train,Integer>();
		trainMap1.put(new Train(82,65,Direction.Right),82);
		trainsSolution.add(trainMap1);
		
		Map<Signal,Boolean> sigMap2 = new HashMap<Signal,Boolean>();
		sigMap2.put(new Signal(82,Direction.Right), true);
		sigMap2.put(new Signal(83,Direction.Right), false);
		signalsSolution.add(sigMap2);
		Map<SwitchRail,Boolean> srMap2 = new HashMap<SwitchRail,Boolean>();
		srMap2.put(new SwitchRail(83,84,64,Direction.Right), true);
		switchRailsSolution.add(srMap2);
		Map<Train,Integer> trainMap2 = new HashMap<Train,Integer>();
		trainMap2.put(new Train(82,65,Direction.Right),82);
		trainsSolution.add(trainMap2);
		
		Map<Signal,Boolean> sigMap3 = new HashMap<Signal,Boolean>();
		sigMap3.put(new Signal(82,Direction.Right), false);
		sigMap3.put(new Signal(83,Direction.Right), false);
		signalsSolution.add(sigMap3);
		Map<SwitchRail,Boolean> srMap3 = new HashMap<SwitchRail,Boolean>();
		srMap3.put(new SwitchRail(83,84,64,Direction.Right), true);
		switchRailsSolution.add(srMap3);
		Map<Train,Integer> trainMap3 = new HashMap<Train,Integer>();
		trainMap3.put(new Train(82,65,Direction.Right),83);
		trainsSolution.add(trainMap3);
		
		Map<Signal,Boolean> sigMap4 = new HashMap<Signal,Boolean>();
		sigMap4.put(new Signal(82,Direction.Right), false);
		sigMap4.put(new Signal(83,Direction.Right), true);
		signalsSolution.add(sigMap4);
		Map<SwitchRail,Boolean> srMap4 = new HashMap<SwitchRail,Boolean>();
		srMap4.put(new SwitchRail(83,84,64,Direction.Right), false);
		switchRailsSolution.add(srMap4);
		Map<Train,Integer> trainMap4 = new HashMap<Train,Integer>();
		trainMap4.put(new Train(82,65,Direction.Right),83);
		trainsSolution.add(trainMap4);
		
		Map<Signal,Boolean> sigMap5 = new HashMap<Signal,Boolean>();
		sigMap5.put(new Signal(82,Direction.Right), false);
		sigMap5.put(new Signal(83,Direction.Right), true);
		signalsSolution.add(sigMap5);
		Map<SwitchRail,Boolean> srMap5 = new HashMap<SwitchRail,Boolean>();
		srMap5.put(new SwitchRail(83,84,64,Direction.Right), false);
		switchRailsSolution.add(srMap5);
		Map<Train,Integer> trainMap5 = new HashMap<Train,Integer>();
		trainMap5.put(new Train(82,65,Direction.Right),64);
		trainsSolution.add(trainMap5);
		
		Map<Signal,Boolean> sigMap6 = new HashMap<Signal,Boolean>();
		sigMap6.put(new Signal(82,Direction.Right), false);
		sigMap6.put(new Signal(83,Direction.Right), true);
		signalsSolution.add(sigMap6);
		Map<SwitchRail,Boolean> srMap6 = new HashMap<SwitchRail,Boolean>();
		srMap6.put(new SwitchRail(83,84,64,Direction.Right), false);
		switchRailsSolution.add(srMap6);
		Map<Train,Integer> trainMap6 = new HashMap<Train,Integer>();
		trainMap6.put(new Train(82,65,Direction.Right),65);
		trainsSolution.add(trainMap6);

		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
    	
	    super.paintComponent(g);
	    
	    
	    
	    
	}
	
	@Override
	protected void drawSignals(Graphics g) {
		int width = 5;
		int height = 10;
		g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		if(signals[i][j] == 1) {
	    			int x = (5+j*xOffset)-5;
		    		int y = (5+i*yOffset)-16;
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 2) {
	    			int x = (5+j*xOffset)+6;
		    		int y = (5+i*yOffset)-16;
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 3) {
	    			int x = (5+j*xOffset)+6;
		    		int y = (5+i*yOffset)-16;
		    		g.fillRect(x, y, width, height);
		    		x = (5+j*xOffset)-5;
		    		g.fillRect(x, y, width, height);
	    		}
	    	}
	    }
	    
	    Map<Signal,Boolean> signalMap = signalsSolution.get(counter);
	    for (Map.Entry<Signal, Boolean> entry : signalMap.entrySet()) {
	    	Signal sig = entry.getKey();
	    	int i = sig.getLocation()/HEIGHT;
	    	int j = sig.getLocation()%WIDTH;
	    	int x = (5+j*xOffset)-5;
    		int y = (5+i*yOffset)-16;
	    	if(entry.getValue()) {
	    		g.setColor(Color.green);
	    	}else {
	    		g.setColor(Color.red);
	    		y = (5+i*yOffset)-11;
	    	}
	    	if(sig.getDirection() == Direction.Right) {
	    		x = (5+j*xOffset)+6;
	    	}
	    	g.fillArc(x+1, y+1, 3, 3, 0, 360);
	    	
	    }
	}
	
	@Override
	protected void drawSwitchRails(Graphics g) {
		Map<SwitchRail,Boolean> switchRailMap = switchRailsSolution.get(counter);
		for(Map.Entry<SwitchRail, Boolean> entry : switchRailMap.entrySet()) {
			SwitchRail switchRail = entry.getKey();
			int startI = Math.floorDiv(switchRail.getStart(), WIDTH);
	    	int startJ = switchRail.getStart()%WIDTH;
	    	int end1I = Math.floorDiv(switchRail.getEnd1(), WIDTH);
	    	int end1J = switchRail.getEnd1()%WIDTH;
	    	int end2I = Math.floorDiv(switchRail.getEnd2(), WIDTH);
	    	int end2J = switchRail.getEnd2()%WIDTH;
	    	
	    	int startX = (5+startJ*xOffset)+3;
    		int startY = (5+startI*yOffset)+3;
    		int end1X = (5+end1J*xOffset)+3;
    		int end1Y = (5+end1I*yOffset)+3;
    		int end2X = (5+end2J*xOffset)+3;
    		int end2Y = (5+end2I*yOffset)+3;
    		g.setColor(Color.red);
    		if(entry.getValue()) {
    			g.drawLine(startX, startY, end1X, end1Y);
    			g.setColor(Color.black);
    			g.drawLine(startX, startY, end2X, end2Y);
    		}else {
    			g.drawLine(startX, startY, end2X, end2Y);
    			g.setColor(Color.black);
    			g.drawLine(startX, startY, end1X, end1Y);
    		}
    		
	    	
    		
		}

	}
	
	@Override
	protected void drawTrains(Graphics g) {
		
		Map<Train,Integer> trainMap = trainsSolution.get(counter);
		for(Map.Entry<Train, Integer> entry : trainMap.entrySet()) {
			Train train = entry.getKey();
			int loc = entry.getValue();
			int i =  loc/WIDTH;
	    	int j = loc%WIDTH;
	    	int x = (5+j*xOffset);
    		int y = (5+i*yOffset)-5;
    		if(train.getDirection() == Direction.Right) {
    			x -= 15;
    		}else {
    			x += 6;
    		}
    		g.setColor(Color.blue);
    		g.fillRect(x, y, 15, 5);
		}

	}
	
	public void nextStep() {
		counter++;
		repaint();
	}

	public void restart() {
		counter = 0;
		repaint();
		
	}

}
