package test;

import world.Chunk;
import entity.Entity;
import entity.Player;
import general.Methods;
import geometry.Beam;
import geometry.Circle;

public class Tester {
//	static String s = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
//	static String s = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	public static void main(String[] args) {
//		for(int i = -18; i < 18; i++) {
//			System.out.print(i + ":" +Methods.absCoordToMinorCoord(i) + "\n");
//			
//		}
		Circle circle = new Circle(0,0,15);
		circle.generateMask();
		for(int i = 0; i < 31; i++) {
			for(int j = 0; j < 31; j++) {
				System.out.print((circle.mask[i][j])?1:0);
			}
			System.out.println();
		}
		System.out.println();
		Beam beam = new Beam(0,0,30,50);
		beam.generateMask();
		for(int j = 0; j < beam.mask[0].length; j++) {
			for(int i = 0; i < beam.mask.length; i++) {
				System.out.print((beam.mask[i][j])?1:0);
			}
			System.out.println();
		}
//		test();
	}
//	public static Chunk chunk = new Chunk();
//	public static Entity e = new Entity(0, 0, chunk, "e");
//	public static Entity f = new Entity(0, 0, chunk, "f");
//	public static Player a = new Player(0, 0, chunk, "a", 0, 0, "~");
//	public static void test() {
//		Class /*<? extends Entity>*/ c = e.getClass();
//		Class /*<? extends Entity>*/ d = f.getClass();
//		Class /*<? extends Entity>*/ z = a.getClass();
//		if(c == d) System.out.println(true); else System.out.println(false);
//		System.out.println(c.equals(d));
//		if(c == z) System.out.println(true); else System.out.println(false);
//		System.out.println(c.equals(z));
//		System.out.println(c.isAssignableFrom(z));
//		System.out.println(z.isAssignableFrom(c));
//	}
}
