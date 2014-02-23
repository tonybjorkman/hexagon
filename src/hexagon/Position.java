package hexagon;

import java.awt.Color;
import java.awt.Polygon;


/*
 * Position is where a Factory can be build
 */
public class Position {
	//Landscape Neighbours[] = new Landscape[3];
	
	
	private int posX;
	private int posY;
	private int gold;
	private String name;
	private boolean start;
	
	public Position(int intX, int intY, int intGold, boolean start, String name) {
		// TODO Auto-generated constructor stub
		posX=intX;
		posY=intY;
		gold=intGold;
		this.start=start;
		this.name=name;
		System.out.println("Pos created: "+name+" posx: "+posX+" posY: "+posY+" gold: "+gold);
	}

	public void setNeighbours(Landscape neighbour1, Landscape neighbour2, Landscape neighbour3){
		//Need to figure out how to find neighbours for a given point
		
	}
	
	public Color getColor(){
		if(!start)
		return new Color(150,54,0);
		else
		return new Color(180,5,190);
	}
	
	public Polygon getPolygon(){
		//set the startposition (0,0) m-value adjusts start. k-value adjust multitude 
		double fx=posX*40+3;
		double fy=posY*35.6-8;	
		
		if((posY & 1)==0){
		fx+=((posX)/2)*49;
		}
		else{
			fx=posX*45-12;
			
			fx+=((posX+1)/2)*37;

		}
		int x=(int)fx;
		int y=(int)fy;
		
		int dia=30;
		int radie=dia/2;
		double radians = (2*Math.PI/6);
		int xa[]=new int[6];
		int ya[]=new int[6];
		
		//build polygons in the shape of hexagons
		for(int i=0;i < 6;i++){
			double rad=i*radians+0.5*Math.PI;
			xa[i]=(int)(radie*Math.cos(rad))+x+radie;
			ya[i]=(int)(radie*Math.sin(rad))+y+radie;
		}
		//System.out.println("x:"+x+"y:"+y);
		return new Polygon(xa,ya,6);
	}
	
	
	/*public Landscape[] getNeighbours(){
		return Neighbours;
	}*/
	
	
		
}
