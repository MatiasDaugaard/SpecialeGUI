package solution;

import java.awt.Color;
import java.awt.Graphics;

import utils.DrawingPanel;

public class SolutionDrawingPanel extends DrawingPanel{
	
	public SolutionDrawingPanel() {
		super();
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
	    			int x = (5+j*xOffset)+5;
		    		int y = (5+i*yOffset)-10;
		    		g.fillRect(x, y, width, height);
	    		}else if(signals[i][j] == 3) {
	    			int x = (5+j*xOffset)+5;
		    		int y = (5+i*yOffset)-10;
		    		g.fillRect(x, y, width, height);
		    		x = (5+j*xOffset)-5;
		    		g.fillRect(x, y, width, height);
	    		}
	    	}
	    }
	}
	
	@Override
	protected void drawSwitchRails(Graphics g) {
		
	}
	
	@Override
	protected void drawTrains(Graphics g) {
		
	}

}
