package controller;

import java.awt.Color;

import model.ChipStock;
import model.Landscape;
import model.City;
import model.Resource;

public class Computer {

	double saltValue=1;
	double oatValue=1;
	double rockValue=1;
	double sheepValue=1;
	double woodValue=1;
	private ChipStock chipStock;

	public Computer(ChipStock chip){
		chipStock=chip;
	}


	public String getCalculatedPoints(City p){

		double value=0;
		if(p.fields!=null){
			for(Landscape land: p.fields){
				double diceChance;

				//if it doesnt have a chip
				if(land.getValue()>0){

					diceChance=getDicePercent(land.getValue());
					value+=calculateLandValue(land.getResource(),diceChance);
				}
			}
		}
		return ""+Math.round(value);
	}

	private double calculateLandValue(Resource resource,double percent){

		if(resource == Resource.SALT)
			percent*=saltValue;
		else if(resource == Resource.WOOD)
			percent*=woodValue;
		else if(resource == Resource.OAT)
			percent*=oatValue;
		else if(resource == Resource.ROCK)
			percent*=rockValue;
		else if(resource == Resource.SHEEP)
			percent*=sheepValue;
		else
			percent=0;

		return percent;

	}

	public void setResMulti(double rock,double salt,double oat,double sheep,double wood){
		rockValue=rock;
		saltValue=salt;
		oatValue=oat;
		sheepValue=sheep;
		woodValue=wood;
	}

	private double getDicePercent(int d){
		//Values are calculated to represent probability of getting a certain dicenumber when throwing two dices.

		double percent=0.0;
		d=Math.abs(d-7); //no need to have values between 2-12, make it -6 to +6 and then abs -> 0 to 6.

		switch(d){
		case 1: //in case two dices gives a 6 or 8
			percent=13.9;  //thats 13.9% chance
			break;
		case 2: //9 or 9 etc
			percent=11.1;
			break;
		case 3:
			percent=8.3;
			break;
		case 4:
			percent=5.6;
			break;
		case 5:
			percent=2.8;
			break;

		}
		return percent;
	}

}
