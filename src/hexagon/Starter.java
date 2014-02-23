package hexagon;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Starter extends Canvas {
	private int width=600;
	private int height=480;
	//making image
	private BufferedImage image = new BufferedImage(90,87,BufferedImage.TYPE_INT_RGB);
	//accessing image
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	JFrame frame;
	private static Playfield playfield;

	public Starter(){
		frame = new JFrame();
		Dimension size = new Dimension(600,480);
		setPreferredSize(size); //method inside Canvas
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Starter start = new Starter();
		start.frame.setResizable(false);
		start.frame.setTitle("Megame");
		start.frame.add(start);//adds the Canvas the frame.
		start.frame.pack();
		start.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.frame.setLocationRelativeTo(null);
		start.frame.setVisible(true);
		//start.load();
		
		playfield = new Playfield();
		
		start.runs();


	}

	//loads image from file
	/*private void load() {
		try{

			BufferedImage image = ImageIO.read(new File("hej.png"));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0,w,h,pixels,0,w);
			System.out.println(""+pixels);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}*/

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
			render();
			frames++;

			//calculate fps and ups
			if(System.currentTimeMillis() - timer > 1000){
				timer+=1000;
				frame.setTitle("fps: " + frames + " UPS: " + updates);
				frames=0;
				updates=0;
			}
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3); //tripple buffering
			return;
		}

		//apply the buffer to a graphics object
		Graphics g = bs.getDrawGraphics();
		//All graphics that needs to be displayed is displayed here.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		

		g.setColor(Color.GREEN);

		//sets size of hexagons
		int dia=60;

		//iterate through each landscape stored in playfield and draw it
		for(int y=0;y<playfield.Pfield[0].length;y++){
			for(int x=0;x<playfield.Pfield.length;x++){
				//is x odd, then draw it half a diameter down
				int odd=((x & 1) == 0)?0:(dia-5)/2;
				
				
				int iny=y*(dia-5)+odd;
				int inx=x*(dia-10);
				
				g.fillPolygon(playfield.Pfield[x][y].getPolygon(inx,iny));
				
			}
		}

		//g.drawImage(image, 40, 40, image.getWidth(),image.getHeight(),null);
		g.dispose();//releases system resources ( removes each rendered frame)
		bs.show();//show the buffer that has been calculated
	}

	//draw hexagon on position x,y with radius
	



}
