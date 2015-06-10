package action;

import core.Clock;
import spells.Spell;
import entity.Entity;
import geometry.PointCollection;

public class SpellAction extends Action{
	Spell spell;
	int x;
	int y;
	boolean go;

	public SpellAction(Spell spell, Entity caster, int x, int y, boolean execute) {
		super(caster, Clock.ticks + spell.casting(), spell.channel(), execute, true);
//		System.out.println("New spell action" + super.startTime + ":" + super.totalTicks);
		this.spell = spell;
		this.x = x;
		this.y = y;
		if(execute) {
			go = actor.cast(spell);
		}
	}
	
	@Override
	public void run() {
		if(go) spell.cast(actor, x, y);
	}

	@Override
	public PointCollection pointsToHighlight() {
		return spell.preview(actor, x, y);
	}
	
	@Override
	public boolean done() {
		return (Clock.ticks >= startTime + totalTicks) || (!go);
	}
	
	@Override
	public boolean addToQueue() {
		go = actor.cast(spell);
		synchronized(queue) {
			queue.put(actor, this);
			return true;
		}
	}
}
