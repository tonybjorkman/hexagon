package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPopupMenu;

import view.GUI;
import view.LandscapeMenu;
import view.PlayCanvas;
import model.Landscape;
import model.City;
import model.Resource;

public class MapClick extends MouseAdapter {

	private PlayCanvas pc;
	private GUI myGUI;
	
	public MapClick(PlayCanvas playCanvas,GUI myGUI){
		this.myGUI=myGUI;
		this.pc=playCanvas;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		/*
		 *  Did you just click on a Landscape?
		 */
		
		
		for(int y=0;y<pc.playfield.lands[0].length;y++){
			for(int x=0;x<pc.playfield.lands.length;x++){
				if(pc.playfield.lands[x][y]!=null && pc.playfield.lands[x][y].contains(e.getPoint(),Landscape.RADIUS)){
					
					Landscape land=pc.playfield.lands[x][y];
					
					if(e.getButton()==MouseEvent.BUTTON1 && land.isChip()){
						
						//makes the menu work together with the heavy canvas
						JPopupMenu.setDefaultLightWeightPopupEnabled( false );

						new LandscapeMenu(pc.playfield,e,pc.playfield.lands[x][y]);
						//pc.render();
						//Open a Landscape menu
					}
				}
			}
		}
		
		/*
		 * Did you click on a City, well did you?
		 */
		
		for(int y=0;y<pc.playfield.cities[0].length;y++){
			for(int x=0;x<pc.playfield.cities.length;x++){
				if(pc.playfield.cities[x][y]!=null && pc.playfield.cities[x][y].contains(e.getPoint(),City.RADIUS)){
					//playfield.positions[x][y].selected=true;
					pc.playfield.setSelected(pc.playfield.cities[x][y]);
					ArrayList<Landscape> neighbors = pc.playfield.cities[x][y].getNeighborFields();
					
					
					int resources=0;
					for(Landscape land: neighbors){
						System.out.println("granne");
					}
					
					myGUI.lres.setText("Resources:"+resources);
					//System.out.println("Resources:"+resources);
				}
			}
		}
		
	}
	
}
