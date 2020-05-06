package editor;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import utils.Direction;
import utils.DrawingPanel;
import utils.Rail;
import utils.Signal;
import utils.SwitchRail;
import utils.Train;


public class EditorDrawingPanel extends DrawingPanel{
    
    private DrawingListener drawingListener;

	
	public EditorDrawingPanel() {
		
		drawingListener = new DrawingListener(this);
		this.addMouseListener(drawingListener);
		this.addKeyListener(drawingListener);
		
		
	}
	
	public void addTrain(int i, int j, int m, int n) {
		if(rails[i][j] != 0 && rails[m][n] != 0) {
			int start = i*WIDTH+j;
			int end = m*WIDTH+n;
			Direction d;
			if(j < n) {
				d = Direction.Right;
			}else {
				d = Direction.Left;
			}
			for(Train train : trainList) {
				if(train.getStartLocation() == start || train.getEndLocation() == end) {
					return;
				}
			}
			Train t = new Train(start,end,d);
			trainList.add(t);
			repaint();
		}
		
	}
	
	public void removeTrain(int i, int j) {
		int start = i*WIDTH+j;
		trainList.removeIf(t -> t.getStartLocation() == start);
		repaint();
	}
	
	public void addSignal(int i, int j, Direction d) {
		if(rails[i][j] != 0) {
			if (d == Direction.Left) {
				if(signals[i][j] == 0) {
					signals[i][j] = 1;
				}else if(signals[i][j] == 2) {
					signals[i][j] = 3;
				}
				
			}else {
				if(signals[i][j] == 0) {
					signals[i][j] = 2;
				}else if(signals[i][j] == 1) {
					signals[i][j] = 3;
				}
			}
			int loc = i*WIDTH+j;
			signalList.add(new Signal(loc, d));
			repaint();
		}
		
	}
	
	public void removeSignal(int i, int j) {
		signals[i][j] = 0;
		int loc = i*WIDTH+j;
		signalList.removeIf(s->s.getLocation() == loc);
		setHighlight(-1,-1);
		repaint();
	}
	
	
	
	public void addRail(int i, int j, int m, int n) {
		int start = i*WIDTH+j;
		int end = m*WIDTH+n;
		Rail r = new Rail(start,end);
		if(!railList.contains(r)) {
			if(rails[i][j] == 4 && rails[m][n] == 3) {
				return;
			}
			if(rails[i][j] == 3 && rails[m][n] == 4) {
				return;
			}
			if(rails[i][j] == 5 && n > j) {
				return;
			}
			if(rails[m][n] == 5 && j > n) {
				return;
			}
			if(rails[i][j] == 6 && n < j) {
				return;
			}
			if(rails[m][n] == 6 && j < n) {
				return;
			}
			
			if((rails[i][j] == 2 && rails[m][n] == 2)) {
				return;
			}else {
				if(rails[i][j] <= 1) {
					rails[i][j]++;
				}if(rails[m][n] <= 1) {
					rails[m][n]++;
				}
				railList.add(r);
				this.repaint();
			}
			
				
		}
	}
	
	public void removeRail(int i, int j, int m, int n) {
		int start = i*WIDTH+j;
		int end = m*WIDTH+n;
		Rail r = new Rail(start,end);
		if(railList.contains(r)) {
			if(rails[i][j] <= 2) {
				rails[i][j]--;
			}
			if(rails[m][n] <= 2) {
				rails[m][n]--;
			}
			railList.remove(r);
			signals[i][j] = 0;
			signals[m][n] = 0;
			trainList.removeIf(t -> (t.getStartLocation() == start || t.getEndLocation() == start || t.getStartLocation() == end || t.getEndLocation() == end));
			this.repaint();
		}
		
	}
	
