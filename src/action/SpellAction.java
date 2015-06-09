package action;

import core.Clock;
import spells.Spell;
import entity.Entity;
import geometry.PointCollection;

public class SpellAction extends Action{
	Spell spell;
	int x;
	int y;

	public SpellAction(Spell spell, Entity caster, int x, int y, boolean execute) {
		super(caster, Clock.ticks + spell.casting(), spell.channel(), execute, true);
//		System.out.println("New spell action" + super.startTime + ":" + super.totalTicks);
		this.spell = spell;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {
		spell.cast(actor, x, y);
	}

	@Override
	public PointCollection pointsToHighlight() {
		return spell.preview(actor, x, y);
	}
	
}
