package hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Landscape {
	
	//hexagonklassen
	
	public Landscape(Playfield p) {
		System.out.println("Landscape created"+(++p.numOfLS));
		this.playfield=p;
	}

	private Playfield playfield;
	Resource resource;
	int position;
	Color color;
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setResource(String resource){

		this.resource = new Resource(resource);
		
	}
	
	public Resource getResource(){
		return this.resource;
		
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public Polygon getPolygon(int x,int y){
		int dia=60;
		int radie=dia/2;
		double radians = (2*Math.PI/6);
		int xa[]=new int[6];
		int ya[]=new int[6];
		
		//build polygons in the shape of hexagons
		for(int i=0;i < 6;i++){
			double rad=i*radians;
			xa[i]=(int)(radie*Math.cos(rad))+x+radie;
			ya[i]=(int)(radie*Math.sin(rad))+y+radie;
		}
		//System.out.println("x:"+x+"y:"+y);
		return new Polygon(xa,ya,6);
	}
	

	
	
}
