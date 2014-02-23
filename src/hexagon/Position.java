package hexagon;

public class Position {
	Landscape Neighbours[] = new Landscape[3];
	
	
	public void setNeighbours(Landscape neighbour1, Landscape neighbour2, Landscape neighbour3){
		//Need to figure out how to find neighbours for a given point
		Neighbours[0] = neighbour1;
		Neighbours[1] = neighbour2;
		Neighbours[2] = neighbour3;
	}
	
	public Landscape[] getNeighbours(){
		return Neighbours;
	}
	
	
		
}
