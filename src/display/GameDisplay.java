package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import key_actions.ToggleHUD;
import core.Core;
import entity.Player;

@SuppressWarnings("serial")
public class GameDisplay extends Display {
	//states of toggleable things
	private static Boolean hudVisible = true;
	
	public GameDisplay(){
		super();
		
		Action ToggleHUDAction = new ToggleHUD();
		
		//config
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		//add to input map
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "toggleHUD");
		
		//add to action map
		this.getActionMap().put("toggleHUD", ToggleHUDAction);
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
	
	//game components
	
	private void drawHUD(Graphics page){
		if(hudVisible){
			page.setColor(Color.GRAY);
			page.fillRect(0, 0, Display.P_WIDTH, 50);
		}
	}
	
	//toggle actions
	
	public static void toggleHUD(){
		hudVisible = !hudVisible;
	}
}
