package general;

import java.util.concurrent.TimeUnit;
import world.Chunk;

public class Tools {
	
	//navigational tools
	public static class nav {
		public static int absCoordToChunkCoord(int abs) {
			return (abs > -1) ? (abs/Chunk.DIM) : ((abs - Chunk.DIM + 1) / (Chunk.DIM));
		}
		
		public static int absCoordToMinorCoord(int abs) {
			return (abs > -1) ? (abs % Chunk.DIM) : ((abs % Chunk.DIM + Chunk.DIM) % Chunk.DIM);
		}
		
		public static int orthoDistance(int x1, int y1, int x2, int y2) {
			return ((x1 > x2) ? (x1 - x2) : (x2 - x1)) + ((y1 > y2) ? (y1 - y2) : (y2 - y1));
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

	//shortcut/simplifying functions, just put here to make it faster to write
	
	//faster way to do System.out.print and System.out.println
	static void p(Object obj){
		System.out.print(obj);
	}
	
	static void pln(Object obj){
		System.out.println(obj);
	}
	
}
