package hexagon;

import java.awt.Color;
import java.awt.Graphics;

public class Landscape {
	
	//hexagonklassen
	
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
	
	

	
	
}