	public void addSwitchRail(int i, int j, Direction d, Direction p) {
		
		if(d == Direction.Left && p == Direction.Up) {
			if(j==0 || i==0) {
				return;
			}
			if(rails[i][j] <= 1 && rails[i][j-1] <= 1 && rails[i-1][j-1] <= 1) {
				int start = i*WIDTH+j;
				int end1 = i*WIDTH+(j-1);
				int end2 = (i-1)*WIDTH+(j-1);
				int loc = (i-1)*WIDTH+(j);
				if(railList.contains(new Rail(start,end1)) || railList.contains(new Rail(end2,loc))) {
					return;
				}
				rails[i][j] = 3;
				rails[i][j-1] = 4;
				rails[i-1][j-1] = 5;
				
				SwitchRail r = new SwitchRail(start, end1, end2, d);
				switchRailList.add(r);
			}
		}else if(d == Direction.Left && p == Direction.Down) {
			if(j==0 || i==HEIGHT-1) {
				return;
			}
			if(rails[i][j] <= 1 && rails[i][j-1] <= 1 && rails[i+1][j-1] <= 1) {
				int start = i*WIDTH+j;
				int end1 = i*WIDTH+(j-1);
				int end2 = (i+1)*WIDTH+(j-1);
				int loc = (i+1)*WIDTH+(j);
				if(railList.contains(new Rail(start,end1)) || railList.contains(new Rail(end2,loc))) {
					return;
				}
				rails[i][j] = 3;
				rails[i][j-1] = 4;
				rails[i+1][j-1] = 5;
				
				SwitchRail r = new SwitchRail(start, end1, end2, d);
				switchRailList.add(r);
			}
		}else if(d == Direction.Right && p == Direction.Up) {
			if(j==WIDTH-1 || i==0) {
				return;
			}
			if(rails[i][j] <= 1 && rails[i][j+1] <= 1 && rails[i-1][j+1] <= 1) {
				int start = i*WIDTH+(j);
				int end1 = i*WIDTH+(j+1);
				int end2 = (i-1)*WIDTH+(j+1);
				int loc = (i-1)*WIDTH+j;
				if(railList.contains(new Rail(start,end1)) || railList.contains(new Rail(end2,loc))) {
					return;
				}
				rails[i][j] = 3;
				rails[i][j+1] = 4;
				rails[i-1][j+1] = 6;
				
				SwitchRail r = new SwitchRail(start, end1, end2, d);
				switchRailList.add(r);
			}
		}else if(d == Direction.Right && p == Direction.Down) {
			if(j==WIDTH-1 || i==HEIGHT-1) {
				return;
			}
			if(rails[i][j] <= 1 && rails[i][j+1] <= 1 && rails[i+1][j+1] <= 1) {
				int start = i*WIDTH+(j);
				int end1 = i*WIDTH+(j+1);
				int end2 = (i+1)*WIDTH+(j+1);
				int loc = (i+1)*WIDTH+j;
				if(railList.contains(new Rail(start,end1)) || railList.contains(new Rail(end2,loc))) {
					return;
				}
				rails[i][j] = 3;
				rails[i][j+1] = 4;
				rails[i+1][j+1] = 6;
				
				SwitchRail r = new SwitchRail(start, end1, end2, d);
				switchRailList.add(r);
			}
		}
		repaint();
	}
	
	public void removeSwitchRail(int i, int j) {
		int start = i*WIDTH+(j);
		System.out.println("Remove switchrail");
		for(SwitchRail r : switchRailList) {
			if(r.getStart() == start) {
				//End1 same row
				System.out.println("Found it");
				int iEnd1 = Math.floorDiv(r.getEnd1(), WIDTH);
		    	int jEnd1 = r.getEnd1()%WIDTH;
		    	if(jEnd1 < j) {
		    		if(jEnd1 > 0 && rails[i][j-2] > 0) {
		    			rails[iEnd1][jEnd1] = 1;
		    		}else {
		    			rails[iEnd1][jEnd1] = 0;
		    		}
		    		if(j < WIDTH - 1 && rails[i][j+1] > 0) {
		    			rails[i][j] = 1;
		    		}else {
		    			rails[i][j] = 0;
		    		}
		    	}else if(jEnd1 > j) {
		    		if(j > 0 && rails[i][j-1] > 0) {
		    			rails[i][j] = 1;
		    		}else {
		    			rails[i][j] = 0;
		    		}
		    		if(jEnd1 < WIDTH - 1 && rails[iEnd1][jEnd1+1] > 0) {
		    			rails[iEnd1][jEnd1] = 1;
		    		}else {
		    			rails[iEnd1][jEnd1] = 0;
		    		}
		    	}
		    	
		    	
		    	
		    	int iEnd2 = Math.floorDiv(r.getEnd2(), WIDTH);
		    	int jEnd2 = r.getEnd2()%WIDTH;
		    	
		    	if(jEnd2 < j) {
		    		if(iEnd2 < i) {
		    			if(jEnd2 > 0 && rails[i-1][j-2] > 0) {
			    			rails[iEnd2][jEnd2] = 1;
			    		}else {
			    			rails[iEnd2][jEnd2] = 0;
			    		}
		    		}else {
		    			if(jEnd2 > 0 && rails[i+1][j-2] > 0) {
			    			rails[iEnd2][jEnd2] = 1;
			    		}else {
			    			rails[iEnd2][jEnd2] = 0;
			    		}
		    		}
		    		
		    	}else if(jEnd2 > j) {
		    		if(iEnd2 < i) {
		    			if(jEnd2 < WIDTH -1 && rails[i-1][j+2] > 0) {
			    			rails[iEnd2][jEnd2] = 1;
			    		}else {
			    			rails[iEnd2][jEnd2] = 0;
			    		}
		    		}else {
		    			if(jEnd2 < WIDTH -1 && rails[i+1][j+2] > 0) {
			    			rails[iEnd2][jEnd2] = 1;
			    		}else {
			    			rails[iEnd2][jEnd2] = 0;
			    		}
		    		}
		    	}
						
				signals[i][j] = 0;
				signals[iEnd1][jEnd1] = 0;
				signals[iEnd2][jEnd2] = 0;
				trainList.removeIf(t -> t.getStartLocation() == r.getStart() || t.getStartLocation() == r.getEnd1() || t.getStartLocation() == r.getEnd2() || t.getEndLocation() == r.getStart() || t.getEndLocation() == r.getEnd1() || t.getEndLocation() == r.getEnd2());
				switchRailList.remove(r);
				break;
				
			}
		}
		repaint();
	}
	
	public void setHighlight(int i, int j) {
		highlightI = i;
		highlightJ = j;
		repaint();
	}

	public void reset() {
		super.reset();
		drawingListener.reset();
		repaint();
		
	}
	

}
