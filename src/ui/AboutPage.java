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
import javax.swing.JTextArea;

import ui.display.Display;
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
			title.setForeground(Color.orange);
			
			main.add(title);
			
			//space
			main.add(Box.createRigidArea(new Dimension(0, 50)));
			
			JTextArea aboutText = new JTextArea();
			aboutText.setForeground(Color.white);
			aboutText.setBackground(new Color(25, 25, 25, 200));
			aboutText.setPreferredSize(new Dimension(1400, 600));
			aboutText.setFont(new Font("Consolas", Font.PLAIN, 16));
			addSources(aboutText);
			
			main.add(aboutText);
			
			//space
			main.add(Box.createRigidArea(new Dimension(0, 50)));
			
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
		
		private void addSources(JTextArea t){
			t.append("Sources\n\n");
			
			t.append("General\n\n");
			
			t.append("Cross Platform Font List: http://templates.mailchimp.com/design/typography/\n");
			t.append("Font Size to Pixel:       http://reeddesign.co.uk/test/points-pixels.html\n");
			t.append("SetVisible hangup:        https://community.oracle.com/thread/1357662\n");
			t.append("                          https://weblogs.java.net/blog/cayhorstmann/archive/2007/06/the_single_thre.html\n");
			t.append("                          https://bitguru.wordpress.com/2007/03/21/will-the-real-swing-single-threading-rule-please-stand-up/\n");
			t.append("JTextArea Resizing:       http://forums.codeguru.com/showthread.php?494250-resizing-text-area-in-a-JFrame\n");
			t.append("Repainting:               http://www.java2s.com/Questions_And_Answers/Swing/Graphics/Repaint.htm\n");
			t.append("Key Binding:              http://www.coderanch.com/t/606742/GUI/java/key-bindings\n");
			t.append("                          http://www.dreamincode.net/forums/topic/245148-java-key-binding-tutorial-and-demo-program/\n");
			t.append("                          http://www.dreamincode.net/forums/topic/245148-java-key-binding-tutorial-and-demo-program/\n");
			t.append("Pathing:                  The book, ISBN 0-201-88259-0\n\n");
			
			
			t.append("A Lot of Stack Overflow Pages...a lot...swing is annoying sometimes... and so is synchronization...\n\n");
			t.append("A Bunch of Java SE 7 API Documentation Pages\n\n");
			t.append("Oracle Community Pages\n\n");
			t.append("Oracle Documentation/Help Pages\n\n");
			t.append("Google Fonts (Cinzel, Cinzel Decorative, Forum)\n\n");
			t.append("Windows Fonts (Consolas)\n\n");
//			t.append("\n\n\n");
//			t.append("Christian's Sources:\n\n");
//			t.append("http://www.stackoverflow.com\n");
//			t.append("http://www.coderanch.com\n");
			
			/*
			t.append("setVisible bug:        http://stackoverflow.com/questions/4413310/setvisible-method-in-java-swing-hangs-system\n");
			t.append("Scrolling:             http://stackoverflow.com/questions/23365847/how-to-auto-scroll-down-jtextarea-after-append\n");
			t.append("Set Size:              http://stackoverflow.com/questions/5714214/set-size-wont-work-in-java\n");
			t.append("Image as a JComponent: http://stackoverflow.com/questions/3775373/java-how-to-add-image-to-jlabel\n");
			t.append("JPanel sizing issues:  http://stackoverflow.com/questions/5921175/how-to-set-jpanels-width-and-height\n");
			t.append("Nested JPanel issue:   http://stackoverflow.com/questions/12021249/adding-jpanel-from-another-class-to-jpanel-in-jframe-begginer-java-programmer");
			t.append("                       http://stackoverflow.com/questions/913139/adding-additional-jpanels-to-a-jpanel\n");
			t.append("Positioning issues:    http://stackoverflow.com/questions/7221876/how-can-i-change-the-position-of-a-jtextfield\n");
			t.append("Repaint issues:        http://stackoverflow.com/questions/5822353/swing-repainting\n");
			t.append("(duplication issue below actually due to code bug but also helped I think)\n");
			t.append("Duplication issues:    http://stackoverflow.com/questions/12506941/jframe-duplicates-drawings-when-resizing\n");
			t.append("                       http://stackoverflow.com/questions/16536670/java-redrawing-duplicate-image\n");
			t.append("Key binding bug:       http://stackoverflow.com/questions/15753551/java-keybindings-how-does-it-work\n");
			t.append("                       http://stackoverflow.com/questions/17984912/java-key-bindings-not-working\n");
			t.append("                       http://stackoverflow.com/questions/11003206/java-jpanel-doesnt-pick-up-keyboard-bindings\n");
			t.append("                       http://stackoverflow.com/questions/15993331/keybinding-code-not-working\n");
			t.append("                       http://stackoverflow.com/questions/22741215/how-to-use-key-bindings-instead-of-key-listeners\n");
			*/
			
			t.append("");
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
						//go back
						Core.mainMenu();
						break;
				}
			}
		}
}