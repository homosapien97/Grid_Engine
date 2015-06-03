package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import action.Clock;
import run.Main;
import core.Core;
//import entity.Player;
import general.Tools;

@SuppressWarnings("serial")
public class GameDisplay extends Display {
	//states of toggleable things
	private static Boolean hudVisible = true;
	private static Boolean cmdLogVisible = false;
	private static Boolean inventoryVisible = false;
	
	//HUD icons
	private static Image broken_heart = null;
	private static Image heart = null;
	
	private static Image broken_shield = null;
	private static Image shield = null;
	
	private static Image tick_clock = null;
	
	//fonts
	private static final Font bodyFont = new Font("Forum", Font.PLAIN, 18);
	private static final Font cmdFont = new Font("Consolas", Font.PLAIN, 16);
	
	//cmd
	private Container cmd = new Container();
	public static JTextField cmdInput = new JTextField();
	
	public static JTextArea cmdLog = new JTextArea();
	JScrollPane logScrollPane = null;
	
	//inventory
	private Container main = new Container();
	private Container center = new Container();
	public Inventory inventory = new Inventory();
	
	//mouse listener
	GridMouseListener listener = new GridMouseListener();
	
	//mouse clicks
	private static Image leftClickHighlight = null;
	private static Image rightClickHighlight = null;
	
	public GameDisplay(){
		super();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		//root layout
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		//command box
		BoxLayout cmdLayout = new BoxLayout(cmd, BoxLayout.Y_AXIS);
		cmd.setLayout(cmdLayout);
		
		//command log
		
		cmdLog.setFont(cmdFont);
		cmdLog.setBackground(new Color(25, 25, 25, 100));
		cmdLog.setForeground(new Color(200, 200, 200, 255));
		Border logBorder = new LineBorder(new Color(25, 25, 25, 200));
		cmdLog.setBorder(logBorder);
		cmdLog.setVisible(cmdLogVisible);
		cmdLog.setFocusable(false);
		//cmdLog.setPreferredSize(new Dimension(P_WIDTH, 22 * 10)); 
		
		//logScrollPane.add(cmdLog);

		logScrollPane = new JScrollPane(cmdLog);
		
		logScrollPane.setPreferredSize(new Dimension(P_WIDTH, 22 * 10)); //22px per line for 16pt font cause yay http://reeddesign.co.uk/test/points-pixels.html
		logScrollPane.setBackground(new Color(0, 0, 0, 0));
		logScrollPane.setForeground(new Color(0, 0, 0, 0));
		logScrollPane.setVisible(cmdLogVisible);
		logScrollPane.setWheelScrollingEnabled(true);
		logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		cmd.add(logScrollPane);
		
		//command input
		
		cmdInput.setBackground(new Color(50, 50, 50, 255));
		cmdInput.setForeground(Color.white);
		cmdInput.setFont(cmdFont);
		Border cmdBorder = new LineBorder(new Color(50, 50, 50, 255));
		cmdInput.setBorder(cmdBorder);
		cmdInput.setVisible(true);
		
		cmd.add(cmdInput);
		
		this.add(cmd, BorderLayout.PAGE_END);
		
		//inventory (manual padding everywhere)
		
		inventory.setVisible(inventoryVisible);
		
		BoxLayout layout_inv = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(layout_inv);
		
		main.add(Box.createRigidArea(new Dimension(0,100)));
		
		BoxLayout layout_inv2 = new BoxLayout(center, BoxLayout.X_AXIS);
		center.setLayout(layout_inv2);
		
		center.add(Box.createRigidArea(new Dimension(350,0)));
		center.add(inventory, BorderLayout.CENTER);
		center.add(Box.createRigidArea(new Dimension(350,0)));
		
		main.add(center);
		
		main.add(Box.createRigidArea(new Dimension(0,100)));
		
		this.add(main);
		
		this.addMouseListener(listener);
	}
	
	public void paintComponent(Graphics page){
		//handle terrain
		Camera.terrainImageSnapshot();
		
		for(int x = 0; x < Display.WIDTH; x++) {
			for(int y = 0; y < Display.HEIGHT; y++) {
				drawSprite(page, x, y, Camera.terrainImageSnapshot[x][y]);
			}
		}
		
		//handle entities
		Camera.entityImageSnapshot();
		
		for(int x = 0; x < Display.WIDTH; x++) {
			for(int y = 0; y < Display.HEIGHT; y++) {
				if(Camera.entityImageSnapshot[x][y] != null) {
					drawSprite(page, x, y, Camera.entityImageSnapshot[x][y]);
				}
			}
		}
		
		//handle action highlighting
		Camera.highlightSnapshot();
		
		for(int x = 0; x < Display.WIDTH; x++) {
			for(int y = 0; y < Display.HEIGHT; y++) {
				highlight(page, Camera.highlightSnapshot[x][y], x, y);
			}
		}
		
		//mouse clicks
		drawClicks(page);
		
		//handle UI overlays
		drawHUD(page);
		CMD_UI(page);
		drawInventory(page);
	}
	
