package solution;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import utils.DrawingPanel;

//Class for the frame for the solution viewer
public class SolutionFrame extends JFrame{
	
	
	public DrawingPanel drawingPanel;

	public SolutionFrame() {
		
		super("Railway Network GUI");  
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        
        drawingPanel = new SolutionDrawingPanel();
        SolutionMenuPanel menuPanel = new SolutionMenuPanel(this); 
        
        contentPane.add(menuPanel);
        contentPane.add(drawingPanel);
 
        
        
        
        // Make the layout look nice
        layout.putConstraint(SpringLayout.WEST, menuPanel,
                             5,
                             SpringLayout.WEST, contentPane);
        
        layout.putConstraint(SpringLayout.NORTH, menuPanel,
                             5,
                             SpringLayout.NORTH, contentPane);
        
        layout.putConstraint(SpringLayout.SOUTH, menuPanel,
	 				 		 -5,
	 				 		 SpringLayout.SOUTH, contentPane);
        
        layout.putConstraint(SpringLayout.EAST, menuPanel,
                			 240,
                			 SpringLayout.WEST, contentPane);
 
        layout.putConstraint(SpringLayout.WEST, drawingPanel,
                             5,
                             SpringLayout.EAST, menuPanel);
        
        layout.putConstraint(SpringLayout.NORTH, drawingPanel,
                             5,
                             SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.EAST, contentPane,
                             5,
                             SpringLayout.EAST, drawingPanel);
        
        layout.putConstraint(SpringLayout.SOUTH, contentPane,
                             5,
                             SpringLayout.SOUTH, drawingPanel);
        
        
        
        
        this.pack();
        this.setSize(1400, 850);  
        this.setLocationRelativeTo(null);
          
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);
	}

}
