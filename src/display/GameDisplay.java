package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import run.Main;
import key_actions.ToggleHUD;
import core.Core;
import entity.Player;
import general.Tools;

@SuppressWarnings("serial")
public class GameDisplay extends Display {
	//states of toggleable things
	private static Boolean hudVisible = true;
	
	//HUD icons
	private static Image heart_transparent = null;
	private static Image heart_opaque = null;
	
	//fonts
	public static final Font bodyFont = new Font("Forum", Font.PLAIN, 18);
	
	public GameDisplay(){
		super();
		
		this.setFocusable(true);
		this.requestFocusInWindow();  
	}
	
	public void paintComponent(Graphics page){
		//handle terrain
		Camera.terrainImageSnapshot();
		
		for(int x = 0; x < Display.WIDTH; x++) {
			for(int y = 0; y < Display.HEIGHT; y++) {
				//draw this element
				drawTerrain(page, x, y, Camera.terrainImageSnapshot[x][y]);
			}
		}
		
		//handle entities
		Camera.entitySnapshot();
		
		for(int x = 0; x < Display.WIDTH; x++) {
			for(int y = 0; y < Display.HEIGHT; y++) {
				if(Camera.entitySnapshot[x][y] != null){
					switch(Camera.entitySnapshot[x][y]){
						case "P":
							drawPlayer(page, x, y, Player.sprite);
							break;
						default:
							System.out.print("Unknown entity: ");
							System.out.println(Camera.entitySnapshot[x][y]);
					}
				}
			}
		}
		
		//handle UI overlay
		drawHUD(page);
		//System.out.println(this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).get(KeyStroke.getKeyStroke(KeyEvent.H, 0)));
	}
	
	/**
	 * Draws the image of ceratin terrain at a certain location.
	 * @param page the graphics page object
	 * @param x the x location in sprites where the image should be placed
	 * @param y the y location in sprites where the image should be placed
	 * @param img the terrain image
	 */
	private void drawTerrain(Graphics page, int x, int y, Image img){
		page.drawImage(img, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
	}
	
	/**
	 * Draws the image of the player at a certain location.
	 * @param page the graphics object
	 * @param x the x position for entities (not pixels)
	 * @param y the y position for entities (not pixels)
	 * @param img the player image file
	 */
	private void drawPlayer(Graphics page, int x, int y, Image img){
		page.drawImage(img, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
	}
	
	private void redHighlight(Graphics page, int x, int y){
		page.drawImage(Core.redHighlight, x * Display.SPRITE_DIM, y * Display.SPRITE_DIM, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
	}
	
	//game components
	
	private void drawHUD(Graphics page){
		if(hudVisible){
			//background
			page.setColor(new Color(89, 89, 89, 100));
			page.fillRect(0, 0, Display.P_WIDTH, 32);
			
			//player name
			page.setColor(Color.white);
			page.setFont(bodyFont);
			page.drawString(Main.player.name, 50, 21);
			
			//player health
			page.drawImage(heart_opaque, 150, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			
			page.setColor(Color.white);
			page.drawString("" + Main.player.health, 180, 21);
		}
	}
	
	//toggle actions
	
	public static void toggleHUD(){
		hudVisible = !hudVisible;
	}
	
	//loading
	
	public static void load(){
		heart_transparent = Tools.img.loadImage("translucent-heart.png", "icons\\hud"); 
		heart_opaque = Tools.img.loadImage("opaque-heart.png", "icons\\hud"); 
	}
}
