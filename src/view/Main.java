package view;

import controller.Computer;
import controller.XMLFile;
import model.ChipStock;
import model.Playfield;

public class Main {
	
	public Playfield playfield;
	public PlayCanvas canvas;
	public GUI myGUI;
	public static Main mainThread;
	public XMLFile mapFile;
	public Computer computer;
	private ChipStock chipStock;
	
	public Main(){
		
		mapFile = new XMLFile();
		chipStock = new ChipStock();
		playfield = new Playfield(chipStock);
		computer = new Computer(chipStock);
		
		playfield = mapFile.readXmlFile(playfield);
		playfield.initializeCities();  //lets the cities know which landscapes they have.
		playfield.initializeLands(); //sets the starter chips on place
		
		canvas = new PlayCanvas(playfield,computer);
		myGUI = new GUI(canvas,computer);
		
		
		
		runs();
	}
	
	public static void main(String[] args){
		
		mainThread= new Main();
		

	}
	
	public void runs() {
		long lastTime=System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000/60;
		int frames=0;
		int updates=0;

		while(true){
			//updates for game logic
			if((System.nanoTime() - lastTime) > ns ){
				lastTime=System.nanoTime();
				//update();
				updates++;
			}
			
			//update for game render
			canvas.render();
			frames++;

			//calculate fps and ups
			if(System.currentTimeMillis() - timer > 1000){
				timer+=1000;
				myGUI.setTitle("fps: " + frames + " UPS: " + updates);
				frames=0;
				updates=0;
			}
		}
	}
	
	
}
