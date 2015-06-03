package action;

import magic.Spell;
import entity.Entity;
import geometry.PointCollection;

public class SpellAction extends Action{
	Spell spell;
	int x;
	int y;

	public SpellAction(Spell spell, Entity caster, int x, int y, boolean execute) {
		super(caster, Clock.getTicks() + spell.casting(), spell.channel(), execute, true);
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
