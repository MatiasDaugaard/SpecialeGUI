package editor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utils.ButtonType;
import utils.Direction;

public class DrawingListener implements MouseListener,KeyListener{

	private EditorDrawingPanel panel;
	public static int lastI = -1;
	public static int lastJ = -1;
	
	public DrawingListener(EditorDrawingPanel panel) {
		this.panel = panel;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.grabFocus();
		int x = e.getX();
		int y = e.getY();
	    
	    int j = (x-5 + EditorDrawingPanel.xOffset/2)/EditorDrawingPanel.xOffset;
	    int i = (y-5 + EditorDrawingPanel.yOffset/2)/EditorDrawingPanel.yOffset;
	    if(lastI == -1) {
	    	if (ButtonListener.buttonType == ButtonType.Signal) {
    	    	Direction d = ButtonListener.d;
    	    	panel.addSignal(i, j, d);
    	    	panel.setHighlight(i,j);
	    	}else if (ButtonListener.buttonType == ButtonType.RemoveSignal) {
	    		panel.removeSignal(i,j);
	    		panel.setHighlight(-1, -1);
	    	}else if (ButtonListener.buttonType == ButtonType.RemoveTrain) {
	    		panel.removeTrain(i, j);
	    		panel.setHighlight(-1, -1);
	    	}else if (ButtonListener.buttonType == ButtonType.SwitchRail) {
	    		panel.addSwitchRail(i,j,ButtonListener.d,ButtonListener.p);
	    		panel.setHighlight(i,j);
	    	}else if(ButtonListener.buttonType == ButtonType.RemoveSwitchRail) {
	    		panel.removeSwitchRail(i,j);
	    		panel.setHighlight(i,j);
	    	}else{
	    		lastI = i;
	    	    lastJ = j;
	    	    panel.setHighlight(lastI,lastJ);
	    	}
    	}else {
    		if (ButtonListener.buttonType == ButtonType.Rail) {
    			if( (j == lastJ + 1 || j == lastJ - 1) && (i == lastI) ) {
    				panel.addRail(lastI, lastJ, i, j);
    			}
    	    }else if (ButtonListener.buttonType == ButtonType.Train) {
    	    	panel.addTrain(lastI, lastJ, i, j);
    	    	lastI = -1;
    	    	lastJ = -1;
    	    	panel.setHighlight(lastI, lastJ);
    	    	return;
    	    }else if(ButtonListener.buttonType == ButtonType.RemoveRail) {
    	    	if( (j == lastJ + 1 || j == lastJ - 1) && (i == lastI || i == lastI+1 || i == lastI-1 ) ) {
    				panel.removeRail(lastI, lastJ, i, j);
    			}
    	    }
    		lastI = i;
    	    lastJ = j;
    	    panel.setHighlight(lastI,lastJ);
    	}
	    
	    
	    
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == ' ') {
			reset();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	public void reset() {
		panel.setHighlight(-1, -1);
		lastI = -1;
	    lastJ = -1;
		
	}

}
