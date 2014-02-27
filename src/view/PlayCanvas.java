package view;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Computer;
import controller.MapClick;
import controller.XMLFile;
import model.Landscape;
import model.Playfield;
import model.City;
import model.Resource;


/*
 * todo:
 * 
 * Deliverable: Visa uträknad sannolikhet att få kort på klickade position.
 * Visa text direkt på positionen.
 * 
 * Klicka på position: Visa 
 * 
 * Grafik landskap: visa värdet
 * 
 */



public class PlayCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width=800;
	private int height=600;
	//making image
	//accessing image
	public Playfield playfield;
	private Computer computer;


	public PlayCanvas(Playfield playfield,Computer computer){
		this.playfield=playfield;
		this.computer=computer;
		
		Dimension size = new Dimension(width,height);
		setPreferredSize(size); //method inside Canvas

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3); //tripple buffering
			return;
		}

		//apply the buffer to a graphics object
		Graphics g = bs.getDrawGraphics();
		//All graphics that needs to be displayed is displayed here.
		g.setColor(new Color(90,90,190));
		g.fillRect(0, 0, width, height);

		playfield.renderLandscapes(g);
		playfield.renderCities(g, computer);
		
		//g.drawImage(image, 40, 40, image.getWidth(),image.getHeight(),null);
		g.dispose();//releases system resources ( removes each rendered frame)
		bs.show();//show the buffer that has been calculated
	}

	//draw hexagon on position x,y with radius




}
