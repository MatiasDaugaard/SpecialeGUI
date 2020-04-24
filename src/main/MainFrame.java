package main;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		super("Railway Network GUI");  
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        
        Font font = new Font("Lucida Grande",Font.PLAIN,40);
        
        JLabel label = new JLabel("Select one of the following");
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(label);
        
        JButton editorButton = new JButton();
        editorButton.setText("Edit railway");
        editorButton.setFont(font);
        editorButton.setHorizontalAlignment(JLabel.CENTER);
        editorButton.addActionListener(new EditorButtonListener(this)); 
        contentPane.add(editorButton);
        
        JButton solutionButton = new JButton();
        solutionButton.setText("Show solution");
        solutionButton.setFont(font);
        solutionButton.setHorizontalAlignment(JLabel.CENTER);
        solutionButton.addActionListener(new SolutionButtonListener(this));
        contentPane.add(solutionButton);
        
        
        
        
        
        layout.putConstraint(SpringLayout.WEST, label,200,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, label,-200,SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,50,SpringLayout.NORTH, contentPane);
        
        layout.putConstraint(SpringLayout.WEST, editorButton,200,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, editorButton,-200,SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, editorButton,50,SpringLayout.SOUTH, label);
        
        layout.putConstraint(SpringLayout.WEST, solutionButton,200,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, solutionButton,-200,SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, solutionButton,50,SpringLayout.SOUTH, editorButton);
        
        
        this.pack();
        this.setSize(1400, 850);  
        this.setLocationRelativeTo(null);
          
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true); 
		
		
	}
	
}
