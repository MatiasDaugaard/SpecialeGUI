package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import utils.Direction;

public class GridSizeButtonListener implements ActionListener{

	private EditorMenuPanel panel;
	private JRadioButton[] buttons;
	
	public GridSizeButtonListener(EditorMenuPanel panel, JRadioButton[] buttons) {
		this.panel = panel;
		this.buttons = buttons;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])) {
			panel.setSizeDirection(Direction.Left);
			buttons[0].setSelected(true);
			buttons[1].setSelected(false);
			buttons[2].setSelected(false);
			buttons[3].setSelected(false);
		}else if(e.getSource().equals(buttons[1])) {
			panel.setSizeDirection(Direction.Right);
			buttons[0].setSelected(false);
			buttons[1].setSelected(true);
			buttons[2].setSelected(false);
			buttons[3].setSelected(false);
		}else if(e.getSource().equals(buttons[2])) {
			panel.setSizeDirection(Direction.Up);
			buttons[0].setSelected(false);
			buttons[1].setSelected(false);
			buttons[2].setSelected(true);
			buttons[3].setSelected(false);
		}else if(e.getSource().equals(buttons[3])) {
			panel.setSizeDirection(Direction.Down);
			buttons[0].setSelected(false);
			buttons[1].setSelected(false);
			buttons[2].setSelected(false);
			buttons[3].setSelected(true);
		}
		
	}

	

}
