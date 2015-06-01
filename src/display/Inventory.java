package display;

import general.Tools;

import java.awt.Container;
import java.awt.Image;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class Inventory extends Display {
	
	//the items the player can get
	/*
	private static Image bow1 = null;
	private static Image bow2 = null;
	private static Image bow3 = null;
	private static Image bow4 = null;
	private static Image bow5 = null;
	private static Image bow6 = null;
	private static Image bow7 = null;
	private static Image shield1 = null;
	private static Image shield2 = null;
	private static Image shield3 = null;
	private static Image shield4 = null;
	private static Image shield5 = null;
	private static Image shield6 = null;
	private static Image shield7 = null;
	private static Image sword1 = null;
	private static Image sword2 = null;
	private static Image sword3 = null;
	private static Image sword4 = null;
	private static Image sword5 = null;
	private static Image sword6 = null;
	private static Image sword7 = null;
	
	private static Image earth1 = null;
	private static Image earth2 = null;
	private static Image earth3 = null;
	private static Image earth4 = null;
	private static Image earth5 = null;
	private static Image earth6 = null;
	private static Image earth7 = null;
	private static Image fire1 = null;
	private static Image fire2 = null;
	private static Image fire3 = null;
	private static Image fire4 = null;
	private static Image fire5 = null;
	private static Image fire6 = null;
	private static Image fire7 = null;
	private static Image plasma1 = null;
	private static Image plasma2 = null;
	private static Image plasma3 = null;
	private static Image plasma4 = null;
	private static Image plasma5 = null;
	private static Image plasma6 = null;
	private static Image plasma7 = null;
	private static Image water1 = null;
	private static Image water2 = null;
	private static Image water3 = null;
	private static Image water4 = null;
	private static Image water5 = null;
	private static Image water6 = null;
	private static Image water7 = null;
	*/
	
	private static Image[] bows = new Image[7];
	private static Image[] shields = new Image[7];
	private static Image[] swords = new Image[7];
	
	private static Image[] earth = new Image[7];
	private static Image[] fire = new Image[7];
	private static Image[] plasma = new Image[7];
	private static Image[] water = new Image[7];
	
	private static Image mystery1 = null;
	
	public Inventory(){
		Container main = new Container();
		BoxLayout bodyLayout = new BoxLayout(main, BoxLayout.X_AXIS);
		main.setLayout(bodyLayout);
		main.setSize(P_WIDTH, P_HEIGHT);
		
	}
	
	public static class load {
		public void bow(){
//			bow1 = Tools.img.loadCard("bow1.png");
//			bow2 = Tools.img.loadCard("bow2.png");
//			bow3 = Tools.img.loadCard("bow3.png");
//			bow4 = Tools.img.loadCard("bow4.png");
//			bow5 = Tools.img.loadCard("bow5.png");
//			bow6 = Tools.img.loadCard("bow6.png");
//			bow7 = Tools.img.loadCard("bow7.png");
			for(int i = 0; i < bows.length; i++){
				bows[i] = Tools.img.loadCard("bow" + (i + 1) + ".png");
			}
		}
		
		public void sword(){
//			sword1 = Tools.img.loadCard("sword1.png");
//			sword2 = Tools.img.loadCard("sword2.png");
//			sword3 = Tools.img.loadCard("sword3.png");
//			sword4 = Tools.img.loadCard("sword4.png");
//			sword5 = Tools.img.loadCard("sword5.png");
//			sword6 = Tools.img.loadCard("sword6.png");
//			sword7 = Tools.img.loadCard("sword7.png");
		}
		
		public void shield(){
//			shield1 = Tools.img.loadCard("shield1.png");
//			shield2 = Tools.img.loadCard("shield2.png");
//			shield3 = Tools.img.loadCard("shield3.png");
//			shield4 = Tools.img.loadCard("shield4.png");
//			shield5 = Tools.img.loadCard("shield5.png");
//			shield6 = Tools.img.loadCard("shield6.png");
//			shield7 = Tools.img.loadCard("shield7.png");
		}
		
	}
}
