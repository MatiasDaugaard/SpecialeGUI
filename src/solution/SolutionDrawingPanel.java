package solution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

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
	private Timer timer;
	
	public SolutionDrawingPanel() {
		super();
		signalsSolution = new ArrayList<>();
		trainsSolution = new ArrayList<>();
		switchRailsSolution = new ArrayList<>();
		timer = new Timer(100, new TrainListener(this));
		counter = 0;
		
		
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
	    if(signalsSolution.size() == 0) {
			return;
		}
	    
	    Map<Signal,Boolean> signalMap = signalsSolution.get(counter);
	    for (Map.Entry<Signal, Boolean> entry : signalMap.entrySet()) {
	    	Signal sig = entry.getKey();
	    	int i = sig.getLocation()/WIDTH;
	    	int j = sig.getLocation()%WIDTH;
	    	int x = (5+j*xOffset)-5;
    		int y = (5+i*yOffset)-16;
    		System.out.println(sig.getLocation());
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
		if(switchRailsSolution.size() == 0) {
			super.drawSwitchRails(g);
			return;
		}
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
		if(trainsSolution.size() == 0) {
			return;
		}
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
	
	public void start() {
		timer.start();
		
		
		
	}
	
	public void stop() {
		timer.stop();
		
	}
	
	

	public void restart() {
		stop();
		counter = 0;
		repaint();
		
	}
	
	public void addState(List<Boolean> signals, List<Integer> trains, List<Boolean> rails) {
		
		int c = 0;
		Map<Train,Integer> trainMap = new HashMap<>();
		for(Train t : this.trainList) {
			trainMap.put(t, trains.get(c));
			c++;
		}
		trainsSolution.add(trainMap);
		
		
		Map<Signal,Boolean> signalMap = new HashMap<>();
		c = 0;
		for(Signal s : this.signalList) {
			signalMap.put(s, signals.get(c));
			c++;
		}
		signalsSolution.add(signalMap);


		
		Map<SwitchRail,Boolean> switchrailMap = new HashMap<>();
		c = 0;
		for(SwitchRail s : this.switchRailList) {
			switchrailMap.put(s, rails.get(c));
			c++;
		}
		switchRailsSolution.add(switchrailMap);
	}

	public void resetSolution() {
		trainsSolution = new ArrayList<>();
		signalsSolution = new ArrayList<>();
		switchRailsSolution = new ArrayList<>();
		counter = 0;
		repaint();
		
	}
	
	public void next() {
		stop();
		if(counter < trainsSolution.size() -1) {
			counter++;
			repaint();
        }
		
		
	}

	public void prev() {
		stop();
		if(counter > 0) {
			counter--;
			repaint();
        }
		
	}

	
	private class TrainListener implements ActionListener{

		private SolutionDrawingPanel panel;
		
		public TrainListener(SolutionDrawingPanel panel) {
			this.panel = panel;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(counter < trainsSolution.size() -1) {
            	panel.counter++;
            	panel.repaint();
            }else {
            	stop();
            }
			
		}
		
	}


}
