package model;

import java.util.Random;
import controller.DatabaseController;

public class DieBagModel {
	DatabaseController databaseController;

	String dieColor = "";
	int dieEyes;

	private int blueCounter;
	private int greenCounter;
	private int yellowCounter;
	private int redCounter;
	private int purpleCounter;

	private int bagamount;
	private int bagcapacity;

	public void testdblogc() {
		blueCounter = 0;
		greenCounter = 0;
		yellowCounter = 0;
		redCounter = 0;
		purpleCounter = 0;

		bagamount = 0;
		bagcapacity = 90;
	}

	public void randomizeColor() {

		Random rand = new Random(); // instance of random class
		int upperbound = 5;
		// generate random values from 0-24
		int int_random = rand.nextInt(upperbound);

		switch (int_random) {
		case 0:
			if (blueCounter < 18) {
				dieColor = "blue";
				blueCounter++;
				bagamount++;
			} else {
				int_random = rand.nextInt(upperbound);
			}
			break;

		case 1:
			if (redCounter < 18) {
				dieColor = "red";
				redCounter++;
				bagamount++;
			} else {
				int_random = rand.nextInt(upperbound);
			}
			break;

		case 2:
			if (greenCounter < 18) {
				dieColor = "green";
				greenCounter++;
				bagamount++;
			} else {
				int_random = rand.nextInt(upperbound);
			}
			break;

		case 3:
			if (yellowCounter < 18) {
				dieColor = "yellow";
				yellowCounter++;
				bagamount++;
			} else {
				int_random = rand.nextInt(upperbound);
			}
			break;

		case 4:
			if (purpleCounter < 18) {
				dieColor = "purple";
				purpleCounter++;
				bagamount++;
			} else {
				int_random = rand.nextInt(upperbound);
			}
			break;

		default:
			break;

		}
	}

	public void randomizeNumber() {

		Random rand = new Random();
		int upperbound = 6;
		int int_random = rand.nextInt(upperbound) + 1;

		dieEyes = int_random;

	}

	public void fillingTheBag() {

		int bagcapacity = 90;

		while (bagamount < bagcapacity) {

			randomizeColor();
			randomizeNumber();

			System.out.println(dieEyes + " " + dieColor + " " + bagamount);
			System.out.println(
					blueCounter + " " + yellowCounter + " " + greenCounter + " " + redCounter + " " + purpleCounter);

		}

	}

}
