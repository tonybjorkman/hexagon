package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Landscape {

	//hexagonklassen
	
	//keep track of the playfield so its fields can be reached.
	private Playfield playfield; 
	private Resource resource;
	int position;
	public Point point;
	public Color color;
	private int value;
	public boolean chip;
	public static final int RADIUS=40; //diameter of hexagons
	public static Landscape selected;

	public Landscape(Playfield p) {
		System.out.println("Landscape created"+(++p.numOfLS));
		this.playfield=p;
	}

	public Landscape(Playfield p,Resource res,int value,Point point,boolean chip) {
		System.out.println("Landscape num: "+(++p.numOfLS) + " value: "+value+" Resource: "+res.name()+ " isChip" +chip);
		this.chip=chip;
		this.point=indexToPixel(point);
		this.playfield=p;
		this.setValue(value);
		this.resource=res;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isChip(){
		return chip;
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

	public Point indexToPixel(Point pt){
		
		int odd=((pt.x & 1) == 0)?0:(RADIUS*2-8)/2;

		//Select where it should paint in the polygon in x and y
		int iny=pt.y*(RADIUS*2-8)+odd+RADIUS;
		int inx=pt.x*(RADIUS*2-16)+RADIUS;

		return new Point(inx,iny);
	}

	public Polygon getPolygon(int x,int y){
		//int radie=DIAMETER/2;

		//Point point = indexToPixel(new Point(x,y));
		double radians = (2*Math.PI/6);

		int xa[]=new int[6];
		int ya[]=new int[6];

		//build polygons in the shape of hexagons
		for(int i=0;i < 6;i++){
			double rad=i*radians;
			xa[i]=(int)(RADIUS*Math.cos(rad))+point.x;
			ya[i]=(int)(RADIUS*Math.sin(rad))+point.y;
		}

		return new Polygon(xa,ya,6);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean contains(Point pt,int radius){

		//radius = search radius
		//check if the point is close enough to this Position
		if(Math.abs(pt.distance(this.point))<radius){
			//getNeighborFields();
			return true;
		}
		else
			return false;
	}

	public static void setSelected(Landscape land) {
		// TODO Auto-generated method stub
		selected=land;
	}

	public static Landscape getSelected() {
		return selected;
		// TODO Auto-generated method stub
		
	}


}
