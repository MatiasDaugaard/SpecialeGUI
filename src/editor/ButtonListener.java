package editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import utils.ButtonType;
import utils.Direction;


public class ButtonListener implements ActionListener{

	private JButton[] buttons;
	private JRadioButton[] radioButtons;
	public static ButtonType buttonType = ButtonType.Rail;
	public static Direction d = Direction.Left;
	public static Direction p = Direction.Up;
	public ButtonListener(JButton[] buttons, JRadioButton[] radioButtons) {
		this.buttons = buttons;
		this.radioButtons = radioButtons;
	}
	
	private void setButtonType(int i) {
		switch (i) {
		case 0 :
			buttonType = ButtonType.Rail;
			break;
		case 1 : 
			buttonType = ButtonType.SwitchRail;
			break;
		case 2 :
			buttonType = ButtonType.Signal;
			break;
		case 3 :
			buttonType = ButtonType.Train;
			break;
		case 4 :
			buttonType = ButtonType.RemoveRail;
			break;
		case 5 :
			buttonType = ButtonType.RemoveSwitchRail;
			break;
		case 6 :
			buttonType = ButtonType.RemoveSignal;
			break;
		case 7 :
			buttonType = ButtonType.RemoveTrain;
			break;	
		}
		
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
					setButtonType(i);
					if(i == 1) {
						radioButtons[0].setSelected(true);
						radioButtons[1].setSelected(false);
						radioButtons[2].setSelected(true);
						radioButtons[3].setSelected(false);
						radioButtons[0].setVisible(true);
						radioButtons[1].setVisible(true);
						radioButtons[2].setVisible(true);
						radioButtons[3].setVisible(true);
						d = Direction.Left;
						p = Direction.Up;
					}else if(i == 2) {
						radioButtons[4].setSelected(true);
						radioButtons[5].setSelected(false);
						radioButtons[4].setVisible(true);
						radioButtons[5].setVisible(true);
						d = Direction.Left;
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
				d = Direction.Left;
			}else if (source == radioButtons[1]){
				radioButtons[0].setSelected(false);
				radioButtons[1].setSelected(true);
				d = Direction.Right;
			}else if (source == radioButtons[2]){
				radioButtons[2].setSelected(true);
				radioButtons[3].setSelected(false);
				p = Direction.Up;
			}else if (source == radioButtons[3]){
				radioButtons[2].setSelected(false);
				radioButtons[3].setSelected(true);
				p = Direction.Down;
			}else if (source == radioButtons[4]) {
				radioButtons[4].setSelected(true);
				radioButtons[5].setSelected(false);
				d = Direction.Left;
			}else if (source == radioButtons[5]){
				radioButtons[4].setSelected(false);
				radioButtons[5].setSelected(true);
				d = Direction.Right;
			}
		}
		
	}

}
