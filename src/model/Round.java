package model;

public class Round {

//	There are 20 entries in the round lookuptable. This table divides every round in 2 roundIDs:
//	one clockwise and the other against the clock. roundID 1 has roundnr 1 en clockwise 1. So roundID 1 is
//	round 1 clockwise. RoundID 2 is round 1 against the clock. Round 12 is round 6 against the clock.

	private int roundID; // 1 - 20
	private int roundnr; // 1 - 10
	private boolean clockwise;

	public Round(int roundID, int roundnr, boolean clockwise) {
		this.roundID = roundID;
		this.roundnr = roundnr;
		this.clockwise = clockwise;
	}

	public int getRoundID() {
		return roundID;
	}

	public int getRoundnr() {
		return roundnr;
	}

	public boolean isClockwise() {
		return clockwise;
	}
}