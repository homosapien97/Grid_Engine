package action;

import java.util.Vector;
/**
 * Every tick, runAll() is called, which calls the run method of every Action in queue for whom the current time is between their start time and end time (inclusive).
 * If end time is negative, the action is assumed to be indefinite or self-terminating.
 * @author Christian
 *
 */
public class Action implements Runnable{
	public static Vector<Action> queue;
	public final int startTime;
	public final int totalTicks;
	@Override
	/**
	 * To be overriden
	 */
	public void run() {
	}
	
	public Action(int startTime, int totalTicks) {
		this.startTime = startTime;
		this.totalTicks = totalTicks;
	}
	
	public static void runAll() {
		for(Action a : queue) {
			if(Clock.ticks >= a.startTime && (Clock.ticks <= a.startTime + a.totalTicks || a.totalTicks < 0)) {
				a.run();
			} else if(Clock.ticks > a.startTime + a.totalTicks && a.totalTicks > 0) queue.remove(a);
		}
	}
	
}
