package hexagon;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;


/*
 * Position is where a Factory can be build
 */
public class Position extends JComponent implements MouseListener {
	//Landscape Neighbours[] = new Landscape[3];


	private int posX;
	private int posY;
	private int gold;
	private String name;
	private boolean start;
	public boolean selected;

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

	public static void pixelToIndex(int pixX,int pixY){
		

		//xIn=100
		
		//jämn rad: out=(xIn*40+3) >> (out-3)/40 = xIn
		
		//förenklat: out=(xIn/2)*49 >> (out/49)*2=xIn
		
		//i rätt ordning
		//xIn=(out-3)/40 - (out/49)*2
		
		//trim pixY
		//pixY-=15;
		
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

	public Point indexToPixel(){
		return indexToPixel(posX,posY);
	}
	
	public Point indexToPixel(int xIn,int yIn){
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
	
	public boolean contains(Point point){
		
		Point place = indexToPixel();
		
		//check if the point is close enough to this Position
		if(Math.abs(point.distance(place))<15){
			return true;
			
		}
		else
		return false;
		
	}
	
	

	public Polygon getPolygon(){
		//set the startposition (0,0) m-value adjusts start. k-value adjust multitude 
		/*double fx=posX*40+3;
		double fy=posY*35.6-8;	

		if((posY & 1)==0){
			fx+=((posX)/2)*49;
		}
		else{
			fx=posX*45-12;

			fx+=((posX+1)/2)*37;

		}
		int x=(int)fx;
		int y=(int)fy;*/
		Point point = indexToPixel();

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
