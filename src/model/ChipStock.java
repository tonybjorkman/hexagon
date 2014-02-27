package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Keeps track of which Chips is available for each landscape.
 */


public class ChipStock {
	
	//get available stock
	
	//Cases:1 - Sätt en chip på en position
	//v.1 getList(resource type) - returnera endast de värden som är lediga. 
	//värden som är lediga kan lagras i en separat lista 
	
	//Cases:2 - Ta bort en chip från en position
	//
	private List<Integer> AsaltChips;
	private List<Integer> AwoodChips;
	private List<Integer> ArockChips;
	private List<Integer> AoatChips;
	private List<Integer> AsheepChips;
	private Map<Resource,List<Integer>> Acollection;

	private List<Integer> TsaltChips;
	private List<Integer> TwoodChips;
	private List<Integer> TrockChips;
	private List<Integer> ToatChips;
	private List<Integer> TsheepChips;
	public Map<Resource,List<Landscape>> Tcollection;
	
	//1. En lista som alltid är densamma likt en databas
	
	//Man söker igenom listan varje gång efter högsta lediga chip.
	
	//2. Två listor, en lista för upptagna kort, en lista för lediga kort. Flyttar element mellan listorna.
	//ska man sno en tag behövs 
	
	public ChipStock(){
		Acollection = new TreeMap<Resource,List<Integer>>();
		AsaltChips = new ArrayList<Integer>(Arrays.asList(10,11));
		AwoodChips = new ArrayList<Integer>(Arrays.asList(9,11));
		ArockChips = new ArrayList<Integer>(Arrays.asList(10,11));
		AoatChips = new ArrayList<Integer>(Arrays.asList(3,5));
		AsheepChips = new ArrayList<Integer>(Arrays.asList(3,4));

		Acollection.put(Resource.SALT, AsaltChips);
		Acollection.put(Resource.WOOD, AwoodChips);
		Acollection.put(Resource.OAT, AoatChips);
		Acollection.put(Resource.ROCK, ArockChips);
		Acollection.put(Resource.SHEEP, AsheepChips);

		Tcollection = new TreeMap<Resource,List<Landscape>>();
		
		
		
		
		/*TsaltChips = new ArrayList<Integer>();
		TwoodChips = new ArrayList<Integer>();
		TrockChips = new ArrayList<Integer>();
		ToatChips = new ArrayList<Integer>();
		TsheepChips = new ArrayList<Integer>();
		
		//Add the dotted chips that are placed in beginning
		TrockChips.add(9);
		TsaltChips.add(9);
		TwoodChips.add(10);
		ToatChips.add(4);
		TsheepChips.add(5);
		*/
	
		Tcollection.put(Resource.SALT, new ArrayList<Landscape>());
		Tcollection.put(Resource.WOOD, new ArrayList<Landscape>());
		Tcollection.put(Resource.OAT, new ArrayList<Landscape>());
		Tcollection.put(Resource.ROCK, new ArrayList<Landscape>());
		Tcollection.put(Resource.SHEEP, new ArrayList<Landscape>());

	}
	
	public List<Landscape> getChippedLands(Resource r) {
		return Tcollection.get(r);
	}

	public void initializeChippedLand(Landscape land) {
		//System.out.print("hellooo");
		Tcollection.get(land.getResource()).add(land);
	}



	
	public int getBestChipValue(Resource r){
		
		int smallest=99;
		//find the smallest value by probability.
		for(Integer i: Acollection.get(r)){
			if(Math.abs(i-7)<Math.abs(smallest-7)){
				smallest=i;
			}
		}
		
		//Error didnt find any value at all!
		if(smallest==99)
			return -1;
		
		return Acollection.get(r).get(0);
	}
	
	
	
	public List<Integer> getAvailableChips(Resource r){
		return Acollection.get(r);
	}
	
	public List<Landscape> getOccupiedChips(Resource r){
		return Tcollection.get(r);
	}
	
	public void removeChipFromLand(Landscape land) throws Exception{
		Resource res = land.getResource();
		int value = land.getValue();
		if(Tcollection.get(res).contains(land) && land.isChip()){
			//Move the item from T to A-collection.
			
			int index = Tcollection.get(res).indexOf(land);
			
			Tcollection.get(res).remove(index);
			Acollection.get(res).add(land.getValue());
			land.setValue(0);
			return; //true=success
		}
		else
			
		throw new Exception();
			 //Couldnt find the chip or land is not for chips!
		
	}
	
	public int getMaxChipValue(Resource r){
		List<Integer> list =Acollection.get(r); //pull out the list stored in the TreeMap
		return list.get(list.size()-1); //get last element of list, its the biggest element
	}

	public void PutChipToLand(Landscape land,int val) throws Exception {
		// TODO Auto-generated method stub
		Resource res = land.getResource();

		//If the land already have a chip, or if the land cant accept chips, or the chip we try to set doesnt exist
		if(!land.isChip())
			throw new Exception();
		
		if(land.getValue()>0)
			removeChipFromLand(land);
		
		int index = Acollection.get(res).indexOf(val);
		
		Acollection.get(res).remove(index);
		Tcollection.get(res).add(land);
		land.setValue(val);
		
	}
	
	public void stealChipFromLand(Landscape fromLand,Landscape toLand) throws Exception {
		// TODO Auto-generated method stub
		Resource res = fromLand.getResource();
		int val = fromLand.getValue();
		//If the land already have a chip, or if the land cant accept chips, or the chip we try to set doesnt exist
		if(!toLand.isChip() || !Tcollection.get(res).contains(fromLand))
			throw new Exception();
		
		removeChipFromLand(fromLand);
		
		PutChipToLand(toLand,val);
		
		
	}
	
	
	

}
