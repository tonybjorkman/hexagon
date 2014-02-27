package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import model.ChipStock;
import model.Landscape;

public class RemoveChip extends AbstractAction  {

	Landscape landscape;
	ChipStock chipStock;
	
	public RemoveChip(Landscape l,ChipStock cs){
		super("Remove");
		landscape=l;
		chipStock=cs;
		setEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
		chipStock.removeChipFromLand(landscape);
		}catch (Exception er){
			er.printStackTrace();
		}
		
	}
}
