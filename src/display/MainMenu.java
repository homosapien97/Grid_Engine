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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import run.Main;


/**
 * Handle's the displaying and interface of the main menu and any pages that are included in that.
 * @author Michael
 */
@SuppressWarnings("serial")
public class MainMenu extends Display{
	//button stuff
	//private final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	//fonts
	public static final Font titleFont = new Font("Cinzel Decorative", Font.PLAIN, 100);
	public static final Font buttonFont = new Font("Cinzel", Font.PLAIN, 20);
	public static final Font bodyFont = new Font("Forum", Font.PLAIN, 20);
	
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
		JButton play = newMainButton("PLAY");
		play.setAlignmentX(RIGHT_ALIGNMENT);
		//play.setSize(BUTTON_SIZE);
		play.setFont(buttonFont);
		play.addActionListener(new ButtonListener(0));
		
		JButton settings = newMainButton("SETTINGS");
		settings.setAlignmentX(RIGHT_ALIGNMENT);
		//settings.setSize(BUTTON_SIZE);
		settings.setFont(buttonFont);
		settings.addActionListener(new ButtonListener(1));
		
		JButton about = newMainButton("ABOUT");
		about.setAlignmentX(RIGHT_ALIGNMENT);
		//about.setSize(BUTTON_SIZE);
		about.setFont(buttonFont);
		about.addActionListener(new ButtonListener(2));
		
		JButton exit = newMainButton("EXIT");
		exit.setAlignmentX(RIGHT_ALIGNMENT);
		//exit.setSize(BUTTON_SIZE);
		exit.setFont(buttonFont);
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
	
	public void paintComponent(Graphics page){
		//debug info
		page.setColor(Color.white);
		page.drawString("VERSION 1.0", 0, 10);
	}
	
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
