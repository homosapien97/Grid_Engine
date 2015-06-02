package action;

import magic.Spell;
import entity.Entity;
import geometry.PointCollection;

public class SpellAction extends Action{
	Spell spell;
	Entity caster;
	int x;
	int y;

	public SpellAction(Spell spell, Entity caster, int x, int y) {
		super(Clock.getTicks() + spell.casting(), spell.channel());
		this.spell = spell;
		this.caster = caster;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {
		spell.cast(caster, x, y);
	}

	@Override
	public PointCollection toHighlight() {
		return spell.preview(caster, x, y);
	}
	
}
