package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.Direction;
//Listener for increase grid size button
public class IncreaseButtonListener implements ActionListener {

	private EditorMenuPanel menuPanel;
	private EditorDrawingPanel drawingPanel;
	
	public IncreaseButtonListener(EditorMenuPanel menuPanel, EditorDrawingPanel drawingPanel) {
		this.menuPanel = menuPanel;
		this.drawingPanel = drawingPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Direction d = menuPanel.getSizeDirection();
		drawingPanel.increaseSize(d);
		

	}

}
