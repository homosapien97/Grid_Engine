package test;

import java.util.HashMap;

import world.Chunk;
import entity.Entity;
import entity.Player;
import general.Tools;
import geometry.Line;
import geometry.Circle;
import geometry.Point;

public class Tester {
//	static String s = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
//	static String s = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	public static void main(String[] args) {
//		for(int i = -18; i < 18; i++) {
//			System.out.print(i + ":" +Methods.absCoordToMinorCoord(i) + "\n");
//			
//		}
//		Circle circle = new Circle(0,0,15);
//		circle.generateMask();
//		for(int i = 0; i < 31; i++) {
//			for(int j = 0; j < 31; j++) {
//				System.out.print((circle.mask[i][j])?1:0);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		Line beam = new Line(0,0,30,50);
//		beam.generateMask();
//		for(int j = 0; j < beam.mask[0].length; j++) {
//			for(int i = 0; i < beam.mask.length; i++) {
//				System.out.print((beam.mask[i][j])?1:0);
//			}
//			System.out.println();
//		}
//		test();
//		HashMap<Point, Dummy> map = new HashMap<Point, Dummy>();
//		Point pos1 = new Point(0,0);
//		Point pos2 = new Point(0,0);
//		System.out.println(pos1.equals(pos2));
//		map.put(pos1, new Dummy("a"));
//		map.put(pos2, new Dummy("b"));
//		System.out.println(map.size());
//		System.out.println(map.get(pos1).name);
//		System.out.println(map.get(pos2).name);
//		System.out.println(map.containsKey(pos2));
//		Line_2 l = new Line_2(0,0,50,30);
//		System.out.println(l);
		int a = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);
		int b = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);
		int c = (Math.random() > .5) ? (int)(Math.random() * Integer.MAX_VALUE) : (int)(Math.random() * Integer.MIN_VALUE);

		System.out.println("" + a + "\t:\t" + Tools.transform.msqrt(a) + "\t:\t" + Tools.transform.drshift(a, 1) + "\t:\t" + Tools.transform.bw(a, b));
		System.out.println("" + b + "\t:\t" + Tools.transform.msqrt(b) + "\t:\t" + Tools.transform.drshift(b, 1) + "\t:\t" + Tools.transform.bw(b, c));
		System.out.println("" + c + "\t:\t" + Tools.transform.msqrt(c) + "\t:\t" + Tools.transform.drshift(c, 1) + "\t:\t" + Tools.transform.bw(c, a));
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
