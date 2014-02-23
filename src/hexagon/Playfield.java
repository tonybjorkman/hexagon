package hexagon;



public class Playfield {
	public Landscape[][] Pfield = new Landscape[12][8]; //Change later
	public Position[][] positions = new Position[13][17];
	public static int numOfLS;

	public Playfield(){
		//Initialize the playfield, put resources on correct places etc.
		
		for(int i=0;i<positions.length;i++){
			for(int j=0;j<positions[0].length;j++){
			positions[i][j]=null;	
			}
		}
			
		
	}
	
	
	
}
