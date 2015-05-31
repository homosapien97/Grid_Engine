package action;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.List;
//import java.util.Vector;
/**
 * Every tick, runAll() is called, which calls the run method of every Action in queue for whom the current time is between their start time and end time (inclusive).
 * If end time is negative, the action is assumed to be indefinite or self-terminating.
 * @author Christian
 *
 */
public class Action implements Runnable{
	public static List<Action> queue = Collections.synchronizedList(new ArrayList<Action>());
	public final int startTime;
	public int totalTicks;
	@Override
	/**
	 * To be overriden
	 */
	public void run() {
	}
	
	public Action(int startTime, int totalTicks) {
		this.startTime = startTime;
		this.totalTicks = totalTicks;
		synchronized(queue) {
			queue.add(this);
		}
	}
	
	public static void runAll() {
//		System.out.println("Executing a queue of " + queue.size() + " actions");
//		if(queue.size() > 1) {
//			synchronized(queue) {
//				for(Action a : queue) {
//					System.out.println(a);
//				}
//			}
//		}
		synchronized(queue) {
			for(Action a : queue) {
				if(Clock.ticks >= a.startTime && (Clock.ticks <= a.startTime + a.totalTicks || a.totalTicks < 0)) {
					a.run();
				}
//				if(a.done() && a.totalTicks > 0) queue.remove(a);
			}
//			for(Action a: queue) {
//				if(a.done()) queue.remove(a);
//			}
//			Iterator<Action> i = queue.iterator();
//			while(i.hasNext()) {
//				Action a = i.next();
//				if(a.done()) i.remove();
//			}
			queue.removeIf(s -> (s.done()));
		}
	}
	
	public boolean done() {
		return Clock.ticks >= startTime + totalTicks;
	}
}