	private void drawSprite(Graphics page, int x, int y, Image img) {
		page.drawImage(img, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
		
	}
	
	private void highlight(Graphics page, char c, int x, int y){
//		page.drawImage(Core.redHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
		switch(c) {
		case Camera.NONE:
			break;
		case Camera.SPELL:
			page.drawImage(Core.purpleHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			break;
		case Camera.RADIUS:
			page.drawImage(Core.cyanHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			break;
		case Camera.GENERIC:
			page.drawImage(Core.greenHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			break;
		case Camera.PATH:
			page.drawImage(Core.redHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			break;
		default:
		}
	}
	
	private void drawClicks(Graphics page){
		int x = listener.getCoordinateClicked().x;
		int y = listener.getCoordinateClicked().y;
		
		Image img = (listener.getClickButton() == 1) ? leftClickHighlight : rightClickHighlight;
		
		page.drawImage(img, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
	}
	
	//command logging
	public static void submitCommand(){
		if(!cmdInput.getText().equals("")){
			if(cmdLog.getText().equals("")){
				cmdLog.setText(cmdInput.getText());
			}else{
				cmdLog.append("\n" + cmdInput.getText());
			}
			
			cmdInput.setText("");
			
			Core.frame.getContentPane().repaint();
		}
	}
	
	//game components
	
	private void drawHUD(Graphics page){
		if(hudVisible){
			//background
			page.setColor(new Color(89, 89, 89, 255));
			page.fillRect(0, 0, Display.P_WIDTH, 48);
			
			//player name
			page.setColor(Color.white);
			page.setFont(bodyFont);
			page.drawString(Main.player.name, 50, 29);
			
			//player health
			if(Main.player.health > 100) {
				page.drawImage(heart, 150, 8, 32, 32, null);
			} else {
				page.drawImage(broken_heart, 150, 8, 32, 32, null);
			}
			
			page.setColor(Color.white);
			page.drawString("" + Main.player.health, 196, 29);
			
			//player armor
			if(Main.player.armor > 25) {
				page.drawImage(shield, 250, 8, 32, 32, null);
			} else {
				page.drawImage(broken_shield, 250, 8, 32, 32, null);
			}
			
			page.setColor(Color.white);
			page.drawString("" + Main.player.armor, 296, 29);
			
			//tick clock
			page.drawImage(tick_clock, 350, 8, 32, 32, null);
			
			page.setColor(Color.white);
			page.drawString("" + Clock.getTicks(), 396, 29);
		}
	}
	
	private void CMD_UI(Graphics page){
		cmdLog.setVisible(cmdLogVisible);
		logScrollPane.setVisible(cmdLogVisible);
		
		if(cmdLogVisible){
			//cmd input
			cmdInput.requestFocus();
			
			//cmd log
			//cmdLog.append("test");
		}else{
			if(inventory.isVisible()){
				inventory.requestFocus();
			}else{
				this.requestFocusInWindow();
			}
		}
	}
	
	private void drawInventory(Graphics page){
		if(inventoryVisible){
			inventory.setVisible(true);
			
			if(cmdLogVisible){
				cmdInput.requestFocus();
			}else{
				inventory.requestFocus();
			}
			
		}else{
			inventory.setVisible(false);
		}
	}
	
	//toggle actions
	
	public static void toggleHUD(){
		hudVisible = !hudVisible;
		
		if(hudVisible){
			Core.frame.getContentPane().repaint();
		}
	}
	
	public static void toggleCMDLog(){
		cmdLogVisible = !cmdLogVisible;
		
		if(cmdLogVisible){
			Core.frame.getContentPane().repaint();
		}
	}
	
	public static void toggleInventory(){
		inventoryVisible = !inventoryVisible;
		
		if(inventoryVisible){
			Core.frame.getContentPane().repaint();
		}
	}
	
	//loading
	
	public static void load(){
		heart = Tools.img.loadHUDSprite("heart.png");
		broken_heart = Tools.img.loadHUDSprite("broken-heart.png");

		shield = Tools.img.loadHUDSprite("shield.png");
		broken_shield = Tools.img.loadHUDSprite("broken-shield.png");
		
		tick_clock = Tools.img.loadHUDSprite("tick-clock.png");
		
		leftClickHighlight = Tools.img.loadImage("leftClickHighlight.png", "general");
		rightClickHighlight = Tools.img.loadImage("rightClickHighlight.png", "general");
	}
	
	//mouse listener
	
	public class GridMouseListener implements MouseListener{
		//note to christian: this is java.awt.Point, not yours
		private Point clickedGridPoint = new Point(-1, -1);
		private int button = 0;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			//rounding makes it bad
			button = e.getButton();
			
			clickedGridPoint.x = (int)/* Math.round*/((double) e.getX() / (double) Display.SPRITE_DIM);
			clickedGridPoint.y = (int)/* Math.round*/((double) e.getY() / (double) Display.SPRITE_DIM);
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
		public Point getCoordinateClicked(){
			return clickedGridPoint;
		}
		
		public int getClickButton(){
			return button;
		}
	}
}
