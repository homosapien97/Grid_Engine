package general;

import geometry.Point;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import display.Camera;
import display.Display;
import entity.Player;
import world.Chunk;

public class Tools {
	
	//navigational tools
	public static class nav {
		public static int absCoordToChunkCoord(int abs) {
			return (abs > -1) ? (abs/Chunk.DIM) : ((abs - Chunk.DIM + 1) / (Chunk.DIM));
		}
		public static Point absPointToChunkPoint(Point abs) {
			return new Point(absCoordToChunkCoord(abs.x), absCoordToChunkCoord(abs.y));
		}
		
		public static int absCoordToMinorCoord(int abs) {
			return (abs > -1) ? (abs % Chunk.DIM) : ((abs % Chunk.DIM + Chunk.DIM) % Chunk.DIM);
		}
		public static Point absPointToMinorPoint(Point abs) {
			return new Point(absCoordToMinorCoord(abs.x), absCoordToMinorCoord(abs.y));
		}
		
		public static int orthoDistance(int x1, int y1, int x2, int y2) {
			return ((x1 > x2) ? (x1 - x2) : (x2 - x1)) + ((y1 > y2) ? (y1 - y2) : (y2 - y1));
		}
		public static int orthoDistance(Point a, Point b) {
			return ((a.x > b.x) ? (a.x - b.x) : (b.x - a.x)) + ((a.y > b.y) ? (a.y - b.y) : (b.y - a.y));
		}
		
		public static double distance(int x1, int y1, int x2, int y2) {
			return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		}
		public static double distance(Point a, Point b) {
			return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
		}
//		public static int screenCoordToAbsCoord(int screen) {
//			return Display.
//		}
		public static Point screenCoordToAbsCoord(int sx, int sy) {
			return new Point(Player.player.REFACTORSTUFFgetAbsoluteX() - Display.WIDTH/2 + sx, Player.player.REFACTORSTUFFgetAbsoluteY() - Display.HEIGHT/2 + sy);
		}
		public static Point screenCoordToAbsCoord(Point p) {
			return new Point(p, Player.player.REFACTORSTUFFgetAbsoluteX() - Display.WIDTH/2, Player.player.REFACTORSTUFFgetAbsoluteY() - Display.HEIGHT/2);
		}
		public static Point absCoordToScreenCoord(int ax, int ay) {
			return new Point(Display.WIDTH/2 + ax - Player.player.REFACTORSTUFFgetAbsoluteX(), Display.HEIGHT/2 + ay - Player.player.REFACTORSTUFFgetAbsoluteY());
		}
		public static Point absCoordToScreenCoord(Point p) {
			return new Point(p, Display.WIDTH/2 - Player.player.REFACTORSTUFFgetAbsoluteX(), Display.HEIGHT/2 - Player.player.REFACTORSTUFFgetAbsoluteY());
		}
	}
	
	//timing
	public static class time {
		//wait in millis
		public static void wait(int millis){
			try{
			    TimeUnit.MILLISECONDS.sleep(millis);
		    }catch(InterruptedException e){
			    e.printStackTrace();
		    }
		}
	}
	
	//images
	public static class img {
		public static Image loadImage(String filename, String folder){
			Image img = null;
			File temp = new File("resources\\" + folder + "\\" + filename);
			//System.out.println(temp);
			try{
				img = ImageIO.read(temp);
			}catch(IOException e){
				System.out.println("For file \"" + filename + "\":" + e);
			}
			
			return img;
		}
		
		public static Image loadCreatureSprite(String filename){
			Image img = loadImage(filename, "creature");
			return img;
		}
		
		public static Image loadEntitySprite(String filename){
			Image img = loadImage(filename, "entities");
			return img;
		}
		
		public static Image loadHUDSprite(String filename){
			Image img = loadImage(filename, "icons\\hud");
			return img;
		}
		
		public static Image loadTerrainSprite(String filename){
			Image img = loadImage(filename, "terrain");
			return img;
		}

		public static Image loadCard(String filename) {
			Image img = loadImage(filename, "cards");
			return img;
		}
	}
	
	//misc functions
	public static class misc {
		//newline (like </br> in html)
		public static void br(){
			System.out.println();
		}
		
		//multiple newlines
		public static void br(int numNewLines){
			for(int i = 0; i < numNewLines; i++){
				System.out.println();
			}
		}
		
		public static int ilowest(int[] ints, boolean[] ignore) {
			int ret = 0;
			for(int i = 0; i < ints.length; i++) {
				ret = ((ints[i] < ints[ret]) && !ignore[i]) ? (i) : (ret);
			}
			return ret;
		}
		public static int ilowest(double[] doubles, boolean[] ignore) {
			int ret = 0;
			for(int i = 0; i < doubles.length; i++) {
				ret = ((doubles[i] < doubles[ret]) && !ignore[i]) ? (i) : (ret);
			}
			return ret;
		}
		
		public static int[][] offsets(int[] dists) {
			int[][] ret = new int[4][2];
			//X1,X2,X3,X4
			//Y1,Y2,Y3,Y4
			boolean[] ignore = {false, false, false, false};
			int l;
			for(int i = 0; i < 4; i++) {
				 l = ilowest(dists, ignore);
				switch(l) {
				case 0:
					ret[i][0] = 1;
					ret[i][1] = 0;
					break;
				case 1:
					ret[i][0] = 0;
					ret[i][1] = -1;
					break;
				case 2:
					ret[i][0] = -1;
					ret[i][1] = 0;
					break;
				case 3:
					ret[i][0] = 0;
					ret[i][1] = 1;
					break;
				}
				ignore[l] = true;
			}
			return ret;
		}
		public static int[][] offsets(double[] dists) {
			int[][] ret = new int[4][2];
			//X1,X2,X3,X4
			//Y1,Y2,Y3,Y4
			boolean[] ignore = {false, false, false, false};
			int l;
			for(int i = 0; i < 4; i++) {
				 l = ilowest(dists, ignore);
				switch(l) {
				case 0:
					ret[i][0] = 1;
					ret[i][1] = 0;
					break;
				case 1:
					ret[i][0] = 0;
					ret[i][1] = -1;
					break;
				case 2:
					ret[i][0] = -1;
					ret[i][1] = 0;
					break;
				case 3:
					ret[i][0] = 0;
					ret[i][1] = 1;
					break;
				}
				ignore[l] = true;
			}
			return ret;
		}
	}
	
	//data transformation methods
	public static class transform {
		public static int drshift(int i, int shift) {
			return (int)(Double.doubleToRawLongBits(Double.valueOf(i)) >> shift);
		}
		public static int bw(int a, int b) {
			return (a | b) + ~(a ^ b) + (a & b);
		}
		public static int msqrt(int a) {
			return (int)((a - Math.sqrt(Math.abs(a))) % (int)Math.sqrt(Math.abs(a)));
		}
		
	}

	//shortcut/simplifying functions, just put here to make it faster to write
	
	//faster way to do System.out.print and System.out.println
	public static void p(Object obj){
		System.out.print(obj);
	}
	
	public static void pln(Object obj){
		System.out.println(obj);
	}
	
//	public static void main(String[] args) {
//		double[] a = {2.0,1.0,0.0};
//		boolean[] ign = {false, false, true};
//		int l = misc.ilowest(a, ign);
//		System.out.println(l);
//	}
}