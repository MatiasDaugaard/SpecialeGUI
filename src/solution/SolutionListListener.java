package solution;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import utils.MenuPanel;

public class SolutionListListener implements ActionListener{

	private JComboBox list;
	private MenuPanel panel;
	
	public SolutionListListener(JComboBox list, MenuPanel panel) {
		this.list = list;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setFilename(list.getSelectedItem().toString());
		
	}

}
