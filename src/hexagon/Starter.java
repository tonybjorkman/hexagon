package hexagon;



import io.XMLFile;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
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

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Starter extends Canvas {
	private int width=800;
	private int height=600;
	//making image
	private BufferedImage image = new BufferedImage(90,87,BufferedImage.TYPE_INT_RGB);
	//accessing image
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	JFrame frame;
	private static Playfield playfield;

	public Starter(){
		frame = new JFrame();
		Dimension size = new Dimension(width,height);
		setPreferredSize(size); //method inside Canvas
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Starter start = new Starter();
		start.frame.setResizable(false);
		start.frame.setTitle("Megame");
		
		JLabel psalt = new JLabel("Points for salt");
		JTextField tsalt = new JTextField();
		
		JLabel poat = new JLabel("Points for oat");
		JTextField toat = new JTextField();
		
		JLabel prock = new JLabel("Points for rock");
		JTextField trock = new JTextField();
		
		JButton calc = new JButton("Calculate");
		
		JPanel rightPanel = new JPanel();
		JPanel label = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS));
		rightPanel.add(psalt);
		rightPanel.add(tsalt);
		rightPanel.add(poat);
		rightPanel.add(toat);
		rightPanel.add(prock);
		rightPanel.add(trock);
		rightPanel.add(calc);
		label.add(start);
		label.add(rightPanel);
		start.frame.add(label);//adds the Canvas the frame.
		start.frame.pack();
		start.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.frame.setLocationRelativeTo(null);
		start.frame.setVisible(true);
		//start.load();
		
		start.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				for(int y=0;y<playfield.positions[0].length;y++){
					for(int x=0;x<playfield.positions.length;x++){
						if(playfield.positions[x][y]!=null && playfield.positions[x][y].contains(e.getPoint())){
							//playfield.positions[x][y].selected=true;
							playfield.setSelected(playfield.positions[x][y]);
							System.out.println("hej");
						}
					}
				}
				
				//Position.pixelToIndex(e.getX(), e.getY());
				System.out.print("x:"+e.getX()+" y:"+e.getY());
			}

			
		});
		
		
		
		
		playfield = new Playfield();
		playfield=new XMLFile().readXmlFile(playfield);
		
		start.runs();


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
		g.setColor(new Color(90,90,190));
		g.fillRect(0, 0, width, height);
		

		g.setColor(Color.GREEN);

		//sets size of hexagons
		int dia=80;

		//iterate through each landscape stored in playfield and draw it
		for(int y=0;y<playfield.Pfield[0].length;y++){
			for(int x=0;x<playfield.Pfield.length;x++){
				//is x odd, then draw it half a diameter down
				int odd=((x & 1) == 0)?0:(dia-8)/2;
				
				//Select where it should paint in the polygon in x and y
				int iny=y*(dia-8)+odd;
				int inx=x*(dia-16);
				if(playfield.Pfield[x][y]!=null){
				g.setColor(playfield.Pfield[x][y].getColor());
				g.fillPolygon(playfield.Pfield[x][y].getPolygon(inx,iny));
				}
				
			}
		}
		
		
		for(int y=0;y<playfield.positions[0].length;y++){
			for(int x=0;x<playfield.positions.length;x++){
				if(playfield.positions[x][y]!=null){
					if(playfield.positions[x][y]==playfield.selected)
						g.setColor(Color.YELLOW);
					else
						g.setColor(playfield.positions[x][y].getColor());
					
					g.fillPolygon(playfield.positions[x][y].getPolygon());
						
					}
			}
		}

		//g.drawImage(image, 40, 40, image.getWidth(),image.getHeight(),null);
		g.dispose();//releases system resources ( removes each rendered frame)
		bs.show();//show the buffer that has been calculated
	}

	//draw hexagon on position x,y with radius
	



}
