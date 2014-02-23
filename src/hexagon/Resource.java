package hexagon;

import java.util.ArrayList;
import java.util.Arrays;

public class Resource {
	
	String resource;
	String[] resources = {"rock", "salt","wood", "oats", "sheep"};
			
	
	public Resource(String resource) {
		if (!Arrays.asList(resources).contains(resource.toLowerCase())){
			System.out.println("Crash n burn wrong input");
			System.exit(1);
		}
		this.resource = resource;
	}
		
	public boolean equals(Resource resource1, Resource resource2){
		if (resource1.equals(resource2)){
			return true;
		}
		return false;
	}
		
}
