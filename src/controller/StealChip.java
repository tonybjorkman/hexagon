package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import model.ChipStock;
import model.Landscape;

public class StealChip extends AbstractAction  {

	private Landscape fromLand,toLand;
	private ChipStock chipStock;
	
	public StealChip(Landscape fromLand,Landscape toLand,ChipStock cs,String label){
		super(label);
		this.fromLand=fromLand;
		this.toLand=toLand;
		chipStock=cs;
		setEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
		chipStock.stealChipFromLand(fromLand,toLand);
		}catch (Exception er){
			er.printStackTrace();
		}
		
	}
}
