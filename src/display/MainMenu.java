package display;

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

import run.Main;


/**
 * Handle's the displaying and interface of the main menu and any pages that are included in that.
 * @author Michael
 */
@SuppressWarnings("serial")
public class MainMenu extends Display{
	//button stuff
	private final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	//fonts
	private final Font titleFont = new Font("roboto", Font.PLAIN, 100);
	
	public MainMenu(){
		super();
		
		//layout
		//BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		Container main = new Container();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.X_AXIS);
		main.setLayout(mainLayout);
		main.setSize(P_WIDTH, P_HEIGHT);
		
		Container buttons = new Container();
		BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.Y_AXIS);
		buttons.setLayout(buttonLayout);
		
		//space
		main.add(Box.createRigidArea(new Dimension(0,800)));
		
		//title
		JLabel title = new JLabel("GRID GAME");
		title.setAlignmentX(LEFT_ALIGNMENT);
		title.setFont(titleFont);
		title.setForeground(Color.orange);
		
		main.add(title);
		
		//space
		main.add(Box.createRigidArea(new Dimension(100,0)));
		
		//buttons
		JButton play = new JButton("PLAY");
		play.setAlignmentX(RIGHT_ALIGNMENT);
		play.setSize(BUTTON_SIZE);
		play.addActionListener(new ButtonListener(0));
		
		JButton settings = new JButton("SETTINGS");
		settings.setAlignmentX(RIGHT_ALIGNMENT);
		settings.setSize(BUTTON_SIZE);
		settings.addActionListener(new ButtonListener(1));
		
		JButton about = new JButton("ABOUT");
		about.setAlignmentX(RIGHT_ALIGNMENT);
		about.setSize(BUTTON_SIZE);
		about.addActionListener(new ButtonListener(2));
		
		JButton exit = new JButton("EXIT");
		exit.setAlignmentX(RIGHT_ALIGNMENT);
		exit.setSize(BUTTON_SIZE);
		exit.addActionListener(new ButtonListener(3));
		
		buttons.add(play);
		buttons.add(Box.createRigidArea(new Dimension(0,10)));
		buttons.add(settings);
		buttons.add(Box.createRigidArea(new Dimension(0,10)));
		buttons.add(about);
		buttons.add(Box.createRigidArea(new Dimension(0,10)));
		buttons.add(exit);
		
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
					//play
					Main.play();
					break;
				case 1:
					//settings
					Main.dispSettings();
					break;
				case 2:
					//about
					Main.dispAbout();
					break;
				case 3:
					//exit
					Main.exit();
			}
		}
	}
}
