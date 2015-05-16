package test;

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
	}
}
