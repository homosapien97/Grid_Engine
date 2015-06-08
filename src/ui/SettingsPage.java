package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import ui.display.Display;
import core.Core;

/**
 * Displays the settings page and provides settings configuration.
 * @author Michael
 */
@SuppressWarnings("serial")
public class SettingsPage extends Display {
	//button stuff
	private final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	public SettingsPage(){
		super();
		
		Container main = new Container();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		main.setSize(P_WIDTH, P_HEIGHT);
		
		Container buttons = new Container();
		BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.Y_AXIS);
		buttons.setLayout(buttonLayout);
		
		//space
		main.add(Box.createRigidArea(new Dimension(0,25)));
		
		//title
		JLabel title = new JLabel("SETTINGS");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Cinzel Decorative", Font.PLAIN, 50));
		title.setForeground(Color.orange);
		
		main.add(title);
		
		//the settings
		
		JLabel graphics = new JLabel("Graphics");
		graphics.setAlignmentX(CENTER_ALIGNMENT);
		graphics.setFont(new Font("Cinzel", Font.PLAIN, 30));
		graphics.setForeground(Color.orange);
		
		//main.add(graphics);
		
		JLabel set1 = new JLabel("Aspect Ratio");
		set1.setAlignmentX(CENTER_ALIGNMENT);
		set1.setFont(MainMenu.bodyFont);
		set1.setForeground(Color.orange);
		
		//main.add(set1);
		
		JComboBox<String> res = new JComboBox<String>();
		res.addItem("16:9");
		res.addItem("4:3");
		res.setAlignmentX(CENTER_ALIGNMENT);
		res.setFont(new Font("Forum", Font.PLAIN, 10));
		
		//buttons.add(res);
		
		//space (temp)
		buttons.add(Box.createRigidArea(new Dimension(0,687)));
		
		
		
		//back button
		JButton mainMenu = newMainButton("BACK");
		mainMenu.setAlignmentX(CENTER_ALIGNMENT);
		mainMenu.setSize(BUTTON_SIZE);
		mainMenu.setFont(MainMenu.buttonFont);
		mainMenu.addActionListener(new ButtonListener(0));
		
		buttons.add(mainMenu);
		
		main.add(buttons);
		
		this.add(main);
	}
	
	public void paintComponent(Graphics page){}
	
	private class ButtonListener implements ActionListener {
		private int buttonIndex;
		
		public ButtonListener(int buttonIndex){
			this.buttonIndex = buttonIndex;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch(this.buttonIndex){
				case 0:
					//go back
					Core.mainMenu();
					break;
			}
		}
	}
}