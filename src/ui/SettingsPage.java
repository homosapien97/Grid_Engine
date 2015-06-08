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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ui.display.Display;
import core.Core;
import core.Settings;

/**
 * Displays the settings page and provides settings configuration.
 * @author Michael
 */
@SuppressWarnings({ "serial", "unused" })
public class SettingsPage extends Display {
	//button stuff
	private final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	private JTextField inv;
	private JTextField hud;
	
	public SettingsPage(){
		super();
		
		Container main = new Container();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		main.setSize(P_WIDTH, P_HEIGHT);
		
		Container buttons = new Container();
		BoxLayout buttonLayout = new BoxLayout(buttons, BoxLayout.X_AXIS);
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
		
		/*
		JLabel graphics = new JLabel("Graphics");
		graphics.setAlignmentX(CENTER_ALIGNMENT);
		graphics.setFont(new Font("Cinzel", Font.PLAIN, 30));
		graphics.setForeground(Color.orange);
		
		main.add(graphics);
		
		JLabel set1 = new JLabel("Aspect Ratio");
		set1.setAlignmentX(CENTER_ALIGNMENT);
		set1.setFont(MainMenu.bodyFont);
		set1.setForeground(Color.orange);
		
		main.add(set1);
		
		JComboBox<String> res = new JComboBox<String>();
		res.addItem("16:9");
		res.addItem("4:3");
		res.setAlignmentX(CENTER_ALIGNMENT);
		res.setFont(new Font("Forum", Font.PLAIN, 10));
		
		buttons.add(res);
		*/
		
		JLabel ctrls = new JLabel("Controls");
		ctrls.setAlignmentX(CENTER_ALIGNMENT);
		ctrls.setFont(new Font("Cinzel", Font.PLAIN, 30));
		ctrls.setForeground(Color.orange);
		
		main.add(ctrls);
		
		Container inventory = new Container();
		BoxLayout iL = new BoxLayout(inventory, BoxLayout.X_AXIS);
		inventory.setLayout(iL);
		
		JLabel label1 = new JLabel("Inventory Toggle: ");
		label1.setAlignmentX(CENTER_ALIGNMENT);
		label1.setFont(MainMenu.bodyFont);
		label1.setForeground(Color.orange);
		
		inv = new JTextField(Settings.inventoryKeyBind);
		inv.setForeground(Color.black);
		inv.setBackground(Color.gray);
		inv.setBorder(null);
		
		inventory.add(label1);
		inventory.add(inv);
		
		main.add(inventory);
		
		//space
		main.add(Box.createRigidArea(new Dimension(0, 10)));
		
		Container hudC = new Container();
		BoxLayout hL = new BoxLayout(hudC, BoxLayout.X_AXIS);
		hudC.setLayout(hL);
		
		JLabel label2 = new JLabel("HUD Toggle: ");
		label2.setAlignmentX(CENTER_ALIGNMENT);
		label2.setFont(MainMenu.bodyFont);
		label2.setForeground(Color.orange);
		
		hud = new JTextField(Settings.hudKeyBind);
		hud.setForeground(Color.black);
		hud.setBackground(Color.gray);
		hud.setBorder(null);
		
		hudC.add(label2);
		hudC.add(hud);
		
		main.add(hudC);
		
		//space
		main.add(Box.createRigidArea(new Dimension(0, 588))); //687 without any settings
		
		//back button
		JButton mainMenu = newMainButton("BACK");
		mainMenu.setAlignmentX(CENTER_ALIGNMENT);
		mainMenu.setSize(BUTTON_SIZE);
		mainMenu.setFont(MainMenu.buttonFont);
		mainMenu.addActionListener(new ButtonListener(0));
		
		//submit button
		JButton save = newMainButton("SAVE");
		save.setAlignmentX(CENTER_ALIGNMENT);
		save.setSize(BUTTON_SIZE);
		save.setFont(MainMenu.buttonFont);
		save.addActionListener(new ButtonListener(1));

		buttons.add(save);
		buttons.add(Box.createRigidArea(new Dimension(20, 0)));
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
				case 1:
					//save settings changes
					Settings.setInventoryKeyBind(inv.getText());
					Settings.setHUDKeyBind(hud.getText());
					
					Core.addKeyBinds(Core.gameDisplay, true);
					
					JOptionPane.showMessageDialog(getRootPane(), "Settings saved!", "Grid Game Settings", JOptionPane.DEFAULT_OPTION);
					
					Core.frame.repaint(); //incase the JOptionPane leaves a black square where it was
					
					inv.setText(Settings.inventoryKeyBind);
					hud.setText(Settings.hudKeyBind);
					break;
			}
		}
	}
}