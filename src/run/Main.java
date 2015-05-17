package run;

import display.Camera;
import display.TextDisplay;
import display.Display;

import entity.Player;

import terrain.Stone;

import world.Chunk;
import world.LoadedChunks;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

public class Main {
	//example chunk
	static Chunk testChunk = new Chunk(0,0,true,Stone.get());
	
	//player object
	static Player player = new Player(0,0,testChunk, "0", 256, 64, "Player");
	
	//graphics
	static JFrame display = new JFrame("Grid Game");
	
	public static void step() {
		stepState();
		stepGFX();
	}
	
	private static void stepGFX() {
		//TextDisplay.print();
		display.setVisible(true);
	}
	
	private static void stepState() {
		player.goToAbsolute(-1, -1);
	}
	
	private static void init() {
		//general init
		Camera.init(player);
		LoadedChunks.init(testChunk);
		
		//graphics init
		
		//content pane
		display.getContentPane().add(Display.getDisplay());
		display.pack();
		
		//size
		display.setSize(Display.DIMENSIONS);
		
		//icon
		Image gameicon = null;
		
		File icon = new File("resources\\icons\\game_icon.jpeg");
		
		try{
			gameicon = ImageIO.read(icon);
		}catch(IOException e){}
		
		display.setIconImage(gameicon);
		
		//general
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		init();
		step();
	}
}
