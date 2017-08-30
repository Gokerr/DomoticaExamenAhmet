package domotica.command;

import domotica.model.DomoticaObject;
import domotica.model.ElectricityException;

public class setAllDoorsClosed implements Command {
	
	DomoticaObject object;
	
	public setAllDoorsClosed(DomoticaObject object){
		this.object = object;
	}

	@Override
	public void execute() {
		
		try{
			object.setDoorsClosed();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void undo() {
		try{
			object.setDoorsOpened();
		}catch(ElectricityException e){
			e.printStackTrace();
		}
		
	}
	
	

}
