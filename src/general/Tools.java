package general;

import geometry.Point;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

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
		public static Image loadTerrainSprite(String filename){
			Image img = null;
			File temp = new File("resources\\terrain\\" + filename);
			
			try{
				img = ImageIO.read(temp);
			}catch(IOException e){
				System.out.println(e);
			}
			
			return img;
		}
	}
	
	//misc functions
	public static class misc {
		//newline (like </br> in html)
		static void br(){
			System.out.println();
		}
		
		//multiple newlines
		static void br(int numNewLines){
			for(int i = 0; i < numNewLines; i++){
				System.out.println();
			}
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
}