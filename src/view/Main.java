package view;

import java.applet.Applet;
import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Computer;
import controller.XMLFile;
import model.ChipStock;
import model.Playfield;

public class Main extends Applet {
	
	public Playfield playfield;
	public PlayCanvas canvas;
	public GUI myGUI;
	public static Main mainThread;
	public XMLFile mapFile;
	public Computer computer;
	private ChipStock chipStock;
	
	public Main(){
		
		/*setResizable(false);
		setTitle("Megame");*/
		
		mapFile = new XMLFile();
		chipStock = new ChipStock();
		playfield = new Playfield(chipStock);
		computer = new Computer(chipStock);
		
		playfield = mapFile.readXmlFile(playfield);
		playfield.initializeCities();  //lets the cities know which landscapes they have.
		playfield.initializeLands(); //sets the starter chips on place
		System.out.println("playfield initialized");

		canvas = new PlayCanvas(playfield,computer);
		System.out.println("playcanvas created");

		add(new GUI(canvas,computer));
		System.out.println("playfield initialized");
		

		//pack();
		System.out.println("after.. adding");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		
		chipStock.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				System.out.println("change event render");

				canvas.repaint();
				System.out.println("change event done");

			}
			
		});
		
		firstRender();
	}
	
	public void init(){
		setSize(new Dimension(1000,680));

		mainThread= new Main();
		
	}
	
	public static void main(String[] args){
		
		//mainThread= new Main();
		

	}
	
	public void firstRender() {
		System.out.println("first render");

		canvas.repaint();
		System.out.println("first render done");

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		canvas.repaint();
		
	}
	
	
}
