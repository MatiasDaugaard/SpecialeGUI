package editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import utils.MenuPanel;

public class EditorMenuPanel extends MenuPanel{

	
	//JTextField menuText;
	
	
	public EditorMenuPanel(EditorFrame frame) {
		
		this.setBackground(Color.LIGHT_GRAY.brighter());
        SpringLayout menuLayout = new SpringLayout();
        this.setLayout(menuLayout);
        
        
        JLabel menuLabel = new JLabel("Create or edit railway");
        menuLabel.setFont(bigFont);
        this.add(menuLabel);
        
        JTextField filenameField = new JTextField("Railway name");
        filenameField.setFont(normalFont);
        TextFieldListener tfListener = new TextFieldListener(this,filenameField);
        filenameField.getDocument().addDocumentListener(tfListener);
        this.add(filenameField);
        
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
        
        loadLabel = new JLabel();
        loadLabel.setFont(normalFont);
        this.add(loadLabel);
        
        JButton loadButton = new JButton();
        loadButton.setText("Load railway");
        loadButton.setFont(normalFont);
        this.add(loadButton);
        
        JButton saveButton = new JButton();
        saveButton.setText("Save railway");
        saveButton.setFont(normalFont);
        this.add(saveButton);
        
        
        
        JButton[] buttons = {railButton,switchRailButton,signalButton,trainButton,removeRailButton, removeSwitchRailButton,removeSignalButton,removeTrainButton};
        JRadioButton[] radioButtons = {leftSwitchButton,rightSwitchButton,upSwitchButton,downSwitchButton,leftButton,rightButton};
        ButtonListener buttonListener = new ButtonListener(buttons,radioButtons);
        for(JRadioButton b : radioButtons) {
        	b.addActionListener(buttonListener);
        	b.setVisible(false);
        }
        for(JButton b : buttons) {
        	b.addActionListener(buttonListener);
        }
        ClearButtonListener clearListener = new ClearButtonListener(frame.drawingPanel);
        clearButton.addActionListener(clearListener);
        SaveButtonListener saveListener = new SaveButtonListener(this, frame.drawingPanel);
        saveButton.addActionListener(saveListener);
        LoadButtonListener loadListener = new LoadButtonListener(this, frame.drawingPanel);
        loadButton.addActionListener(loadListener);
        
        
        
        //Make menu layout look nice
        // Layout of label
        menuLayout.putConstraint(SpringLayout.NORTH, menuLabel, 10,SpringLayout.NORTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  menuLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  menuLabel, 5,SpringLayout.WEST,  this);
        
        //Layout of textfield
        menuLayout.putConstraint(SpringLayout.NORTH, filenameField, 20,SpringLayout.SOUTH, menuLabel);
        menuLayout.putConstraint(SpringLayout.EAST,  filenameField,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  filenameField, 5,SpringLayout.WEST,  this);
        
        //Layout of rail button
        menuLayout.putConstraint(SpringLayout.NORTH, railButton, 10,SpringLayout.SOUTH, filenameField);
        menuLayout.putConstraint(SpringLayout.EAST,  railButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  railButton, 5,SpringLayout.WEST, this);
        
        //Layout of switchrail button
        menuLayout.putConstraint(SpringLayout.NORTH, switchRailButton, 10,SpringLayout.SOUTH, railButton);
        menuLayout.putConstraint(SpringLayout.EAST,  switchRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  switchRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of left and right switch button
        menuLayout.putConstraint(SpringLayout.NORTH, leftSwitchButton, 10,SpringLayout.SOUTH, switchRailButton);
        menuLayout.putConstraint(SpringLayout.NORTH, rightSwitchButton, 10,SpringLayout.SOUTH, switchRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  rightSwitchButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  leftSwitchButton, 50,SpringLayout.WEST,  this);
        
        //Layout of up and down switch button
        menuLayout.putConstraint(SpringLayout.NORTH, upSwitchButton, 10,SpringLayout.SOUTH, leftSwitchButton);
        menuLayout.putConstraint(SpringLayout.NORTH, downSwitchButton, 10,SpringLayout.SOUTH, rightSwitchButton);
        menuLayout.putConstraint(SpringLayout.EAST,  downSwitchButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  upSwitchButton, 50,SpringLayout.WEST,  this);
        
        //Layout of signal button
        menuLayout.putConstraint(SpringLayout.NORTH, signalButton, 10,SpringLayout.SOUTH, upSwitchButton);
        menuLayout.putConstraint(SpringLayout.EAST,  signalButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  signalButton, 5,SpringLayout.WEST,  this);
        
        //Layout of signal left and right
        menuLayout.putConstraint(SpringLayout.NORTH, leftButton, 10,SpringLayout.SOUTH, signalButton);
        menuLayout.putConstraint(SpringLayout.NORTH, rightButton, 10,SpringLayout.SOUTH, signalButton);
        menuLayout.putConstraint(SpringLayout.EAST,  rightButton,-50,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  leftButton, 50,SpringLayout.WEST,  this);
        
        //Layout of train button
        menuLayout.putConstraint(SpringLayout.NORTH, trainButton, 10,SpringLayout.SOUTH, leftButton);
        menuLayout.putConstraint(SpringLayout.EAST,  trainButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  trainButton, 5,SpringLayout.WEST,  this);
        
        //Layout of remove rail button
        menuLayout.putConstraint(SpringLayout.NORTH, removeRailButton, 10,SpringLayout.SOUTH, trainButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of remove rail button
        menuLayout.putConstraint(SpringLayout.NORTH, removeSwitchRailButton, 10,SpringLayout.SOUTH, removeRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeSwitchRailButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeSwitchRailButton, 5,SpringLayout.WEST, this);
        
        //Layout of remove signal button
        menuLayout.putConstraint(SpringLayout.NORTH, removeSignalButton, 10,SpringLayout.SOUTH, removeSwitchRailButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeSignalButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeSignalButton, 5,SpringLayout.WEST,  this);
        
        //Layout of remove train button
        menuLayout.putConstraint(SpringLayout.NORTH, removeTrainButton, 10,SpringLayout.SOUTH, removeSignalButton);
        menuLayout.putConstraint(SpringLayout.EAST,  removeTrainButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  removeTrainButton, 5,SpringLayout.WEST,  this);
        
        //Layout of clear button
        menuLayout.putConstraint(SpringLayout.NORTH, clearButton, 10,SpringLayout.SOUTH, removeTrainButton);
        menuLayout.putConstraint(SpringLayout.EAST,  clearButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  clearButton, 5,SpringLayout.WEST,  this);
        
        //Layout of save load label
        menuLayout.putConstraint(SpringLayout.SOUTH, loadLabel, -10,SpringLayout.NORTH, loadButton);
        menuLayout.putConstraint(SpringLayout.EAST,  loadLabel,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadLabel, 5,SpringLayout.WEST,  this);
        
        //Layout of load button
        menuLayout.putConstraint(SpringLayout.SOUTH, loadButton, -10,SpringLayout.NORTH, saveButton);
        menuLayout.putConstraint(SpringLayout.EAST,  loadButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  loadButton, 5,SpringLayout.WEST,  this);
        
        //Layout of save button
        menuLayout.putConstraint(SpringLayout.SOUTH, saveButton, -10,SpringLayout.SOUTH, this);
        menuLayout.putConstraint(SpringLayout.EAST,  saveButton,-5,SpringLayout.EAST,  this);
        menuLayout.putConstraint(SpringLayout.WEST,  saveButton, 5,SpringLayout.WEST,  this);
		
	}

	
	
}
