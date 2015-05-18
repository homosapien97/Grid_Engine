package action;

import entity.Entity;
import entity.Mobile;
import general.Tools;

public class MoveAction <T extends Entity & Mobile> extends Action{
	public int ticksPerDistance;
	public int xTarget;
	public int yTarget;
	public T actor;
	public MoveAction (T actor, int xTarget, int yTarget, int ticksPerDistance) {
		super(Clock.time, Tools.nav.orthoDistance(actor.getAbsoluteX(), actor.getAbsoluteY(), xTarget, yTarget) * ticksPerDistance);
		this.ticksPerDistance = ticksPerDistance;
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		this.actor = actor;
	}
	@Override
	public void run() {
		actor.stepTowards(xTarget, yTarget);
	}

}
