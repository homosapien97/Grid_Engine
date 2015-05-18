package display;

import javax.swing.*;

import java.awt.*;

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
	private static Dimension screenDimensions = new Dimension(WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
	
	//initialization
	public Display() {
		//set screen size
		setPreferredSize(screenDimensions);
		
		//background color
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics page){
		/*
		page.setColor(Color.red);
		page.drawLine(0, 0, WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
		page.setColor(Color.green);
		page.drawLine(P_WIDTH - 1, P_HEIGHT - 1, P_WIDTH - 1, P_HEIGHT - 1);
		*/
		
		Camera.terrainSnapshot();
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				switch(Camera.terrainSnapshot[x][y]){
					case "X":
						drawStone(page, x, y);
						break;
					case "S":
						drawQuickSand(page, x, y);
						break;
					default:
						System.out.print("Unknown terrain type: ");
						System.out.println(Camera.terrainSnapshot[x][y]);
				}
			}
		}
		
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
	
	public static Dimension getDimensions(){
		return screenDimensions;
	}
	
	/**
	 * Draws a stone colored square to represent the player.
	 * @param page the graphics object
	 * @param x the x position for terrain (not pixels)
	 * @param y the y position for terrain (not pixels)
	 */
	private void drawStone(Graphics page, int x, int y){
		page.setColor(Color.gray);
		page.fillRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
		page.setColor(Color.black);
		page.drawRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
	}
	
	/**
	 * Draws a sand colored square to represent the player.
	 * @param page the graphics object
	 * @param x the x position for terrain (not pixels)
	 * @param y the y position for terrain (not pixels)
	 */
	private void drawQuickSand(Graphics page, int x, int y){
		Color sand = new Color(242, 239, 145);
		page.setColor(sand);
		page.fillRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
		page.setColor(Color.black);
		page.drawRect(x * SPRITE_DIM, y * SPRITE_DIM, SPRITE_DIM, SPRITE_DIM);
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
}
