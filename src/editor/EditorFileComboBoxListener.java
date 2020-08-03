package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
//Listener for the dropdown menu of railways
public class EditorFileComboBoxListener implements ActionListener{

	private JComboBox<String> list;
	private EditorMenuPanel panel;
	
	public EditorFileComboBoxListener(JComboBox<String> list, EditorMenuPanel panel) {
		this.list = list;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setFilenameLoad(list.getSelectedItem().toString());
		
	}

}
