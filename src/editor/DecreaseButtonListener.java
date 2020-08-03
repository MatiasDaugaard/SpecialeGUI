package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.Direction;
//Listener for decrease grid size button
public class DecreaseButtonListener implements ActionListener {
	
	private EditorMenuPanel menuPanel;
	private EditorDrawingPanel drawingPanel;
	
	public DecreaseButtonListener(EditorMenuPanel menuPanel, EditorDrawingPanel drawingPanel) {
		this.menuPanel = menuPanel;
		this.drawingPanel = drawingPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Direction d = menuPanel.getSizeDirection();
		drawingPanel.decreaseSize(d);

	}

}
