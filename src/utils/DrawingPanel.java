package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	
	public static int WIDTH = 20;
	public static int HEIGHT = 20;
	
	protected int[][] rails = new int[HEIGHT][WIDTH];
	protected int[][] signals = new int[HEIGHT][WIDTH];
	protected List<Train> trainList;
	protected List<Rail> railList;
	protected List<Signal> signalList;
	protected List<SwitchRail> switchRailList;
	
	public static int xOffset = 0;
    public static int yOffset = 0;
	
	
    
    protected int highlightI = -1;
    protected int highlightJ = -1;
    
    public DrawingPanel() {
    	for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				rails[i][j] = 0;
				signals[i][j] = 0;
				
			}
		}
    	
		this.setBackground(Color.LIGHT_GRAY);
		trainList = new ArrayList<Train>();
		signalList = new ArrayList<Signal>();
		railList = new ArrayList<Rail>();
		switchRailList = new ArrayList<SwitchRail>();
		repaint();
    }
    
    @Override
	protected void paintComponent(Graphics g) {
    	
	    super.paintComponent(g);
	    
	    xOffset = (this.getSize().width-10)/(WIDTH-1);
	    yOffset = (this.getSize().height-10)/(HEIGHT-1);
	    
	    g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		int x = (5+j*xOffset);
	    		int y = (5+i*yOffset);
	    		if(rails[i][j] != 0) {
	    			g.setColor(Color.red);
	    			
	    		}else {
	    			g.setColor(Color.black);
	    		}
	    		g.fillOval(x, y, 6, 6);
	    	}
	    }
	    
	    
	    
	    drawRails(g);
	    drawSwitchRails(g);
	    drawSignals(g);
	    drawTrains(g);
	    drawHighlight(g);
	    
	    
	}
    
    
    private void drawRails(Graphics g) {
    	g.setColor(Color.red);
	    for(Rail rail : railList) {
	    	int startI = Math.floorDiv(rail.getStart(), WIDTH);
	    	int startJ = rail.getStart()%WIDTH;
	    	int endI = Math.floorDiv(rail.getEnd(), WIDTH);
	    	int endJ = rail.getEnd()%WIDTH;
	    	
	    	int startX = (5+startJ*xOffset)+3;
    		int startY = (5+startI*yOffset)+3;
    		int endX = (5+endJ*xOffset)+3;
    		int endY = (5+endI*yOffset)+3;
	    	
	    	g.drawLine(startX, startY, endX, endY);
	    }
    }
    
    protected void drawSwitchRails(Graphics g) {
    	for(SwitchRail rail : switchRailList) {
	    	int startI = Math.floorDiv(rail.getStart(), WIDTH);
	    	int startJ = rail.getStart()%WIDTH;
	    	int end1I = Math.floorDiv(rail.getEnd1(), WIDTH);
	    	int end1J = rail.getEnd1()%WIDTH;
	    	int end2I = Math.floorDiv(rail.getEnd2(), WIDTH);
	    	int end2J = rail.getEnd2()%WIDTH;
	    	
	    	int startX = (5+startJ*xOffset)+3;
    		int startY = (5+startI*yOffset)+3;
    		int end1X = (5+end1J*xOffset)+3;
    		int end1Y = (5+end1I*yOffset)+3;
    		int end2X = (5+end2J*xOffset)+3;
    		int end2Y = (5+end2I*yOffset)+3;
	    	
	    	g.drawLine(startX, startY, end1X, end1Y);
	    	g.drawLine(startX, startY, end2X, end2Y);
	    }
    }
    
    protected void drawSignals(Graphics g) {
    	g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		if(signals[i][j] == 1) {
	    			int x = (5+j*xOffset)-5;
		    		int y = (5+i*yOffset)-5;
		    		g.drawString("L", x, y);
	    		}else if(signals[i][j] == 2) {
	    			int x = (5+j*xOffset)+5;
		    		int y = (5+i*yOffset)-5;
		    		g.drawString("R", x, y);
	    		}else if(signals[i][j] == 3) {
	    			int x = (5+j*xOffset)+5;
		    		int y = (5+i*yOffset)-5;
		    		g.drawString("R", x, y);
		    		x = (5+j*xOffset)-5;
		    		g.drawString("L", x, y);
	    		}
	    	}
	    }
    }
    
    protected void drawTrains(Graphics g) {
    	int c = 0;
	    for(Train train : trainList) {
	    	int startI = Math.floorDiv(train.getStartLocation(), WIDTH);
	    	int startJ = train.getStartLocation()%WIDTH;
	    	int endI = Math.floorDiv(train.getEndLocation(), WIDTH);
	    	int endJ = train.getEndLocation()%WIDTH;
	    	
	    	int startX = (5+startJ*xOffset)-10;
    		int startY = (5+startI*yOffset)+15;
    		int endX = (5+endJ*xOffset)+10;
    		int endY = (5+endI*yOffset)+15;
	    	g.setColor(Color.blue);
	    	g.drawString("T" + c, startX, startY);
	    	g.drawString("t" + c, endX, endY);
	    	c++;
	    }
    }
    
    private void drawHighlight(Graphics g) {
    	g.setColor(Color.yellow);
	    if(highlightI >= 0) {
	    	int x = (5+highlightJ*xOffset);
    		int y = (5+highlightI*yOffset);
    		g.fillOval(x, y, 6, 6);
	    }
    }
    
    public void reset() {
    	for(int i = 0; i < HEIGHT; i++) {
    		for(int j = 0; j < WIDTH; j++) {
    			rails[i][j] = 0;
    			signals[i][j] = 0;
    		}
    	}
    	xOffset = (this.getSize().width-10)/(WIDTH-1);
	    yOffset = (this.getSize().height-10)/(HEIGHT-1);
    	trainList = new ArrayList<Train>();
    	railList = new ArrayList<Rail>();
    	switchRailList = new ArrayList<SwitchRail>();
    	repaint();
    }
    
    public int getWIDTH() {
		return WIDTH;
	}
	public int getHEIGHT() {
		return HEIGHT;
	}
	
	public int[][] getRails(){
		return rails;
	}
	public int[][] getSignals(){
		return signals;
	}
	public List<Train> getTrainList(){
		return trainList;
	}
	public List<Rail> getRailList() {
		return railList;
		
	}
	public List<SwitchRail> getSwitchRailList(){
		return switchRailList;
	}

	public void setWIDTH(int width) {
		WIDTH = width;
	}
	public void setHEIGHT(int height) {
		HEIGHT = height;
	}
	
	public void setRails(int[][] rails){
		this.rails = rails;
	}
	public void setSignals(int[][] signals){
		this.signals = signals;
	}
	public void setTrainList(List<Train> trainList){
		this.trainList = trainList;
	}
	public void setRailList(List<Rail> railList) {
		this.railList = railList;
		
	}
	public void setSwitchRailList(List<SwitchRail> switchRailList){
		this.switchRailList = switchRailList;
	}

	public void setSignalList(List<Signal> signalList) {
		this.signalList = signalList;
		
	}

}
