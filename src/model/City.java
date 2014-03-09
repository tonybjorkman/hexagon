package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;


/*
 * Position is where a Factory can be build
 * 
 * 
 */
public class City extends JComponent implements MouseListener {
	//Landscape Neighbours[] = new Landscape[3];

	private int posX;
	private int posY;
	public Point point;
	private int gold;
	private String name;
	private boolean start;
	public boolean selected;
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	private Playfield playfield;
	public ArrayList<Landscape> fields; //the neighboring fields
	public static final int RADIUS=15;

	public City(Playfield playfield,Point point, int intGold, boolean start, String name) {
		// TODO Auto-generated constructor stub
		this.playfield=playfield;
		this.point=this.indexToPixel(point);
		gold=intGold;
		this.start=start;
		this.name=name;
		System.out.println("Pos created: "+name+" posx: "+posX+" posY: "+posY+" gold: "+gold);
		this.fields=new ArrayList<Landscape>();
		//fields=getNeighborFields();
	}

	public ArrayList<Landscape> getNeighborFields(){
		
		//if(fields.size()!=0)
			//return (ArrayList<Landscape>)fields;
		fields.clear();
		//ArrayList<Landscape> list = new ArrayList<Landscape>();
		int found=0;
		for(int i=0;i<playfield.lands.length;i++){
			for(int j=0;j<playfield.lands[0].length;j++){

				if(found > 2 )
					break;
					if(this.contains(playfield.lands[i][j].point,Landscape.RADIUS+10)){
						fields.add(playfield.lands[i][j]);
						found++;
					}
			}
		}
		System.out.println("Found "+found+"neighbors");

		return fields;
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

	public static void pixelToIndex(int pixX,int pixY){

		double yIn= ((pixY-5)/35.6);	
		double B=42,A=84;
		double xIn1=0;
		double xIn2=0;

		//pixX-=20;

		int overlap;
		int shift=0;

		if(((int)yIn & 1)==0){  //hexagoner som börjar innåt och med litet steg

			//shift pixelcount right 
			pixX-=20;

			overlap = (int) ((pixX)%(B+A));

			if(overlap>=B){
				//antal serier      +    100px/seriePx-
				xIn1 = 2*(pixX/((int)(B+A)));
				xIn2=((overlap-B)/A)+1;
			}
			else{
				xIn1 = 2*(pixX/((int)(B+A)));
				xIn2 =(overlap/B);
			}
			System.out.println("Xin2:"+xIn2 + "top?:"+((pixX%(B+A))>=B));
			xIn2+=xIn1;
		}
		else{

			overlap = (int) (pixX%(B+A));

			double Bholder=B;
			B=A;
			A=Bholder;

			if(overlap>=B){
				//antal serier      +    100px/seriePx-
				xIn1 = 2*(pixX/((int)(B+A)));
				xIn2=((overlap-B)/A)+1;
			}
			else{
				xIn1 = 2*(pixX/((int)(B+A)));
				xIn2 =(overlap/B);
			}
			System.out.println("Xin2:"+xIn2 + "top?:"+((pixX%(B+A))>=B));
			xIn2+=xIn1;
		}

		//int xIn=(int)((fx-3)/40.0);

		System.out.println("fx="+xIn2+" fy="+yIn);

		//lets filter the indexes and see if they are really close to a int index.




	}


	public Point indexToPixel(Point pt){
		int yIn=pt.y;
		int xIn=pt.x;
		double fx;
		double fy=yIn*35.6+7;	

		if((yIn & 1)==0){ //om Raden är jämn
			fx=xIn*40+18;
			fx+=((xIn)/2)*49;
		}
		else{//om raden är udda
			fx=xIn*45+3;
			fx+=((xIn+1)/2)*37;

		}
		int x=(int)fx;
		int y=(int)fy;
		return new Point(x,y);
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


	public Polygon getPolygon(){

		int dia=30;
		int radie=dia/2;
		double radians = (2*Math.PI/6);
		int xa[]=new int[6];
		int ya[]=new int[6];

		//build polygons in the shape of hexagons
		for(int i=0;i < 6;i++){
			double rad=i*radians+0.5*Math.PI;
			xa[i]=(int)(radie*Math.cos(rad))+point.x;//+radie;
			ya[i]=(int)(radie*Math.sin(rad))+point.y;//+radie;
		}
		//System.out.println("x:"+x+"y:"+y);
		return new Polygon(xa,ya,6);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("You clicked it!!!!!!");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	/*public Landscape[] getNeighbours(){
		return Neighbours;
	}*/



}
