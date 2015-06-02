package display;

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

import spells.BowSpell;
import spells.EarthSpell;
import spells.FireSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.SwordSpell;
import spells.WaterSpell;

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
		close.addActionListener(new ButtonListener());
		
		main.add(close);
		
		this.add(main);
	}
	
	private static void addCards(Container c){
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(EarthSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(FireSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(WaterSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(PlasmaSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(BowSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(SwordSpell.sprite[i], i));
		}
		
		for(int i = 0; i < 7; i++){
			c.add(getCardAsComponent(ShieldSpell.sprite[i], i));
		}
	}
	
	private static Container getCardAsComponent(Image img, int i){
		Container c = new Container();
		BoxLayout l = new BoxLayout(c, BoxLayout.X_AXIS);
		c.setLayout(l);
		
		JLabel image = new JLabel();
		JLabel amount = new JLabel(" x " + 0);
		
		amount.setFont(bodyFont);
		amount.setForeground(new Color(201, 166, 137));
		
		ImageIcon icon = new ImageIcon(img);
		
		image.setIcon(icon);
		image.setSize(32, 32);
		
		c.add(image);
		c.add(amount);
		
		return c;
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameDisplay.toggleInventory();
		}
	}
}
