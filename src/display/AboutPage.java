package display;

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

import core.Core;

@SuppressWarnings("serial")
public class AboutPage extends Display {
	//button stuff
		private final Dimension BUTTON_SIZE = new Dimension(100, 30);
		
		public AboutPage(){
			super();
			
			Container main = new Container();
			BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
			main.setLayout(mainLayout);
			main.setSize(P_WIDTH, P_HEIGHT);
			
			//space
			main.add(Box.createRigidArea(new Dimension(0,25)));
			
			//title
			JLabel title = new JLabel("ABOUT");
			title.setAlignmentX(CENTER_ALIGNMENT);
			title.setFont(new Font("Cinzel Decorative", Font.PLAIN, 50));
			
			main.add(title);
			
			//space (temp) (about will go here)
			main.add(Box.createRigidArea(new Dimension(0,700)));
			
			//back button
			JButton mainMenu = newMainButton("BACK");
			mainMenu.setAlignmentX(CENTER_ALIGNMENT);
			mainMenu.setSize(BUTTON_SIZE);
			mainMenu.setFont(MainMenu.buttonFont);
			mainMenu.addActionListener(new ButtonListener(0));
			
			main.add(mainMenu);
			
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