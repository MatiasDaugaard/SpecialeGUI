package solution;

import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import editor.LoadButtonListener;
import utils.MenuPanel;


public class SolutionMenuPanel extends MenuPanel{

	private JButton startButton;
	private JButton stopButton;
	private JButton restartButton;
	
	public SolutionMenuPanel(SolutionFrame frame) {
		
		this.setBackground(Color.LIGHT_GRAY.brighter());
        SpringLayout menuLayout = new SpringLayout();
        this.setLayout(menuLayout);
        
        
        JLabel menuLabel = new JLabel("Select railway");
        menuLabel.setFont(bigFont);
        this.add(menuLabel);
        
        //Find all railways in current directory
        // TODO : create .sol files instead of .txt and has to exist for both. sol files contains solution. txt files contain railway
        File f = new File(".");
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        
        String[] solutionFiles = new String[matchingFiles.length];
        for(int i = 0; i < matchingFiles.length ; i++) {
        	String file = matchingFiles[i].getName();
        	solutionFiles[i] = file.substring(0, file.length()-4);
        }

        
        JComboBox<String> solutionList = new JComboBox<String>(solutionFiles);
        solutionList.setSelectedIndex(0);
        filename = solutionList.getItemAt(0);
        solutionList.setFont(normalFont);
        solutionList.addActionListener(new SolutionListListener(solutionList, this));
        this.add(solutionList);
        
        JButton loadButton = new JButton("Load solution");
        loadButton.setFont(normalFont);
        LoadButtonListener loadListener = new LoadButtonListener(this, frame.networkPanel);
        loadButton.addActionListener(loadListener);
        this.add(loadButton);
        
        startButton = new JButton("Start");
        startButton.setFont(normalFont);
        startButton.setVisible(false);
        startButton.addActionListener(new StartButtonListener((SolutionDrawingPanel)frame.networkPanel));
        this.add(startButton);
        
        stopButton = new JButton("Stop");
        stopButton.setFont(normalFont);
        stopButton.setVisible(false);
        this.add(stopButton);
        
        restartButton = new JButton("Restart");
        restartButton.setFont(normalFont);
        restartButton.setVisible(false);
        restartButton.addActionListener(new RestartButtonListener((SolutionDrawingPanel)frame.networkPanel));
        this.add(restartButton);
        
        
        
        
        menuLayout.putConstraint(SpringLayout.NORTH, menuLabel, 10,SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  menuLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  menuLabel, 5,SpringLayout.WEST,  this);
        
        
        menuLayout.putConstraint(SpringLayout.NORTH, solutionList, 20,SpringLayout.SOUTH, menuLabel);
        menuLayout.putConstraint(SpringLayout.EAST,  solutionList,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  solutionList, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, loadButton, 20,SpringLayout.SOUTH, solutionList);
        menuLayout.putConstraint(SpringLayout.EAST,  loadButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, startButton, 50,SpringLayout.SOUTH, loadButton);
        menuLayout.putConstraint(SpringLayout.EAST,  startButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  startButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, stopButton, 20,SpringLayout.SOUTH, startButton);
        menuLayout.putConstraint(SpringLayout.EAST,  stopButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  stopButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, restartButton, 20,SpringLayout.SOUTH, stopButton);
        menuLayout.putConstraint(SpringLayout.EAST,  restartButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  restartButton, 5,SpringLayout.WEST,  this);
        
        
        
		
	}
	
	public void loaded() {
		startButton.setVisible(true);
		stopButton.setVisible(true);
		restartButton.setVisible(true);
	}
}
