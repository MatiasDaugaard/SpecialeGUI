package editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import utils.ButtonType;
import utils.Direction;

//Listener for buttons in editor menu panel
public class ButtonListener implements ActionListener{

	private JButton[] buttons;
	private JRadioButton[] radioButtons;
	private JComboBox<String> heightBox;
	private EditorDrawingPanel panel;
	
	public ButtonListener(JButton[] buttons, JRadioButton[] radioButtons, JComboBox<String> heightBox, EditorDrawingPanel panel) {
		this.buttons = buttons;
		this.radioButtons = radioButtons;
		this.heightBox = heightBox;
		this.panel = panel;
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton source = (JButton) e.getSource();
			for(int i = 0; i < buttons.length; i++) {
				if(source == buttons[i]) {
					buttons[i].setFont(EditorMenuPanel.boldFont);
					for(JRadioButton rb : radioButtons) {
						rb.setVisible(false);
					}
					heightBox.setVisible(false);
					panel.setButtonType(i);
					if(i == 1) {
						radioButtons[0].setSelected(true);
						radioButtons[1].setSelected(false);
						radioButtons[2].setSelected(true);
						radioButtons[3].setSelected(false);
						radioButtons[0].setVisible(true);
						radioButtons[1].setVisible(true);
						radioButtons[2].setVisible(true);
						radioButtons[3].setVisible(true);
						heightBox.setVisible(true);
						panel.setDirectionLR(Direction.Left);
						panel.setDirectionUD(Direction.Up);
					}else if(i == 2) {
						radioButtons[4].setSelected(true);
						radioButtons[5].setSelected(false);
						radioButtons[4].setVisible(true);
						radioButtons[5].setVisible(true);
						panel.setDirectionLR(Direction.Left);
					}
				}else {
					buttons[i].setFont(EditorMenuPanel.normalFont);
				}
			}
			DrawingListener.lastI = -1;
			DrawingListener.lastJ = -1;
		}else if (e.getSource() instanceof JRadioButton) {
			JRadioButton source = (JRadioButton) e.getSource();
			if (source == radioButtons[0]){
				radioButtons[0].setSelected(true);
				radioButtons[1].setSelected(false);
				panel.setDirectionLR(Direction.Left);
			}else if (source == radioButtons[1]){
				radioButtons[0].setSelected(false);
				radioButtons[1].setSelected(true);
				panel.setDirectionLR(Direction.Right);
			}else if (source == radioButtons[2]){
				radioButtons[2].setSelected(true);
				radioButtons[3].setSelected(false);
				panel.setDirectionUD(Direction.Up);
			}else if (source == radioButtons[3]){
				radioButtons[2].setSelected(false);
				radioButtons[3].setSelected(true);
				panel.setDirectionUD(Direction.Down);
			}else if (source == radioButtons[4]) {
				radioButtons[4].setSelected(true);
				radioButtons[5].setSelected(false);
				panel.setDirectionLR(Direction.Left);
			}else if (source == radioButtons[5]){
				radioButtons[4].setSelected(false);
				radioButtons[5].setSelected(true);
				panel.setDirectionLR(Direction.Right);
			}
		}
		
	}

}
