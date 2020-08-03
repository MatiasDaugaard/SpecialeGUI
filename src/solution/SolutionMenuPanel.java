package solution;

import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import editor.LoadButtonListener;
import utils.BackButtonListener;
import utils.MenuPanel;


public class SolutionMenuPanel extends MenuPanel{

	private JButton startButton;
	private JButton stopButton;
	private JButton restartButton;
	private JButton nextButton;
	private JButton prevButton;
	
	private JLabel solutionLength;
	private JLabel generatedStates;
	private JLabel solutionTime;
	
	public SolutionMenuPanel(SolutionFrame frame) {
		
		this.setBackground(Color.LIGHT_GRAY.brighter());
        SpringLayout menuLayout = new SpringLayout();
        this.setLayout(menuLayout);
        
        
        JLabel menuLabel = new JLabel("Select railway");
        menuLabel.setFont(bigFont);
        this.add(menuLabel);
        
        //Find all railways in current directory
        Path currentRelativePath = Paths.get("");
		String p = currentRelativePath.toAbsolutePath().toString()+"/Railways/";
		File f = new File(p);
        File[] railwayFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        
        File[] solutionFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".sol");
            }
        });
        
        Set<String> railwayFileSet = new HashSet<>();
        for(File fl : railwayFiles) {
        	String file = fl.getName();
        	railwayFileSet.add(file.substring(0, file.length()-4));
        }
        
        Set<String> solutionFileSet = new HashSet<>();
        for(File fl : solutionFiles) {
        	String file = fl.getName();
        	solutionFileSet.add(file.substring(0, file.length()-4));
        }
        
        solutionFileSet.removeIf(s -> !railwayFileSet.contains(s));
        String[] files = new String[solutionFileSet.size()];
        int i = 0;
        for(String s : solutionFileSet) {
        	files[i] = s;
        	i++;
        }
        
        
        

        
        JComboBox<String> solutionList = new JComboBox<String>(files);
        if(files.length > 0) {
        	solutionList.setSelectedIndex(0);
            filename = solutionList.getItemAt(0);
        }
        
        solutionList.setFont(normalFont);
        solutionList.addActionListener(new SolutionListListener(solutionList, this));
        this.add(solutionList);
        
        JButton loadButton = new JButton("Load solution");
        loadButton.setFont(normalFont);
        LoadButtonListener loadListener = new LoadButtonListener(this, frame.drawingPanel);
        loadButton.addActionListener(loadListener);
        this.add(loadButton);
        
        solutionLength = new JLabel();
        solutionLength.setFont(normalFont);
        solutionLength.setVisible(false);
        this.add(solutionLength);
        
        generatedStates = new JLabel();
        generatedStates.setFont(normalFont);
        generatedStates.setVisible(false);
        this.add(generatedStates);
        
        solutionTime = new JLabel();
        solutionTime.setFont(normalFont);
        solutionTime.setVisible(false);
        this.add(solutionTime);
        
        startButton = new JButton("Start");
        startButton.setFont(normalFont);
        startButton.setVisible(false);
        startButton.addActionListener(new StartButtonListener((SolutionDrawingPanel)frame.drawingPanel));
        this.add(startButton);
        
        stopButton = new JButton("Stop");
        stopButton.setFont(normalFont);
        stopButton.setVisible(false);
        stopButton.addActionListener(new StopButtonListener((SolutionDrawingPanel)frame.drawingPanel));
        this.add(stopButton);
        
        restartButton = new JButton("Restart");
        restartButton.setFont(normalFont);
        restartButton.setVisible(false);
        restartButton.addActionListener(new RestartButtonListener((SolutionDrawingPanel)frame.drawingPanel));
        this.add(restartButton);
        
        JLabel borderLabel = new JLabel("___________________________");
        borderLabel.setHorizontalAlignment(JLabel.CENTER);
        borderLabel.setFont(normalFont);
        this.add(borderLabel);
        
        nextButton = new JButton("Next state");
        nextButton.setFont(normalFont);
        nextButton.setVisible(false);
        nextButton.addActionListener(new NextButtonListener((SolutionDrawingPanel)frame.drawingPanel));
        this.add(nextButton);
        
        prevButton = new JButton("Previous state");
        prevButton.setFont(normalFont);
        prevButton.setVisible(false);
        prevButton.addActionListener(new PrevButtonListener((SolutionDrawingPanel)frame.drawingPanel));
        this.add(prevButton);
        
        
        
        
        
        JButton backButton = new JButton("Return to menu");
        backButton.setFont(normalFont);
        backButton.addActionListener(new BackButtonListener(frame));
        this.add(backButton);
        
        
        
        
        menuLayout.putConstraint(SpringLayout.NORTH, menuLabel, 10,SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  menuLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  menuLabel, 5,SpringLayout.WEST,  this);
        
        
        menuLayout.putConstraint(SpringLayout.NORTH, solutionList, 20,SpringLayout.SOUTH, menuLabel);
        menuLayout.putConstraint(SpringLayout.EAST,  solutionList,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  solutionList, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, loadButton, 20,SpringLayout.SOUTH, solutionList);
        menuLayout.putConstraint(SpringLayout.EAST,  loadButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, solutionLength, 50,SpringLayout.SOUTH, loadButton);
        menuLayout.putConstraint(SpringLayout.EAST,  solutionLength,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  solutionLength, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, generatedStates, 50,SpringLayout.SOUTH, solutionLength);
        menuLayout.putConstraint(SpringLayout.EAST,  generatedStates,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  generatedStates, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, solutionTime, 50,SpringLayout.SOUTH, generatedStates);
        menuLayout.putConstraint(SpringLayout.EAST,  solutionTime,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  solutionTime, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, startButton, 50,SpringLayout.SOUTH, solutionTime);
        menuLayout.putConstraint(SpringLayout.EAST,  startButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  startButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, stopButton, 20,SpringLayout.SOUTH, startButton);
        menuLayout.putConstraint(SpringLayout.EAST,  stopButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  stopButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, restartButton, 20,SpringLayout.SOUTH, stopButton);
        menuLayout.putConstraint(SpringLayout.EAST,  restartButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  restartButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, borderLabel, 20,SpringLayout.SOUTH, restartButton);
        menuLayout.putConstraint(SpringLayout.EAST,  borderLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  borderLabel, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, nextButton, 20,SpringLayout.SOUTH, borderLabel);
        menuLayout.putConstraint(SpringLayout.EAST,  nextButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  nextButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.NORTH, prevButton, 20,SpringLayout.SOUTH, nextButton);
        menuLayout.putConstraint(SpringLayout.EAST,  prevButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  prevButton, 5,SpringLayout.WEST,  this);
        
        menuLayout.putConstraint(SpringLayout.SOUTH, backButton, -10,SpringLayout.SOUTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  backButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  backButton, 5,SpringLayout.WEST,  this);
        
		
	}
	
	public void loaded(int length, int generated, double time) {
		startButton.setVisible(true);
		stopButton.setVisible(true);
		restartButton.setVisible(true);
		nextButton.setVisible(true);
		prevButton.setVisible(true);
		
		solutionLength.setVisible(true);
		solutionLength.setText("Solution length : " + Integer.toString(length));
		generatedStates.setVisible(true);
		generatedStates.setText("Generated states : " + Integer.toString(generated));
		solutionTime.setVisible(true);
		solutionTime.setText("Time spend : " + Double.toString(time) + "ms");
	}
}
