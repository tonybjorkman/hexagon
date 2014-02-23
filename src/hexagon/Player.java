package hexagon;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	List<Position> PositionsConquered = new ArrayList<Position>(); //List is better than array here to have it dynamic.
	List<Resource> CardsOwned = new ArrayList<Resource>();
	
	
	public Player(Position Pos1, Position Pos2, Position Pos3){ //3 Start positions, possibly not include to have it 
																//analyze start positions
		PositionsConquered.add(Pos1);
		PositionsConquered.add(Pos2);
		PositionsConquered.add(Pos3);
		
		Landscape[] neighbours = Pos3.getNeighbours();
		for (int i=0; i<3; i++){
			if (neighbours[i] == null){
				break;
			}
			CardsOwned.add(neighbours[i].getResource());
		}
		
	}
	
	
	public Player(Position Pos1, Position Pos2, Position Pos3, Resource card1, Resource card2, Resource card3){
		//Used if you play by the noob rules which puts the three first settlements on prepositioned positions.
		
		PositionsConquered.add(Pos1);
		PositionsConquered.add(Pos2);
		PositionsConquered.add(Pos3);
		
		CardsOwned.add(card1);
		CardsOwned.add(card2);
		CardsOwned.add(card3);
		
		
	}
	
	
	public void AddLandscape(Position LS){
		PositionsConquered.add(LS);
		
	}
	
	public void AddCards(Resource card){
		CardsOwned.add(card);
	}
	
	
	
}
