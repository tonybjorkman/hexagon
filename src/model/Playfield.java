package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import controller.Computer;




public class Playfield {
	public Landscape[][] lands = new Landscape[12][8]; //Change later
	public City[][] cities = new City[13][17];
	public static int numOfLS;
	public City selected;
	public ChipStock chipStock;

	public Playfield(ChipStock cs){
		//Initialize the playfield, put resources on correct places etc.
				
		this.chipStock=cs;
		
		for(int i=0;i<cities.length;i++){
			for(int j=0;j<cities[0].length;j++){
			cities[i][j]=null;	
			}
		}
		
	}
	
	//needs to be done after Landscapes are set
	//Sets neighbors to the positions.
	public void initializeCities(){
		for(int i=0;i<cities.length;i++){
			for(int j=0;j<cities[0].length;j++){
				if(cities[i][j]!=null)
			cities[i][j].getNeighborFields();	
			}
		}
	}
	
	
	//Sets all the chip-less lands to a value
	public void initializeLands(){
		for(int i=0;i<lands.length;i++){
			for(int j=0;j<lands[0].length;j++){
				Landscape land=lands[i][j];
				if(land!=null && land.isChip() && land.getValue()>0){
					
					try {
						chipStock.initializeChippedLand(land);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Set current land's value to the best chip of that resource-type.
					//land.setValue(bestChipValue);
				}
			}
		}
		
	}
	
	public void setSelected(City pos){
		selected=pos;
	}
	

	
	public void getObjectAtPosition(int x,int y){
			
	}
	
	public void renderCities(Graphics g,Computer computer){

		Font f2 = new Font("Arial", Font.PLAIN, 12);
	    g.setFont(f2);
		for(int y=0;y<cities[0].length;y++){
			for(int x=0;x<cities.length;x++){
				if(cities[x][y]!=null){
					if(cities[x][y]==selected)
						g.setColor(Color.YELLOW);
					else
						g.setColor(cities[x][y].getColor());

					g.fillPolygon(cities[x][y].getPolygon());
					String value = computer.getCalculatedPoints(cities[x][y]);
					g.setColor(Color.BLACK);
					
					g.drawChars(value.toCharArray(), 0, value.length(), cities[x][y].point.x-8, cities[x][y].point.y+5);
				}
			}
		}
		
	}
	
	public void renderLandscapes(Graphics g){
		Font f = new Font("Arial", Font.BOLD, 16);
	    g.setFont(f);
		//iterate through each landscape stored in playfield and draw it
		for(int y=0;y<lands[0].length;y++){
			for(int x=0;x<lands.length;x++){
				//is x odd, then draw it half a diameter down

				Landscape land = lands[x][y];
				int posX=land.point.x;
				int posY=land.point.y;
				
				
				if(land!=null){
					
					//DRAW HEXAGON
					g.setColor(land.getColor());
					g.fillPolygon(land.getPolygon(x,y));
					
					
					//TEXT FOR VALUES > 0
					String value = Integer.toString(land.getValue());
					g.setColor(Color.BLACK);
					if(!value.equals("0")){
						g.drawChars(value.toCharArray(), 0, value.length(), posX-5, posY+3);		
					}
					
					//CIRCLES FOR LANDSCAPES WITH CHIPS
					if(land.isChip()){
						int radius=50;
					g.drawOval(posX-radius/2, posY-radius/2, radius, radius);
					}
					
				}
			}
		}
	}
	

	
	
	
}
