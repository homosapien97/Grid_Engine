package entity;

import spells.BowSpell;
import spells.EarthSpell;
import spells.FireSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.Spell;
import spells.SwordSpell;
import spells.WaterSpell;

public class Inventory {
	public static final Spell[] spells =  {
		BowSpell.get(0),	//0
		BowSpell.get(1),
		BowSpell.get(2),
		BowSpell.get(3),
		BowSpell.get(4),
		BowSpell.get(5),
		BowSpell.get(6),
		EarthSpell.get(0),	//7
		EarthSpell.get(1),
		EarthSpell.get(2),
		EarthSpell.get(3),
		EarthSpell.get(4),
		EarthSpell.get(5),
		EarthSpell.get(6),
		FireSpell.get(0),	//14
		FireSpell.get(1),
		FireSpell.get(2),
		FireSpell.get(3),
		FireSpell.get(4),
		FireSpell.get(5),
		FireSpell.get(6),
		PlasmaSpell.get(0),	//21
		PlasmaSpell.get(1),
		PlasmaSpell.get(2),
		PlasmaSpell.get(3),
		PlasmaSpell.get(4),
		PlasmaSpell.get(5),
		PlasmaSpell.get(6),
		ShieldSpell.get(0),	//28
		ShieldSpell.get(1),
		ShieldSpell.get(2),
		ShieldSpell.get(3),
		ShieldSpell.get(4),
		ShieldSpell.get(5),
		ShieldSpell.get(6),
		SwordSpell.get(0),	//35
		SwordSpell.get(1),
		SwordSpell.get(2),
		SwordSpell.get(3),
		SwordSpell.get(4),
		SwordSpell.get(5),
		SwordSpell.get(6),
		WaterSpell.get(0),	//42
		WaterSpell.get(1),
		WaterSpell.get(2),
		WaterSpell.get(3),
		WaterSpell.get(4),
		WaterSpell.get(5),
		WaterSpell.get(6),
	};
	public final int[] nums = new int[spells.length];
	
	public Inventory() {
		for(int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
	}
	public Inventory(int[] nums) {
		int n = (nums.length < this.nums.length) ? nums.length : this.nums.length;
		for(int i = 0; i < n; i++) {
			this.nums[i] = nums[i];
		}
	}
	public static int indexOf(Spell s) {
		for(int i = 0; i < spells.length; i++) {
			if(s.equals(spells[i])) return i;
		}
		return -1;
	}
	public int add(int index, int num) {
		if(index > -1 && index < nums.length) {
			return (nums[index] += num);
		}
		return nums[index];
	}
	public int add(int index) {
		if(index > -1 && index < nums.length) {
			return (++nums[index]);
		}
		return nums[index];
	}
	public void add(Inventory inv) {
		for(int i = 0; i < nums.length; i++) {
			nums[i] += inv.nums[i];
		}
	}
	public boolean canUse(int index) {
		return (((index > -1) && (index < nums.length)) ? (nums[index] > 0) : false);
	}
	public int use(int index) {
		if(canUse(index)) {
			return (--nums[index]);
		}
		return -1;
	}
	public int set(int index, int num) {
		if(index > -1 && index < nums.length) {
			return (nums[index] = num);
		}
		return -1;
	}
	public boolean set(int[] nums) {
		if (nums.length == this.nums.length) {
			for(int i = 0; i < nums.length; i++) {
				this.nums[i] = nums[i];
			}
		}
		return false;
	}
}
