package model;

import java.util.ArrayList;

// Supply of dies during the game.

public class DiesInSupply {
	
	private ArrayList<Die> diesInSupply;
	
	public DiesInSupply() {
		diesInSupply = new ArrayList<Die>();
	}
	
	public ArrayList<Die> getDies() {
		return diesInSupply;
	}
	
	public void addDie(Die die) {
		diesInSupply.add(die);
	}
	
	public void removeDie(Die die) {
		diesInSupply.remove(die);
	}
}
