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
	public final int endTime;
	@Override
	/**
	 * To be overriden
	 */
	public void run() {
	}
	public Action(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public static void runAll() {
		for(Action a : queue) {
			if(Clock.time >= a.startTime && (Clock.time <= a.endTime || a.endTime < 0)) {
				a.run();
			} else if(Clock.time > a.endTime && a.endTime > 0) queue.remove(a);
		}
	}
	
}
