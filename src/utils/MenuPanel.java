package utils;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	
	public static String fontName = "Lucida Grande";
	public static int fontSize = 16;
	public static Font normalFont = new Font(fontName,Font.PLAIN,fontSize);
	public static Font boldFont = new Font(fontName,Font.BOLD,fontSize);
	public static Font bigFont = new Font(fontName,Font.PLAIN,22);
	
	protected JLabel loadLabel = new JLabel();
	protected String filename = "";
	protected boolean loaded = false;
	
	public void setLabelMessage(String s) {
		loadLabel.setText(s);
		loadLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void loaded() {}
	
	public void loaded(int length, int generated, double time) {}

	public String getFilenameLoad() {
		return "";
	}

}
