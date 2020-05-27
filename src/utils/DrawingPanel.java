package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	
	protected int WIDTH = 100;
	protected int HEIGHT = 100;
	
	protected int[][] rails = new int[HEIGHT][WIDTH];
	protected int[][] signals = new int[HEIGHT][WIDTH];
	protected List<Train> trainList;
	protected List<Rail> railList;
	protected List<Signal> signalList;
	protected List<SwitchRail> switchRailList;
	
	public static double xOffset = 0;
    public static double yOffset = 0;
	
	
    
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
	    
	    xOffset = (this.getSize().width-20)/(WIDTH-1);
	    yOffset = (this.getSize().height-20)/(HEIGHT-1);
	    
	    g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		int x = (int)(5+j*xOffset);
	    		int y = (int)(5+i*yOffset);
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
	    	
	    	int startX = (int)(5+startJ*xOffset)+3;
    		int startY = (int)(5+startI*yOffset)+3;
    		int endX = (int)(5+endJ*xOffset)+3;
    		int endY = (int)(5+endI*yOffset)+3;
	    	
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
	    	
	    	int startX = (int)(5+startJ*xOffset)+3;
    		int startY = (int)(5+startI*yOffset)+3;
    		int end1X = (int)(5+end1J*xOffset)+3;
    		int end1Y = (int)(5+end1I*yOffset)+3;
    		int end2X = (int)(5+end2J*xOffset)+3;
    		int end2Y = (int)(5+end2I*yOffset)+3;
	    	
	    	g.drawLine(startX, startY, end1X, end1Y);
	    	g.drawLine(startX, startY, end2X, end2Y);
	    }
    }
    
    protected void drawSignals(Graphics g) {
    	g.setColor(Color.black);
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		if(signals[i][j] == 1) {
	    			int x = (int)(5+j*xOffset)-5;
		    		int y = (int)(5+i*yOffset)-5;
		    		g.drawString("L", x, y);
	    		}else if(signals[i][j] == 2) {
	    			int x = (int)(5+j*xOffset)+5;
		    		int y = (int)(5+i*yOffset)-5;
		    		g.drawString("R", x, y);
	    		}else if(signals[i][j] == 3) {
	    			int x = (int)(5+j*xOffset)+5;
		    		int y = (int)(5+i*yOffset)-5;
		    		g.drawString("R", x, y);
		    		x = (int)(5+j*xOffset)-5;
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
	    	
	    	int startX = (int)(5+startJ*xOffset)-10;
    		int startY = (int)(5+startI*yOffset)+15;
    		int endX = (int)(5+endJ*xOffset)+10;
    		int endY = (int)(5+endI*yOffset)+15;
	    	g.setColor(Color.blue);
	    	g.drawString("T" + c, startX, startY);
	    	g.drawString("t" + c, endX, endY);
	    	c++;
	    }
    }
    
    private void drawHighlight(Graphics g) {
    	g.setColor(Color.yellow);
	    if(highlightI >= 0) {
	    	int x = (int)(5+highlightJ*xOffset);
    		int y = (int)(5+highlightI*yOffset);
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
	
	public void increaseSize(Direction d) {
		int[][] newRails;
		int[][] newSignals;
		switch (d) {
			case Left:
				newRails = new int[HEIGHT][WIDTH+1];
				newSignals = new int[HEIGHT][WIDTH+1];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i][j+1]= rails[i][j];
						newSignals[i][j+1] = signals[i][j];
					}
					newRails[i][0] = 0;
					newSignals[i][0] = 0;
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
					int i = Math.floorDiv(t.getStartLocation(), WIDTH);
			    	t.setStartLocation(t.getStartLocation()+i+1);
					int m = Math.floorDiv(t.getEndLocation(), WIDTH);
					t.setEndLocation(t.getEndLocation()+m+1);
				}
				
				for(Rail r : railList) {
					int i = Math.floorDiv(r.getStart(), WIDTH);
					r.setStart(r.getStart()+i+1);
					int m = Math.floorDiv(r.getEnd(), WIDTH);
					r.setEnd(r.getEnd()+m+1);
				}
				
				for(Signal s : signalList) {
					int i = Math.floorDiv(s.getLocation(), WIDTH);
					s.setLocation(s.getLocation()+i+1);
				}
				for(SwitchRail sr : switchRailList) {
					int i = Math.floorDiv(sr.getStart(), WIDTH);
					sr.setStart(sr.getStart()+i+1);
					int m = Math.floorDiv(sr.getEnd1(), WIDTH);
					sr.setEnd1(sr.getEnd1()+m+1);
					int o = Math.floorDiv(sr.getEnd2(), WIDTH);
					sr.setEnd2(sr.getEnd2()+o+1);
				}
				
				
				WIDTH++;
				repaint();
				break;
			case Right :
				
				newRails = new int[HEIGHT][WIDTH+1];
				newSignals = new int[HEIGHT][WIDTH+1];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i][j]= rails[i][j];
						newSignals[i][j] = signals[i][j];
					}
					newRails[i][WIDTH] = 0;
					newSignals[i][WIDTH] = 0;
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
					int i = Math.floorDiv(t.getStartLocation(), WIDTH);
			    	t.setStartLocation(t.getStartLocation()+i);
					int m = Math.floorDiv(t.getEndLocation(), WIDTH);
					t.setEndLocation(t.getEndLocation()+m);
				}
				
				for(Rail r : railList) {
					int i = Math.floorDiv(r.getStart(), WIDTH);
					r.setStart(r.getStart()+i);
					int m = Math.floorDiv(r.getEnd(), WIDTH);
					r.setEnd(r.getEnd()+m);
				}
				
				for(Signal s : signalList) {
					int i = Math.floorDiv(s.getLocation(), WIDTH);
					s.setLocation(s.getLocation()+i);
				}
				for(SwitchRail sr : switchRailList) {
					int i = Math.floorDiv(sr.getStart(), WIDTH);
					sr.setStart(sr.getStart()+i);
					int m = Math.floorDiv(sr.getEnd1(), WIDTH);
					sr.setEnd1(sr.getEnd1()+m);
					int o = Math.floorDiv(sr.getEnd2(), WIDTH);
					sr.setEnd2(sr.getEnd2()+o);
				}
				
				WIDTH++;
				repaint();
				break;
			case Up:
				newRails = new int[HEIGHT+1][WIDTH];
				newSignals = new int[HEIGHT+1][WIDTH];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i+1][j]= rails[i][j];
						newSignals[i+1][j] = signals[i][j];
						newRails[0][j] = 0;
						newSignals[0][j] = 0;
					}
					
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
			    	t.setStartLocation(t.getStartLocation()+WIDTH);
					t.setEndLocation(t.getEndLocation()+WIDTH);
				}
				
				for(Rail r : railList) {
					r.setStart(r.getStart()+WIDTH);
					r.setEnd(r.getEnd()+WIDTH);
				}
				
				for(Signal s : signalList) {
					s.setLocation(s.getLocation()+WIDTH);
				}
				for(SwitchRail sr : switchRailList) {
					sr.setStart(sr.getStart()+WIDTH);
					sr.setEnd1(sr.getEnd1()+WIDTH);
					sr.setEnd2(sr.getEnd2()+WIDTH);
				}
				
				HEIGHT++;
				repaint();
				break;
			case Down:
				newRails = new int[HEIGHT+1][WIDTH];
				newSignals = new int[HEIGHT+1][WIDTH];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i][j]= rails[i][j];
						newSignals[i][j] = signals[i][j];
						newRails[HEIGHT][j] = 0;
						newSignals[HEIGHT][j] = 0;
					}
					
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				HEIGHT++;
				repaint();
				break;
			default:
				break;
		}
		
	}
	
	public void decreaseSize(Direction d) {
		int[][] newRails;
		int[][] newSignals;
		switch (d) {
			case Left:
				for(int i = 0; i < HEIGHT; i++) {
					if(rails[i][0] != 0) {
						return;
					}
				}
				newRails = new int[HEIGHT][WIDTH-1];
				newSignals = new int[HEIGHT][WIDTH-1];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH-1 ; j++) {
						newRails[i][j]= rails[i][j+1];
						newSignals[i][j] = signals[i][j+1];
					}
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
					int i = Math.floorDiv(t.getStartLocation(), WIDTH);
			    	t.setStartLocation(t.getStartLocation()-i-1);
					int m = Math.floorDiv(t.getEndLocation(), WIDTH);
					t.setEndLocation(t.getEndLocation()-m-1);
				}
				
				for(Rail r : railList) {
					int i = Math.floorDiv(r.getStart(), WIDTH);
					r.setStart(r.getStart()-i-1);
					int m = Math.floorDiv(r.getEnd(), WIDTH);
					r.setEnd(r.getEnd()-m-1);
				}
				
				for(Signal s : signalList) {
					int i = Math.floorDiv(s.getLocation(), WIDTH);
					s.setLocation(s.getLocation()-i-1);
				}
				for(SwitchRail sr : switchRailList) {
					int i = Math.floorDiv(sr.getStart(), WIDTH);
					sr.setStart(sr.getStart()-i-1);
					int m = Math.floorDiv(sr.getEnd1(), WIDTH);
					sr.setEnd1(sr.getEnd1()-m-1);
					int o = Math.floorDiv(sr.getEnd2(), WIDTH);
					sr.setEnd2(sr.getEnd2()-o-1);
				}
				
				
				WIDTH--;
				repaint();
				break;
			case Right :
				for(int i = 0; i < HEIGHT; i++) {
					if(rails[i][WIDTH-1] != 0) {
						return;
					}
				}
				newRails = new int[HEIGHT][WIDTH-1];
				newSignals = new int[HEIGHT][WIDTH-1];
				for(int i = 0 ; i < HEIGHT ; i++) {
					for(int j = 0 ; j < WIDTH-1 ; j++) {
						newRails[i][j]= rails[i][j];
						newSignals[i][j] = signals[i][j];
					}
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
					int i = Math.floorDiv(t.getStartLocation(), WIDTH);
			    	t.setStartLocation(t.getStartLocation()-i);
					int m = Math.floorDiv(t.getEndLocation(), WIDTH);
					t.setEndLocation(t.getEndLocation()-m);
				}
				
				for(Rail r : railList) {
					int i = Math.floorDiv(r.getStart(), WIDTH);
					r.setStart(r.getStart()-i);
					int m = Math.floorDiv(r.getEnd(), WIDTH);
					r.setEnd(r.getEnd()-m);
				}
				
				for(Signal s : signalList) {
					int i = Math.floorDiv(s.getLocation(), WIDTH);
					s.setLocation(s.getLocation()-i);
				}
				for(SwitchRail sr : switchRailList) {
					int i = Math.floorDiv(sr.getStart(), WIDTH);
					sr.setStart(sr.getStart()-i);
					int m = Math.floorDiv(sr.getEnd1(), WIDTH);
					sr.setEnd1(sr.getEnd1()-m);
					int o = Math.floorDiv(sr.getEnd2(), WIDTH);
					sr.setEnd2(sr.getEnd2()-o);
				}
				
				WIDTH--;
				repaint();
				break;
			case Up:
				for(int i = 0; i < WIDTH; i++) {
					if(rails[0][i] != 0) {
						return;
					}
				}
				newRails = new int[HEIGHT-1][WIDTH];
				newSignals = new int[HEIGHT-1][WIDTH];
				for(int i = 0 ; i < HEIGHT-1 ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i][j]= rails[i+1][j];
						newSignals[i][j] = signals[i+1][j];
					}
					
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				for(Train t : trainList) {
			    	t.setStartLocation(t.getStartLocation()-WIDTH);
					t.setEndLocation(t.getEndLocation()-WIDTH);
				}
				
				for(Rail r : railList) {
					r.setStart(r.getStart()-WIDTH);
					r.setEnd(r.getEnd()-WIDTH);
				}
				
				for(Signal s : signalList) {
					s.setLocation(s.getLocation()-WIDTH);
				}
				for(SwitchRail sr : switchRailList) {
					sr.setStart(sr.getStart()-WIDTH);
					sr.setEnd1(sr.getEnd1()-WIDTH);
					sr.setEnd2(sr.getEnd2()-WIDTH);
				}
				
				HEIGHT--;
				repaint();
				break;
			case Down:
				for(int i = 0; i < WIDTH; i++) {
					if(rails[HEIGHT-1][i] != 0) {
						return;
					}
				}
				newRails = new int[HEIGHT-1][WIDTH];
				newSignals = new int[HEIGHT-1][WIDTH];
				for(int i = 0 ; i < HEIGHT-1 ; i++) {
					for(int j = 0 ; j < WIDTH ; j++) {
						newRails[i][j]= rails[i][j];
						newSignals[i][j] = signals[i][j];
					}
					
				}
				this.setRails(newRails);
				this.setSignals(newSignals);
				
				HEIGHT--;
				repaint();
				break;
			default:
				break;
		}
		
	}

}
