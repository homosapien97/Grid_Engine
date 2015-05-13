package run;

import display.Display;
import world.Chunk;
import world.LoadedChunks;

public class Main {
	Chunk blank = new Chunk();
	LoadedChunks chunks = new LoadedChunks(blank);
	Display display;
	
	public void step() {
		stepState();
		stepGFX();
	}
	
	private void stepGFX() {
		
	}
	private void stepState() {
		
	}
}
