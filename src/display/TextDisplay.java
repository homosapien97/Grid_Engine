package display;

import world.LoadedChunks;

public class TextDisplay extends Display{
	public static void print() {
		Camera.snapshot();
		for(int i = 0; i < Display.HEIGHT; i++) {
			for(int j = 0; j < Display.WIDTH; j++) {
				System.out.print(Camera.snapshot[j][i]);
			}
			System.out.println();
		}
//		for(int i = 0; i< 35; i++) {
//			System.out.print(LoadedChunks.spriteAt(i, 0));
//		}
	}
}