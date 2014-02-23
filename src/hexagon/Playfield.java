package hexagon;



public class Playfield {
	Landscape[][] Pfield = new Landscape[12][8]; //Change later
	public static int numOfLS;

	public Playfield(){
		//Initialize the playfield, put resources on correct places etc.
		
		for(int i=0;i<Pfield.length;i++){
			for(int j=0;j<Pfield[0].length;j++){
			Pfield[i][j]=new Landscape(this);	
			}
		}
			
		
	}
	
	
	
}
