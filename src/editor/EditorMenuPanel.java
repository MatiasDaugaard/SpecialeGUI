package editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import solution.SolutionListListener;
import utils.BackButtonListener;
import utils.Direction;
import utils.MenuPanel;
// The menu panel for the editor window
public class EditorMenuPanel extends MenuPanel{
	
	
	private String filenameLoad;
	private Direction sizeDirection = Direction.Right;

	public EditorMenuPanel(EditorFrame frame) {
		
		this.setBackground(Color.LIGHT_GRAY.brighter());
        SpringLayout menuLayout = new SpringLayout();
        this.setLayout(menuLayout);
        
        
        JLabel menuLabel = new JLabel("Create or edit railway");
        menuLabel.setFont(bigFont);
        this.add(menuLabel);
        
        //For editing railway
        
        JButton railButton = new JButton();
        railButton.setText("Add rail(s)");
        railButton.setFont(boldFont);
        this.add(railButton);
        
        JButton switchRailButton = new JButton();
        switchRailButton.setText("Add switch rail(s)");
        switchRailButton.setFont(normalFont);
        this.add(switchRailButton);
        
        JRadioButton leftSwitchButton = new JRadioButton();
        leftSwitchButton.setText("Left");
        leftSwitchButton.setFont(normalFont);
        this.add(leftSwitchButton);
        
        JRadioButton rightSwitchButton = new JRadioButton();
        rightSwitchButton.setText("Right");
        rightSwitchButton.setFont(normalFont);
        this.add(rightSwitchButton);
        
        JRadioButton upSwitchButton = new JRadioButton();
        upSwitchButton.setText("Up");
        upSwitchButton.setFont(normalFont);
        this.add(upSwitchButton);
        
        JRadioButton downSwitchButton = new JRadioButton();
        downSwitchButton.setText("Down");
        downSwitchButton.setFont(normalFont);
        this.add(downSwitchButton);
        
        String[] heightOptions = {"1","2","3","4","5"};
        
        JComboBox<String> heightBox = new JComboBox<String>(heightOptions);
        heightBox.setSelectedIndex(0);
        frame.drawingPanel.setSwitchHeight(1);
        
        heightBox.setFont(normalFont);
        heightBox.addActionListener(new EditorComboBoxListener(heightBox, frame.drawingPanel));
        this.add(heightBox);
        heightBox.setVisible(false);
        
        JButton signalButton = new JButton();
        signalButton.setText("Add signal(s)");
        signalButton.setFont(normalFont);
        this.add(signalButton);
        
        JRadioButton leftButton = new JRadioButton();
        leftButton.setText("Left");
        leftButton.setFont(normalFont);
        this.add(leftButton);
        
        JRadioButton rightButton = new JRadioButton();
        rightButton.setText("Right");
        rightButton.setFont(normalFont);
        this.add(rightButton);
 
        JButton trainButton = new JButton();
        trainButton.setText("Add train(s)");
        trainButton.setFont(normalFont);
        this.add(trainButton);
        
        JButton removeRailButton = new JButton();
        removeRailButton.setText("Remove rail");
        removeRailButton.setFont(normalFont);
        this.add(removeRailButton);
        
        JButton removeSwitchRailButton = new JButton();
        removeSwitchRailButton.setText("Remove switch rail");
        removeSwitchRailButton.setFont(normalFont);
        this.add(removeSwitchRailButton);
        
        JButton removeSignalButton = new JButton();
        removeSignalButton.setText("Remove signal");
        removeSignalButton.setFont(normalFont);
        this.add(removeSignalButton);
 
        JButton removeTrainButton = new JButton();
        removeTrainButton.setText("Remove train");
        removeTrainButton.setFont(normalFont);
        this.add(removeTrainButton);
        
        JButton clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setFont(normalFont);
        this.add(clearButton);
        
        JLabel borderLabel1 = new JLabel("___________________________");
        borderLabel1.setHorizontalAlignment(JLabel.CENTER);
        borderLabel1.setFont(normalFont);
        this.add(borderLabel1);
        
        JButton[] buttons = {railButton,switchRailButton,signalButton,trainButton,removeRailButton, removeSwitchRailButton,removeSignalButton,removeTrainButton};
        JRadioButton[] radioButtons = {leftSwitchButton,rightSwitchButton,upSwitchButton,downSwitchButton,leftButton,rightButton};
        ButtonListener buttonListener = new ButtonListener(buttons,radioButtons,heightBox,frame.drawingPanel);
        for(JRadioButton b : radioButtons) {
        	b.addActionListener(buttonListener);
        	b.setVisible(false);
        }
        for(JButton b : buttons) {
        	b.addActionListener(buttonListener);
        }
        ClearButtonListener clearListener = new ClearButtonListener(frame.drawingPanel);
        clearButton.addActionListener(clearListener);
        
        
        
        //For changing grid size
        
        JButton increaseButton = new JButton();
        increaseButton.setText("Increase");
        increaseButton.setFont(normalFont);
        increaseButton.addActionListener(new IncreaseButtonListener(this, frame.drawingPanel));
        this.add(increaseButton);
        
        JButton decreaseButton = new JButton();
        decreaseButton.setText("Decrease");
        decreaseButton.setFont(normalFont);
        decreaseButton.addActionListener(new DecreaseButtonListener(this, frame.drawingPanel));
        this.add(decreaseButton);
        
        
        
        JRadioButton leftSizeButton = new JRadioButton();
        leftSizeButton.setText("Left");
        leftSizeButton.setFont(normalFont);
        this.add(leftSizeButton);
        
        JRadioButton rightSizeButton = new JRadioButton();
        rightSizeButton.setText("Right");
        rightSizeButton.setFont(normalFont);
        rightSizeButton.setSelected(true);
        this.add(rightSizeButton);
        
        JRadioButton upSizeButton = new JRadioButton();
        upSizeButton.setText("Up");
        upSizeButton.setFont(normalFont);
        this.add(upSizeButton);
        
        JRadioButton downSizeButton = new JRadioButton();
        downSizeButton.setText("Down");
        downSizeButton.setFont(normalFont);
        this.add(downSizeButton);
        
        JRadioButton[] sizeButtons = {leftSizeButton,rightSizeButton,upSizeButton,downSizeButton};
        
        GridSizeButtonListener gridSizeListener = new GridSizeButtonListener(this, sizeButtons);
        for(JRadioButton rb : sizeButtons) {
        	rb.addActionListener(gridSizeListener);
        }
        
        JLabel borderLabel2 = new JLabel("___________________________");
        borderLabel2.setHorizontalAlignment(JLabel.CENTER);
        borderLabel2.setFont(normalFont);
        this.add(borderLabel2);
        
        
        //For loading and saving railways
        
        loadLabel = new JLabel();
        loadLabel.setFont(normalFont);
        this.add(loadLabel);
        
        
        Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString()+"/Railways/";
		File f = new File(s);
        File[] railwayFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        
        String[] fileOptions = new String[railwayFiles.length];
        for(int i = 0; i < railwayFiles.length; i++) {
        	String file = railwayFiles[i].getName();
        	fileOptions[i] = file.substring(0, file.length()-4);
        }
        
        JComboBox<String> fileBox = new JComboBox<String>(fileOptions);
        fileBox.setSelectedIndex(0);
        filenameLoad = fileBox.getSelectedItem().toString();
        
        
        fileBox.setFont(normalFont);
        fileBox.addActionListener(new EditorFileComboBoxListener(fileBox, this));
        this.add(fileBox);
        
        JButton loadButton = new JButton();
        loadButton.setText("Load railway");
        loadButton.setFont(normalFont);
        this.add(loadButton);
        
        JTextField filenameField = new JTextField("Railway name");
        filenameField.setFont(normalFont);
        TextFieldListener tfListener = new TextFieldListener(this,filenameField);
        filenameField.getDocument().addDocumentListener(tfListener);
        this.add(filenameField);
        
        JButton saveButton = new JButton();
        saveButton.setText("Save railway");
        saveButton.setFont(normalFont);
        this.add(saveButton);
        
        JButton backButton = new JButton();
        backButton.setText("Return to menu");
        backButton.setFont(normalFont);
        backButton.addActionListener(new BackButtonListener(frame));
        this.add(backButton);
        
        
        
        
        SaveButtonListener saveListener = new SaveButtonListener(this, frame.drawingPanel);
        saveButton.addActionListener(saveListener);
        LoadButtonListener loadListener = new LoadButtonListener(this, frame.drawingPanel);
        loadButton.addActionListener(loadListener);
        
        
        
        //Make menu layout look nice
        // Layout of label
        menuLayout.putConstraint(SpringLayout.NORTH, menuLabel, 6,SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  menuLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  menuLabel, 5,SpringLayout.WEST,  this);
        
        //Layout of rail button
        menuLayout.putConstraint(SpringLayout.NORTH, railButton, 6,SpringLayout.SOUTH, menuLabel);
        menuLayout.putConstraint(SpringLayout.EAST,  railButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  railButton, 5,SpringLayout.WEST, this);
        
        //Layout of switchrail button
        menuLayout.putConstraint(SpringLayout.NORTH, switchRailButton, 6,SpringLayout.SOUTH, railButton);
        menuLayout.putConstraint(SpringLayout.EAST,  switchRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  switchRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of left and right switch button
        menuLayout.putConstraint(SpringLayout.NORTH, leftSwitchButton, 6,SpringLayout.SOUTH, switchRailButton);
        menuLayout.putConstraint(SpringLayout.NORTH, rightSwitchButton, 6,SpringLayout.SOUTH, switchRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  rightSwitchButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  leftSwitchButton, 50,SpringLayout.WEST,  this);
        
        //Layout of up and down switch button
        menuLayout.putConstraint(SpringLayout.NORTH, upSwitchButton, 6,SpringLayout.SOUTH, leftSwitchButton);
        menuLayout.putConstraint(SpringLayout.NORTH, downSwitchButton, 6,SpringLayout.SOUTH, rightSwitchButton);
        menuLayout.putConstraint(SpringLayout.EAST,  downSwitchButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  upSwitchButton, 50,SpringLayout.WEST,  this);
        
        //Layout of heightBox
        menuLayout.putConstraint(SpringLayout.NORTH, heightBox, 6,SpringLayout.SOUTH, upSwitchButton);
        menuLayout.putConstraint(SpringLayout.EAST,  heightBox,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  heightBox, 5,SpringLayout.WEST, this);
        
        //Layout of signal button
        menuLayout.putConstraint(SpringLayout.NORTH, signalButton, 6,SpringLayout.SOUTH, heightBox);
        menuLayout.putConstraint(SpringLayout.EAST,  signalButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  signalButton, 5,SpringLayout.WEST,  this);
        
        //Layout of signal left and right
        menuLayout.putConstraint(SpringLayout.NORTH, leftButton, 6,SpringLayout.SOUTH, signalButton);
        menuLayout.putConstraint(SpringLayout.NORTH, rightButton, 6,SpringLayout.SOUTH, signalButton);
        menuLayout.putConstraint(SpringLayout.EAST,  rightButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  leftButton, 50,SpringLayout.WEST,  this);
        
        //Layout of train button
        menuLayout.putConstraint(SpringLayout.NORTH, trainButton, 6,SpringLayout.SOUTH, leftButton);
        menuLayout.putConstraint(SpringLayout.EAST,  trainButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  trainButton, 5,SpringLayout.WEST,  this);
        
        //Layout of remove rail button
        menuLayout.putConstraint(SpringLayout.NORTH, removeRailButton, 6,SpringLayout.SOUTH, trainButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of remove rail button
        menuLayout.putConstraint(SpringLayout.NORTH, removeSwitchRailButton, 6,SpringLayout.SOUTH, removeRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeSwitchRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeSwitchRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of remove signal button
        menuLayout.putConstraint(SpringLayout.NORTH, removeSignalButton, 6,SpringLayout.SOUTH, removeSwitchRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeSignalButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeSignalButton, 5,SpringLayout.WEST,  this);
        
        //Layout of remove train button
        menuLayout.putConstraint(SpringLayout.NORTH, removeTrainButton, 6,SpringLayout.SOUTH, removeSignalButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeTrainButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeTrainButton, 5,SpringLayout.WEST,  this);
        
        //Layout of clear button
        menuLayout.putConstraint(SpringLayout.NORTH, clearButton, 6,SpringLayout.SOUTH, removeTrainButton);
        menuLayout.putConstraint(SpringLayout.EAST,  clearButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  clearButton, 5,SpringLayout.WEST,  this);
        
        //Layout of border label 1
        menuLayout.putConstraint(SpringLayout.NORTH, borderLabel1, 18,SpringLayout.SOUTH, removeTrainButton);
        menuLayout.putConstraint(SpringLayout.EAST,  borderLabel1,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  borderLabel1, 5,SpringLayout.WEST,  this);
        
        //Layout of increase and decrease buttons
        menuLayout.putConstraint(SpringLayout.NORTH, increaseButton, 6,SpringLayout.SOUTH, borderLabel1);
        menuLayout.putConstraint(SpringLayout.EAST,  increaseButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.NORTH, decreaseButton, 6,SpringLayout.SOUTH, borderLabel1);
        menuLayout.putConstraint(SpringLayout.WEST,  decreaseButton, 5,SpringLayout.WEST,  this);
        
        //Layout of left and right size button
        menuLayout.putConstraint(SpringLayout.NORTH, leftSizeButton, 6,SpringLayout.SOUTH, increaseButton);
        menuLayout.putConstraint(SpringLayout.NORTH, rightSizeButton, 6,SpringLayout.SOUTH, increaseButton);
        menuLayout.putConstraint(SpringLayout.EAST,  rightSizeButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  leftSizeButton, 50,SpringLayout.WEST,  this);
        
        //Layout of up and down size button
        menuLayout.putConstraint(SpringLayout.NORTH, upSizeButton, 6,SpringLayout.SOUTH, leftSizeButton);
        menuLayout.putConstraint(SpringLayout.NORTH, downSizeButton, 6,SpringLayout.SOUTH, rightSizeButton);
        menuLayout.putConstraint(SpringLayout.EAST,  downSizeButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  upSizeButton, 50,SpringLayout.WEST,  this);
        
        //Layout of border label 2
        menuLayout.putConstraint(SpringLayout.NORTH, borderLabel2, 18,SpringLayout.SOUTH, leftSizeButton);
        menuLayout.putConstraint(SpringLayout.EAST,  borderLabel2,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  borderLabel2, 5,SpringLayout.WEST,  this);
        
        //Layout of save load label
        menuLayout.putConstraint(SpringLayout.SOUTH, loadLabel, -6,SpringLayout.NORTH, fileBox);
        menuLayout.putConstraint(SpringLayout.EAST,  loadLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadLabel, 5,SpringLayout.WEST,  this);
        
        //Layout of save load label
        menuLayout.putConstraint(SpringLayout.SOUTH, fileBox, -6,SpringLayout.NORTH, loadButton);
        menuLayout.putConstraint(SpringLayout.EAST,  fileBox,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  fileBox, 5,SpringLayout.WEST,  this);
        
        //Layout of load button
        menuLayout.putConstraint(SpringLayout.SOUTH, loadButton, -6,SpringLayout.NORTH, filenameField);
        menuLayout.putConstraint(SpringLayout.EAST,  loadButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadButton, 5,SpringLayout.WEST,  this);
        
        //Layout of textfield
        menuLayout.putConstraint(SpringLayout.SOUTH, filenameField, -6,SpringLayout.NORTH, saveButton);
        menuLayout.putConstraint(SpringLayout.EAST,  filenameField,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  filenameField, 5,SpringLayout.WEST,  this);
        
        //Layout of save button
        menuLayout.putConstraint(SpringLayout.SOUTH, saveButton, -6,SpringLayout.NORTH, backButton);
        menuLayout.putConstraint(SpringLayout.EAST,  saveButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  saveButton, 5,SpringLayout.WEST,  this);
      
        //Layout of back button
        menuLayout.putConstraint(SpringLayout.SOUTH, backButton, -6,SpringLayout.SOUTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  backButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  backButton, 5,SpringLayout.WEST,  this);
		
	}
	
	@Override
	public String getFilenameLoad() {
		return filenameLoad;
	}

	public void setFilenameLoad(String filename) {
		filenameLoad = filename;
		
	}

	public void setSizeDirection(Direction d) {
		sizeDirection = d;
	}
		
	

	public Direction getSizeDirection() {
		return sizeDirection;
	}


	
	
}
