package run;

import display.Camera;
import display.TextDisplay;
import display.Display;
import entity.Player;
import general.Tools;
import terrain.Stone;
import world.Chunk;
import world.LoadedChunks;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Main {
	//example chunk
	static Chunk testChunk = new Chunk(0,0,true,Stone.get());
	
	//player object
	static Player player = new Player(0,0,testChunk, "P", 256, 64, "Player");
	
	//graphics
	static JFrame frame = new JFrame("Grid Game");
	
	public static void step() {
		stepState();
		stepGFX();
	}
	
	private static void stepGFX() {
		//TextDisplay.print();
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static void stepState() {
		player.goToRelative(-1, -1);
	}
	
	private static void init() {
		//general init
		Camera.init(player);
		LoadedChunks.init(testChunk);
		
		//graphics init
		
		//content pane
		frame.getContentPane().add(new Display());
		frame.setSize(Display.getDimensions());
		frame.setBackground(Color.black);
		frame.pack();
		
		//icon
		Image gameicon = null;
		
		File icon = new File("resources\\icons\\game_icon.jpg");
		
		try{
			gameicon = ImageIO.read(icon);
		}catch(IOException e){
			System.out.println(e);
		}
		
		frame.setIconImage(gameicon);
		
		//general
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		init();
		step();
		Tools.time.wait(1000);
		step();
		Tools.time.wait(1000);
		step();
		Tools.time.wait(1000);
		step();
		
//		System.out.print(testChunk.getNeighbor(0, 0).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(1, 0).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(2, 0).terrain[0][0]);
//		System.out.println();
//		System.out.print(testChunk.getNeighbor(0, 1).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(1, 1).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(2, 1).terrain[0][0]);
//		System.out.println();
//		System.out.print(testChunk.getNeighbor(0, 2).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(1, 2).terrain[0][0]);
//		System.out.print(testChunk.getNeighbor(2, 2).terrain[0][0]);
	}
}
