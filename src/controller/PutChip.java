package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import model.ChipStock;
import model.Landscape;

public class PutChip extends AbstractAction  {

	private Landscape landscape;
	private ChipStock chipStock;
	private int value;
	
	public PutChip(Landscape l,ChipStock cs,int val,String label){
		super(label);
		value=val;
		landscape=l;
		chipStock=cs;
		setEnabled(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
		chipStock.PutChipToLand(landscape,value);
		}catch (Exception er){
			er.printStackTrace();
		}
		
	}
}
