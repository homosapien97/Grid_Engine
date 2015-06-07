package action;

import entity.Entity;
import entity.Player;
import geometry.PointCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import core.Clock;
//import java.util.Vector;
/**
 * Every tick, runAll() is called, which calls the run method of every Action in queue for whom the current time is between their start time and end time (inclusive).
 * If end time is negative, the action is assumed to be indefinite or self-terminating.
 * @author Christian
 *
 */
public abstract class Action implements Runnable{
//	public static List<Action> queue = Collections.synchronizedList(new ArrayList<Action>());
	public static Map<Entity, Action> queue = Collections.synchronizedMap(new HashMap<Entity, Action>());
	public static List<Action> highlightable = Collections.synchronizedList(new ArrayList<Action>());
	public final boolean highlight;
	public final int startTime;
	public int totalTicks;
	public Entity actor;
	@Override
	/**
	 * To be overriden
	 */
	public abstract void run();
	
	protected Action(Entity actor, int startTime, int totalTicks, boolean execute, boolean highlight) {
		this.actor = actor;
		this.startTime = startTime;
		this.totalTicks = totalTicks;
		this.highlight = highlight;
		if(execute) {
			synchronized(queue) {
//				queue.add(this);
				queue.put(actor, this);
			}
		}
		if((actor instanceof Player) && highlight) {
			synchronized(highlightable) {
				highlightable.add(this);
			}
		}
	}
	
	public static void runAll() {
		synchronized(queue) {
			Collection<Action> values = queue.values();
//			for(Action a : queue) {
//				
//				if(Clock.ticks >= a.startTime && (Clock.ticks <= a.startTime + a.totalTicks || a.totalTicks < 0)) {
//					System.out.println("executing " + a.getClass() + " from " + a.startTime + " to " + (a.startTime + a.totalTicks));
//					a.run();
//				}
//			}
			
//			for(Action a : values) {
//				if(Clock.ticks >= a.startTime && (Clock.ticks <= a.startTime + a.totalTicks || a.totalTicks < 0)) {
//					System.out.println("executing " + a.getClass() + " from " + a.startTime + " to " + (a.startTime + a.totalTicks));
//					a.run();
//				}
//			}
//			synchronized(highlightable) {
//				highlightable.removeIf(s -> (!queue.contains(s)));
//			}
			synchronized(highlightable) {
				highlightable.removeIf(s -> (!values.contains(s)));
			}
//			queue.removeIf(s -> (s.done()));
			for(Map.Entry<Entity, Action> entry : queue.entrySet()) {
				if((entry.getValue() != null) && (Clock.ticks >= entry.getValue().startTime && (Clock.ticks <= entry.getValue().startTime + entry.getValue().totalTicks || entry.getValue().totalTicks < 0))) {
					System.out.println("executing " + entry.getValue().getClass() + " from " + entry.getValue().startTime + " to " + (entry.getValue().startTime + entry.getValue().totalTicks));
					entry.getValue().run();
				}
				if((entry.getValue() != null) && entry.getValue().done()) entry.setValue(null);
			}
		}
	}
	
	public boolean done() {
		return Clock.ticks >= startTime + totalTicks;
	}
	public boolean addToQueue() {
		synchronized(queue) {
//			if(queue.contains(this)) {
//				return false;
//			}
			queue.put(actor, this);
			return true;
//			return queue.add(this);
		}
	}
	
	public static List<Action> toHighlight() {
		return highlightable;
	}
	
	public abstract PointCollection pointsToHighlight();
}
