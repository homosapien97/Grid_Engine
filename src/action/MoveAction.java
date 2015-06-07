package action;

import core.Clock;
import entity.Entity;
import entity.interfaces.Mobile;
import entity.interfaces.Pathing;
import entity.interfaces.Sighted;
import geometry.PointCollection;

public class MoveAction <T extends Entity & Mobile & Sighted & Pathing<T> > extends Action{
	public int ticksPerDistance;
	public int xTarget;
	public int yTarget;
//	public T actor;
	public MoveAction (T actor, int xTarget, int yTarget, boolean execute) {
		super(actor, Clock.ticks + actor.ticksPerTileWalked(), 0, execute, true);
		this.ticksPerDistance = actor.ticksPerTileWalked();
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		actor.getPath().constructPathTo(xTarget, yTarget);
		super.totalTicks = (actor.getPath().length() - 1) * ticksPerDistance;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
//		actor.stepTowards(xTarget, yTarget);
		((T)(actor)).getPath().goTo(xTarget, yTarget);
	}
	
	@Override
	public String toString() {
		return "Move action to (" + xTarget + ", " + yTarget + ") " + (Clock.ticks - startTime) + "/" + totalTicks;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PointCollection pointsToHighlight() {
		return ((T)(actor)).getPath().path();
	}

}
