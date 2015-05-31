package action;

public class Clock {
	public static int ticks = 0;
	public static int tick() {
		return ++ticks;
	}
	public static int getTicks() {
		return ticks;
	}
}
