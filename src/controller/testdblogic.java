package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javafx.scene.paint.Color;
import model.Die;
import model.PatterncardField;
import view.DieView;

public class testdblogic {
	DatabaseController databaseController;
	
	String dieColor="";
	int dieEyes;
	
	int blueCounter=0;
	int greenCounter=0;
	int yellowCounter=0;
	int redCounter=0;
	int purpleCounter=0;
	
	int bagamount=0;
	int bagcapacity=90;
	
	
public void randomizeColor() {
	
    Random rand = new Random(); //instance of random class
    int upperbound = 5;
      //generate random values from 0-24
    int int_random = rand.nextInt(upperbound);
    
    switch (int_random)
    {
        case 0:
        	if (blueCounter<18) {
        	dieColor="blue";
        	blueCounter++;
        	bagamount++;
        	}else {int_random = rand.nextInt(upperbound);}
            break;
            
        case 1:
        	if (redCounter<18) {
        	dieColor="red";
        	redCounter++;
        	bagamount++;
			}else {int_random = rand.nextInt(upperbound);}
            break;
            
        case 2:
        	if (greenCounter<18) {
        	dieColor="green";
        	greenCounter++;
        	bagamount++;
			}else {int_random = rand.nextInt(upperbound);}
            break;
        	
        case 3:
        	if (yellowCounter<18) {
        	dieColor="yellow";
			yellowCounter++;
			bagamount++;
			}else {int_random = rand.nextInt(upperbound);}
            break;
           
        case 4:
        	if (purpleCounter<18) {
        	dieColor="purple";
			purpleCounter++;
			bagamount++;
			}else {int_random = rand.nextInt(upperbound);}
            break;
        	
        default:
        	break;
            

    }	
}


public void randomizeNumber() {
	
    Random rand = new Random(); 
    int upperbound = 6;
    int int_random = rand.nextInt(upperbound)+1;
    
    dieEyes=int_random;
     
}


public void fillingTheBag() {
	
	int bagcapacity=90;
	
	while(bagamount<bagcapacity) {
	
	
	
	randomizeColor();
	randomizeNumber();
	
	System.out.println(dieEyes+" "+dieColor+" "+ bagamount);
	System.out.println(blueCounter + " " +  yellowCounter + " " + greenCounter + " " + redCounter + " " + purpleCounter);
	
//	addDieToDatabase(1, 1,dieColor,dieEyes,1,1 );
	
	
	}
	
	
}



public void insertDie() {

databaseController = new DatabaseController();
databaseController.doUpdateQuery("SELECT * FROM public_objectivecard where points = 5");

}


private void addDieToDatabase(int idgame, int dienumber, String diecolor,int eyes, int roundtrack,int roundID) {
	String query = "INSERT INTO gamedie VALUES (" + idgame + ", " + dienumber + ", " + diecolor + ", " + eyes + ", "
			+ roundtrack + ", " + roundID + "\");";

	databaseController.doUpdateQuery(query);
}



	
	
	
public void oefenen() {
	
	databaseController = new DatabaseController();
	ResultSet rs = databaseController.doQuery("SELECT * FROM public_objectivecard where points = 5");
	
	try {
		while (rs.next()) {
		
			int xPosition = rs.getInt("idpublic_objectivecard");
			System.out.println(xPosition);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	
}
	
	
	
	
}}
