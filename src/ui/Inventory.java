package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.Core;
import core.GameState;
import entity.Player;
import spells.BowSpell;
import spells.EarthSpell;
import spells.FireSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.SwordSpell;
import spells.WaterSpell;
import ui.display.Display;
import ui.display.GameDisplay;
import ui.key_actions.ToggleInventory;

@SuppressWarnings("serial")
public class Inventory extends Display {
	
	//private static Image mystery1 = null;
	
	//fonts
	private static final Font inventoryFont = new Font("Cinzel Decorative", Font.PLAIN, 30);
	public static final Font buttonFont = new Font("Cinzel", Font.PLAIN, 20);
	private static final Font bodyFont = new Font("Forum", Font.PLAIN, 18);
	
	public Inventory(){
		super();

		this.setBackground(new Color(110, 52, 4, 200));
		
		Color borderColor = new Color(61, 28, 1);
		Border line = new LineBorder(borderColor, 4);
		this.setBorder(line);
		
		Container main = new Container();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		
		JLabel title = new JLabel("Inventory");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(inventoryFont);
		title.setForeground(Color.white);
		
		main.add(title);
		
		main.add(Box.createRigidArea(new Dimension(0,32)));
		
		Container inventory = new Container();
		GridLayout inventoryLayout = new GridLayout(7, 7, 50, 32);
		inventory.setLayout(inventoryLayout);
		
		addCards(inventory);
		
		main.add(inventory);

		main.add(Box.createRigidArea(new Dimension(0,100)));
		
		JButton close = newMainButton("CLOSE");
		close.setAlignmentX(CENTER_ALIGNMENT);
		close.setFont(buttonFont);
		close.addActionListener(new CloseButtonListener());
		
		main.add(close);
		
		this.add(main);
	}
	
	private static void addCards(Container c){
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(BowSpell.sprite[i], i, spell.BOW_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(EarthSpell.sprite[i], i, spell.EARTH_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(FireSpell.sprite[i], i, spell.FIRE_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(PlasmaSpell.sprite[i], i, spell.PLASMA_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(ShieldSpell.sprite[i], i, spell.SHIELD_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(SwordSpell.sprite[i], i, spell.SWORD_SPELL));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(WaterSpell.sprite[i], i, spell.WATER_SPELL));
		}
	}
	
	private static Container getCardAsComponent(Image img, int i, spell s){
		Container c = new Container();
		BoxLayout l = new BoxLayout(c, BoxLayout.X_AXIS);
		c.setLayout(l);
		
		int x = 0;
		
		switch(s){
			case BOW_SPELL:
				x = Player.player.spellInventory.nums[i];
				break;
			case EARTH_SPELL:
				x = Player.player.spellInventory.nums[i + 7];
				break;
			case FIRE_SPELL:
				x = Player.player.spellInventory.nums[i + 14];
				break;
			case PLASMA_SPELL:
				x = Player.player.spellInventory.nums[i + 21];
				break;
			case SHIELD_SPELL:
				x = Player.player.spellInventory.nums[i + 28];
				break;
			case SWORD_SPELL:
				x = Player.player.spellInventory.nums[i + 35];
				break;
			case WATER_SPELL:
				x = Player.player.spellInventory.nums[i + 42];
				break;
		}
		
		String count;
		
		if(x > 999){
			count = "a lot";
		}else{
			count = Integer.toString(x);
		}
		
		JButton image = new JButton();
		JLabel amount = new JLabel(" x " + count);
		
		amount.setFont(bodyFont);
		amount.setForeground(new Color(201, 166, 137));
		
		ImageIcon icon = new ImageIcon(img);
		
		image.setForeground(Color.black);
		image.setBackground(Color.black);
		
		Color hoverColor = new Color(219, 158, 107);
		Color pressedColor = new Color(135, 90, 53);
		Color borderColor = new Color(110, 52, 4);
		
		Border line = new LineBorder(borderColor, 2);
		Border hover = new LineBorder(hoverColor, 2);
		Border pressed = new LineBorder(pressedColor, 2);
		
		image.setBorder(line);
		
		image.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if(image.getModel().isPressed()){
					image.setBorder(pressed);
		        }else if(image.getModel().isRollover()){
		        	image.setBorder(hover);
		        }else{
		        	image.setBorder(line);
		        }
			}
		});
		
		int index = i + 7 * s.ordinal();
		
		image.setToolTipText("" + index);
		
		image.setPreferredSize(new Dimension(32, 32));
		
		image.setIcon(icon);
		
		image.addActionListener(new SpellButtonListener(index));
		
		c.add(image);
		c.add(amount);
		
		return c;
	}
	
	private class CloseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameDisplay.toggleInventory();
			
			if(Core.gameState == GameState.PAUSED) {
				Core.gameState = ToggleInventory.oldState;
			} else {
				ToggleInventory.oldState = Core.gameState;
				Core.gameState = GameState.PAUSED;
			}
		}
	}
	
	private static class SpellButtonListener implements ActionListener {
		private int index = -1;
		
		public SpellButtonListener(int index){
			this.index = index;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	private enum spell { BOW_SPELL, EARTH_SPELL, FIRE_SPELL, PLASMA_SPELL, SHIELD_SPELL, SWORD_SPELL, WATER_SPELL }
}
