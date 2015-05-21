package run;

import display.Camera;
import display.Display;
import display.GameDisplay;
import display.MainMenu;

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
	static GameDisplay gameDisplay = new GameDisplay();
	static MainMenu mainMenu = new MainMenu();
	
	private static void init() {
		//general init
		Camera.init(player);
		LoadedChunks.init(testChunk);
		
		//graphics init
		
		//content pane
		frame.getContentPane().add(gameDisplay);
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
	
	public static void step() {
		stepState();
		stepGraphics();
	}
	
	private static void stepGraphics() {
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static void stepState() {
		player.goToRelative((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
	}
	
	public static void main(String[] args) {
		init();
		
		
		for(int i = 0; i < 40; i++) {
			step();
			Tools.time.wait(500);
		}
		
	}
}
