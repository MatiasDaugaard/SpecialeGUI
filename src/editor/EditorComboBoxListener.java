package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import utils.MenuPanel;
//Listener for dropdown menu with switch-rail height options
public class EditorComboBoxListener implements ActionListener{

	private JComboBox<String> list;
	private EditorDrawingPanel panel;
	
	public EditorComboBoxListener(JComboBox<String> list, EditorDrawingPanel panel) {
		this.list = list;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setSwitchHeight(Integer.parseInt((String) list.getSelectedItem()));
		
	}

}
