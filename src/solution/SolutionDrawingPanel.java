package solution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
	
	private BufferedImage imageRight;
	private BufferedImage imageLeft;
	
	public SolutionDrawingPanel() {
		super();
		signalsSolution = new ArrayList<>();
		trainsSolution = new ArrayList<>();
		switchRailsSolution = new ArrayList<>();
		timer = new Timer(100, new TrainListener(this));
		counter = 0;
		try {
			imageRight = ImageIO.read(new File("TrainRight.png"));
			imageLeft = ImageIO.read(new File("TrainLeft.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
    	
	    super.paintComponent(g);

	}
	
	@Override
	protected void drawSignals(Graphics g) {
		int scale = 1;
		int width = (int) (xOffset/4);
		int height = (int) (yOffset/2);
		if (width <= height/2) {
			height = width*2;
		}else if(height <= width*2) {
			width = height/2;
		}
		double h = height + (xOffset/2.5);
		g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		if(signals[i][j] == 1) {
	    			int x = (int)(5+j*xOffset)-(width);
		    		int y = (int) ((int)(5+i*yOffset)-(h));
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 2) {
	    			int x = (int)(5+j*xOffset)+6;
		    		int y = (int) ((int)(5+i*yOffset)-(h));
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 3) {
	    			int x = (int)(5+j*xOffset)+6;
		    		int y = (int) ((int)(5+i*yOffset)-(h));
		    		g.fillRect(x, y, width, height);
		    		x = (int)(5+j*xOffset)-width;
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
	    	int x;
    		int y = (int) ((int)(5+i*yOffset)-(h));
	    	if(entry.getValue()) {
	    		g.setColor(Color.green);
	    		y += height/10;
	    	}else {
	    		g.setColor(Color.red);
	    		y += (height/2)+(height/10);
	    	}
	    	if(sig.getDirection() == Direction.Right) {
	    		x = (int)(5+j*xOffset)+6 + width/5;
	    	}else {
	    		x = (int)(5+j*xOffset)-(width) + width/5;
	    	}
	    	int lightSize = width*3/5;
	    	g.fillArc(x, y, lightSize, lightSize, 0, 360);
	    	
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
	    	
	    	int startX = (int)(5+startJ*xOffset)+3;
    		int startY = (int)(5+startI*yOffset)+3;
    		int end1X = (int)(5+end1J*xOffset)+3;
    		int end1Y = (int)(5+end1I*yOffset)+3;
    		int end2X = (int)(5+end2J*xOffset)+3;
    		int end2Y = (int)(5+end2I*yOffset)+3;
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
		int h = (int) (xOffset/2.5);
		int w = (int) xOffset;
		if(h > yOffset/2) {
			h = (int) (yOffset/2);
			w = (int) (h*2.5);
		}
		Map<Train,Integer> trainMap = trainsSolution.get(counter);
		for(Map.Entry<Train, Integer> entry : trainMap.entrySet()) {
			Train train = entry.getKey();
			int loc = entry.getValue();
			int i =  loc/WIDTH;
	    	int j = loc%WIDTH;
	    	int x = (int) ((int)(5+j*xOffset)-w);
    		int y = (int) ((int)(5+i*yOffset)-h);
    		if(train.getDirection() == Direction.Right) {
    			g.drawImage(imageRight, x, y, w ,h, null);
    		}else {
    			x = (int) ((int)(5+j*xOffset)+6);
    			g.drawImage(imageLeft, x, y, w ,h, null);
    		}
    		
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
