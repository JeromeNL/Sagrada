package model;

public class Round {
	
	/*
	 * Er zijn 20 entries in de round lookuptable. Deze tabel splitst elke ronde op
	 * in 2 roundIDs: een met de klok mee en een tegen de klok in gedeelte. roundID
	 * 1 heeft roundnr 1 en clockwise 1: roundID 1 is dus ronde 1 met de klok mee.
	 * roundID 12 is dus ronde 6 tegen de klok in.
	 */
	
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
