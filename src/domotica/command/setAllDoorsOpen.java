package domotica.command;

import domotica.model.DomoticaObject;

public class setAllDoorsOpen implements Command {

	DomoticaObject object;
	
	public setAllDoorsOpen(DomoticaObject object) {
		this.object = object;
	}
	
	@Override
	public void execute() {
		try{
			object.setDoorsOpened();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void undo() {
		try{
			object.setDoorsClosed();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
