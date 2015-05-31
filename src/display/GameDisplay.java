package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import action.Clock;
import run.Main;
import core.Core;
import entity.Player;
import general.Tools;

@SuppressWarnings("serial")
public class GameDisplay extends Display {
	//states of toggleable things
	private static Boolean hudVisible = true;
	
	//HUD icons
	private static Image broken_heart = null;
	private static Image heart = null;
	
	private static Image broken_shield = null;
	private static Image shield = null;
	
	private static Image tick_clock = null;
	
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
			page.setColor(new Color(89, 89, 89, 255));
			page.fillRect(0, 0, Display.P_WIDTH, 32);
			
			//player name
			page.setColor(Color.white);
			page.setFont(bodyFont);
			page.drawString(Main.player.name, 50, 21);
			
			//player health
			if(Main.player.health > 100) {
				page.drawImage(heart, 150, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			} else {
				page.drawImage(broken_heart, 150, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			}
			
			page.setColor(Color.white);
			page.drawString("" + Main.player.health, 180, 21);
			
			//player armor
			if(Main.player.armor > 25) {
				page.drawImage(shield, 250, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			} else {
				page.drawImage(broken_shield, 250, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			}
			
			page.setColor(Color.white);
			page.drawString("" + Main.player.armor, 280, 21);
			
			//tick clock
			page.drawImage(tick_clock, 350, 8, Display.SPRITE_DIM, Display.SPRITE_DIM, null);
			
			page.setColor(Color.white);
			page.drawString("" + Clock.getTicks(), 380, 21);
		}
	}
	
	//toggle actions
	
	public static void toggleHUD(){
		hudVisible = !hudVisible;
	}
	
	//loading
	
	public static void load(){
		heart = Tools.img.loadHUDSprite("heart.png");
		broken_heart = Tools.img.loadHUDSprite("broken-heart.png");

		shield = Tools.img.loadHUDSprite("shield.png");
		broken_shield = Tools.img.loadHUDSprite("broken-shield.png");
		
		tick_clock = Tools.img.loadHUDSprite("tick-clock.png");
	}
}
