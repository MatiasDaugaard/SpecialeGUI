package editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Listener for clear railway button
public class ClearButtonListener implements ActionListener{

	private EditorDrawingPanel panel;
	
	public ClearButtonListener(EditorDrawingPanel panel) {
		this.panel = panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		panel.reset();
		
	}

}
