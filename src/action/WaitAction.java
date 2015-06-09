package action;

import core.Clock;
import entity.Entity;
import geometry.PointCollection;

public class WaitAction extends Action{
	public WaitAction(Entity caster, int ticks) {
		super(caster, Clock.ticks, ticks, true, false);
	}
	
	@Override
	public void run() {
	}

	@Override
	public PointCollection pointsToHighlight() {
		return null;
	}
}
