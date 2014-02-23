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
	
	public Landscape(Playfield p,Resource res,int value) {
		System.out.println("Landscape num: "+(++p.numOfLS) + " value: "+value+" Resource: "+res.name());
		this.playfield=p;
		this.value=value;
		this.resource=res;
	}

	//keep track of the playfield so its fields can be reached.
	private Playfield playfield; 
	Resource resource;
	int position;
	Color color;
	int value;
	
	public void setColor(Color color) {
		this.color = color;
	}

	//Resource is Enum. like Resource.SALT, Resource.OAT etc.
	public void setResource(Resource resource){
		this.resource = resource;
	}
	
	public Resource getResource(){
		return this.resource;
	}
	
	public Color getColor(){
		if(resource == Resource.SALT)
			return Color.WHITE;
		else if(resource == Resource.WOOD)
			return new Color(8,105,8);
		else if(resource == Resource.OAT)
			return Color.YELLOW;
		else if(resource == Resource.ROCK)
			return Color.DARK_GRAY;
		else if(resource == Resource.SHEEP)
			return Color.GREEN;
		else
			return Color.BLUE;
	}
	
	public Polygon getPolygon(int x,int y){
		int dia=80;
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
