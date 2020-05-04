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
		Map<Signal,Boolean> sigMap = new HashMap<Signal,Boolean>();
		sigMap.put(new Signal(85,Direction.Right), false);
		sigMap.put(new Signal(88,Direction.Left), true);
		signalsSolution.add(sigMap);
		Map<SwitchRail,Boolean> srMap = new HashMap<SwitchRail,Boolean>();
		srMap.put(new SwitchRail(85,86,66,Direction.Right), true);
		srMap.put(new SwitchRail(88,87,67,Direction.Left), false);
		switchRailsSolution.add(srMap);

		
		
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
		    		int y = (5+i*yOffset)-10;
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 2) {
	    			int x = (5+j*xOffset)+6;
		    		int y = (5+i*yOffset)-10;
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 3) {
	    			int x = (5+j*xOffset)+6;
		    		int y = (5+i*yOffset)-10;
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
    		int y = (5+i*yOffset)-10;
	    	if(entry.getValue()) {
	    		g.setColor(Color.green);
	    	}else {
	    		g.setColor(Color.red);
	    		y = (5+i*yOffset)-5;
	    	}
	    	if(sig.getDirection() == Direction.Right) {
	    		x = (5+j*xOffset)+6;
	    	}
	    	g.fillArc(x+1, y+1, 3, 3, 0, 360);
	    	
	    }
	}
	
	@Override
	protected void drawSwitchRails(Graphics g) {
		g.setColor(Color.red);
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
		
	}

}
