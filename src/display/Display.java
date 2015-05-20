package display;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class Display extends JPanel {
	//dimensions of sprites in screen pixels
	public static final int SPRITE_DIM = 16;
	
	//units are sprites
	public static final int WIDTH = 96;
	public static final int HEIGHT = 54;
	
	//pixel units
	private static final int P_WIDTH = WIDTH * SPRITE_DIM;
	private static final int P_HEIGHT = HEIGHT * SPRITE_DIM;
	
	//screen size
	private static Dimension screenDimensions = new Dimension(P_WIDTH, P_HEIGHT);
	
	//initialization
	public Display() {
		//set screen size
		setPreferredSize(screenDimensions);
		
		//background color
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics page){
		
		//handle terrain
		Camera.terrainImageSnapshot();
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				//draw this element
				drawTerrain(page, x, y, Camera.terrainImageSnapshot[x][y]);
			}
		}
		
		//handle entities
		Camera.entitySnapshot();
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				if(Camera.entitySnapshot[x][y] != null){
					switch(Camera.entitySnapshot[x][y]){
						case "P":
							drawPlayer(page, x, y);
							break;
						default:
							System.out.print("Unknown entity: ");
							System.out.println(Camera.entitySnapshot[x][y]);
					}
				}
			}
		}
	}
	
	/**
	 * Draws the image of ceratin terrain at a certain location.
	 * @param page the graphics page object
	 * @param x the x location in sprites where the image should be placed
	 * @param y the y location in sprites where the image should be placed
	 * @param img the terrain image
	 */
	private void drawTerrain(Graphics page, int x, int y, Image img){
		page.drawImage(img, x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM, null);
	}
	
	/**
	 * Draws a green square to represent the player.
	 * @param page the graphics object
	 * @param x the x position for entities (not pixels)
	 * @param y the y position for entities (not pixels)
	 */
	private void drawPlayer(Graphics page, int x, int y){
		Color green = new Color(43, 217, 24);
		page.setColor(green);
		page.fillRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
		page.setColor(Color.black);
		page.drawRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
		page.setColor(Color.white);
		page.fillRect((x * SPRITE_DIM) - 8, (y * SPRITE_DIM) - 15, 35, 12);
		page.setColor(Color.darkGray);
		page.drawString("Player", (x * SPRITE_DIM) - 8, (y * SPRITE_DIM) - 5);
	}
	
	public static Dimension getDimensions(){
		return screenDimensions;
	}
}
