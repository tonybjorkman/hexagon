package view;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import controller.PutChip;
import controller.RemoveChip;
import controller.StealChip;
import model.ChipStock;
import model.Landscape;
import model.Playfield;

public class LandscapeMenu extends JPopupMenu {

	public LandscapeMenu(Playfield p,MouseEvent e,Landscape toLand){

		//I really need the chipstock class

		List<Integer> aChips = p.chipStock.getAvailableChips(toLand.getResource());
		List<Landscape> tChips = p.chipStock.getChippedLands(toLand.getResource());

		JMenuItem[] items=new JMenuItem[aChips.size()];
		JMenuItem[] titems=new JMenuItem[tChips.size()];

		if(toLand.getValue()!=0){
		JMenuItem removeItem = new JMenuItem("Remove");
		add(removeItem);
		removeItem.setAction(new RemoveChip(toLand,p.chipStock));
		}
		
		//Available chips
		for(int i=0;i < aChips.size();i++){
			int landValue = aChips.get(i);
			String label = "Available Chip: "+Integer.toString(landValue);
			items[i] = new JMenuItem();
			add(items[i]);
			items[i].setAction(new PutChip(toLand,p.chipStock,landValue,label));
		}
		
		//Taken chips (occupied)
		for(int i=0;i < tChips.size();i++){
			Landscape fromLand = tChips.get(i);
			
			//dont display chipitem of the land that was clicked
			if(toLand==fromLand)
				continue;
			
			int landValue = fromLand.getValue();
			String label = "Occupied Chip: "+Integer.toString(landValue);
			titems[i] = new JMenuItem();
			add(titems[i]);
			titems[i].setAction(new StealChip(fromLand,toLand,p.chipStock,label));

		}

		show(e.getComponent(),e.getX(),e.getY());
	}




}
