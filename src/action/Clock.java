package action;

public class Clock {
	public static int time = 0;
	public static int tick() {
		return ++time;
	}
	public static int time() {
		return time;
	}
}
