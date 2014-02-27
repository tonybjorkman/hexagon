package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Computer;
import controller.MapClick;
import model.Landscape;
import model.City;
import model.Resource;

public class GUI extends JFrame {

	public JLabel lname,lres,lgold;
	public JTextField tsalt,toat,trock,tsheep,twood;
	//private Computer computer; //Need to define it here so i can reach it in actionlistener class.
	
	public GUI(PlayCanvas playCanvas,final Computer computer){
		
			setResizable(false);
			setTitle("Megame");
			
			lname = new JLabel("Name:");
			lres = new JLabel("Total resources:");
			lgold = new JLabel("Gold:");

			JLabel psalt = new JLabel("Points for salt");
			tsalt = new JTextField("1");
			
			JLabel poat = new JLabel("Points for oat");
			toat = new JTextField("1");
			
			JLabel prock = new JLabel("Points for rock");
			trock = new JTextField("1");
			
			JLabel psheep = new JLabel("Points for sheep");
			tsheep = new JTextField("1");
			
			JLabel pwood = new JLabel("Points for wood");
			twood = new JTextField("1");
			
			
			
			JButton calc = new JButton("Calculate");
			
			calc.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent e){
				double rock=Double.parseDouble(trock.getText());
				double salt=Double.parseDouble(tsalt.getText());
				double oat=Double.parseDouble(toat.getText());
				double sheep=Double.parseDouble(tsheep.getText());
				double wood=Double.parseDouble(twood.getText());

				
				computer.setResMulti(rock,salt,oat,sheep,wood);
				}
			});
			
			JPanel rightPanel = new JPanel();
			JPanel label = new JPanel();
			rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS));
			rightPanel.add(lres);
			rightPanel.add(psalt);
			rightPanel.add(tsalt);
			rightPanel.add(poat);
			rightPanel.add(toat);
			rightPanel.add(prock);
			rightPanel.add(trock);
			rightPanel.add(psheep);
			rightPanel.add(tsheep);
			rightPanel.add(pwood);
			rightPanel.add(twood);
			rightPanel.add(calc);
			label.add(playCanvas);
			label.add(rightPanel);
			add(label);//adds the Canvas the frame.
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setVisible(true);
			
			//make the MapClick respond to mouseclicks on PlayCanvas
			playCanvas.addMouseListener(new MapClick(playCanvas,this));
			

		}
		
	}
	

