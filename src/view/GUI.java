package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Computer;
import controller.MapClick;
import model.Landscape;
import model.City;
import model.Resource;

public class GUI extends JPanel {

	public JLabel lname,lres,lgold;
	public JSpinner oatInput,rockInput,sheepInput,woodInput;
	public JSpinner saltInput,goldInput;
	private PlayCanvas canvas;
	//private Computer computer; //Need to define it here so i can reach it in actionlistener class.
	
	public GUI(final PlayCanvas playCanvas,final Computer computer){
		
		
			canvas=playCanvas;
			
			lname = new JLabel("Name:");
			lres = new JLabel("Total resources:");
			lgold = new JLabel("Gold:");

			System.out.println("error1");
			Icon icoSalt = new ImageIcon(getClass().getResource("/salt.jpg"));
			Icon icoWood = new ImageIcon(getClass().getResource("/wood.jpg"));
			Icon icoRock = new ImageIcon(getClass().getResource("/rock.jpg"));
			Icon icoSheep = new ImageIcon(getClass().getResource("/sheep.jpg"));
			Icon icoOat = new ImageIcon(getClass().getResource("/oat.jpg"));
			Icon icoGold = new ImageIcon(getClass().getResource("/coins.jpg"));

			
			
			JLabel saltLabel = new JLabel(icoSalt);
			System.out.println("error2");

			saltLabel.setToolTipText("Set the multiplier for Salt");
			
			JLabel oatLabel = new JLabel(icoOat);
			
			//String[] monthStrings = getMonthStrings(); //get month names
			SpinnerNumberModel modelSa = new SpinnerNumberModel(1,0,2,0.1);
			SpinnerNumberModel modelSh = new SpinnerNumberModel(1,0,2,0.1);
			SpinnerNumberModel modelWo = new SpinnerNumberModel(1,0,2,0.1);
			SpinnerNumberModel modelOa = new SpinnerNumberModel(1,0,2,0.1);
			SpinnerNumberModel modelRo = new SpinnerNumberModel(1,0,2,0.1);
			SpinnerNumberModel modelGold = new SpinnerNumberModel(1,0,5,1);

			
			ChangeListener change = new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					double rock=(double) rockInput.getValue();
					//double salt=Double.parseDouble(saltInput.getText());
					double salt = (double) saltInput.getValue();
					double oat=(double) oatInput.getValue();
					double sheep=(double) sheepInput.getValue();
					double wood=(double) woodInput.getValue();
					double gold= (double) ((Integer)goldInput.getValue());

					computer.setResMulti(rock,salt,oat,sheep,wood,gold);
					
					//Update the rendering of the map after the computations are done
					canvas.repaint();
					
				}
				
			};
			System.out.println("pre-change listenerS");

			modelSa.addChangeListener(change);
			modelSh.addChangeListener(change);
			modelWo.addChangeListener(change);
			modelOa.addChangeListener(change);
			modelRo.addChangeListener(change);
			modelGold.addChangeListener(change);

			
			Dimension spinnerSize=new Dimension(40,50);
			
			saltInput = new JSpinner(modelSa);			
			saltInput.setPreferredSize(spinnerSize);
			oatInput = new JSpinner(modelOa);
			oatInput.setPreferredSize(spinnerSize);
			JLabel rockLabel = new JLabel(icoRock);
			rockInput = new JSpinner(modelRo);
			rockInput.setPreferredSize(spinnerSize);
			JLabel sheepLabel = new JLabel(icoSheep);
			sheepInput = new JSpinner(modelSh);
			sheepInput.setPreferredSize(spinnerSize);
			JLabel woodLabel = new JLabel(icoWood);
			woodInput = new JSpinner(modelWo);
			woodInput.setPreferredSize(spinnerSize);
			
			JLabel goldLabel = new JLabel(icoGold);
			goldInput = new JSpinner(modelGold);
			goldInput.setPreferredSize(spinnerSize);
			
			System.out.println("spinners created");

			
			saltLabel.setToolTipText("Set the multiplier for\n Salt landscapes");
			oatLabel.setToolTipText("Set the multiplier for\n Oat landscapes");
			rockLabel.setToolTipText("Set the multiplier for Rock");
			sheepLabel.setToolTipText("Set the multiplier for Sheep");
			woodLabel.setToolTipText("Set the multiplier for Wood");
			goldLabel.setToolTipText("Set number of points for each City Gold");
			
			
			
			JPanel rightPanel = new JPanel();
			JPanel mainPanel = new JPanel();
						
			GridBagConstraints gbc = new GridBagConstraints();
			
			rightPanel.setLayout(new GridBagLayout());
			
			gbc.fill=GridBagConstraints.BOTH;
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			
			
			
			//rightPanel.add(lres,gbc);
			
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridheight=1;
			gbc.gridwidth=1;

			System.out.println("pre-salt label");
			rightPanel.add(saltLabel,gbc);
			System.out.println("salt label added");
			
			gbc.gridx=1;
			
			rightPanel.add(saltInput,gbc);

			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			rightPanel.add(oatLabel,gbc);
			gbc.gridx=1;

			rightPanel.add(oatInput,gbc);

			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=3;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			rightPanel.add(rockLabel,gbc);
			gbc.gridx=1;

			rightPanel.add(rockInput,gbc);

			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=4;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			rightPanel.add(sheepLabel,gbc);
			gbc.gridx=1;

			rightPanel.add(sheepInput,gbc);

			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=5;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			rightPanel.add(woodLabel,gbc);
			gbc.gridx=1;

			rightPanel.add(woodInput,gbc);

			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=6;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			rightPanel.add(goldLabel,gbc);
			gbc.gridx=1;

			rightPanel.add(goldInput,gbc);
			
			gbc.fill=GridBagConstraints.HORIZONTAL;
			gbc.gridx=0;
			gbc.gridy=7;
			gbc.gridheight=1;
			gbc.gridwidth=1;
			
			
			mainPanel.add(playCanvas);
			mainPanel.add(rightPanel);
			//label.add(psalt);
			//getContentPane().add(playCanvas);
			System.out.println("pre.. adding");
			add(mainPanel);//adds the Canvas the frame.
			//setLocationRelativeTo(null);
			
			System.out.println("finished adding");
			playCanvas.addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					if(Landscape.getSelected() != null && Landscape.getSelected().contains(e.getPoint(), Landscape.RADIUS))
						return;
					else
						Landscape.setSelected(null);
					
					for(int y=0;y<playCanvas.playfield.lands[0].length;y++){
						for(int x=0;x<playCanvas.playfield.lands.length;x++){
							if(playCanvas.playfield.lands[x][y]!=null && playCanvas.playfield.lands[x][y].contains(e.getPoint(),Landscape.RADIUS)){
								
								Landscape land=playCanvas.playfield.lands[x][y];
								

									Landscape.setSelected(land);
								    System.out.println("new selected:" +land.getValue());
									
								    playCanvas.repaint();
								
							}
						}
					}
				}
				
			});
			
			//make the MapClick respond to mouseclicks on PlayCanvas
			playCanvas.addMouseListener(new MapClick(playCanvas,this));
			
			playCanvas.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e) {
					//Update render when map has been clicked
					canvas.repaint();
				}

			});

		}
		
	}
	

