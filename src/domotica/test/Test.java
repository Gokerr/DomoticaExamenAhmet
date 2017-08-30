package domotica.test;

import domotica.command.*;

import java.util.*;

import domotica.model.*;
import domotica.view.*;

public class Test {
	
	private static Command command;
	private static Random random = new Random();

	private static int randomInt(int lo, int hi) {
		return lo + random.nextInt(hi - lo + 1);
	}

	private static float randomFloat(float lo, float hi) {
		return lo + random.nextFloat() * (hi - lo);
	}

	private static void round(House house) throws DomoticaException {

		Living living = house.getLiving();
		living.setAircoTemperature(randomInt(15, 25));
		living.setLightLevel(randomFloat(0, 1));
		living.setTvSwitchedOn(!living.isTvSwitchedOn());
		living.setTvChannel(randomInt(0, 99));
		living.setTvVolume(randomInt(0, 10));
		living.update();

		Kitchen kitchen = house.getKitchen();
		kitchen.setAircoTemperature(randomInt(15, 25));
		kitchen.setLightLevel(randomFloat(0, 1));
		kitchen.setCooktopSwitchedOn(!kitchen.isCooktopSwitchedOn());
		kitchen.setExtractorHoodSwitchedOn(!kitchen.isExtractorHoodSwitchedOn());
		kitchen.setRefrigeratorTemperature(randomInt(1, 15));
		kitchen.setFreezerTemperature(randomInt(-15, -1));
		kitchen.update();

		Bathroom bathroom = house.getBathroom();
		bathroom.setAircoTemperature(randomInt(15, 25));
		bathroom.setLightLevel(randomFloat(0, 1));
		bathroom.setShowerTemperature(randomInt(10, 60));
		bathroom.setShowerFlowLevel(randomFloat(0, 1));
		bathroom.setTapTemperature(randomInt(10, 60));
		bathroom.setTapFlowLevel(randomFloat(0, 1));
		bathroom.update();

		Bedroom bedroom = house.getBedroom();
		bedroom.setAircoTemperature(randomInt(15, 25));
		bedroom.setLightLevel(randomFloat(0, 1));
		bedroom.setAlarmSwitchedOn(!bedroom.isAlarmSwitchedOn());
		bedroom.setWaterbedTemperature(randomInt(10, 30));
		bedroom.update();
	}
	
	public static void openDoors(DomoticaObject object) {
		setAllDoorsOpen opendoor = new setAllDoorsOpen(object);
		opendoor.execute();
		command = opendoor;
		object.update();
	}
	
	public static void closeDoors(DomoticaObject object) {
		setAllDoorsClosed closedoor = new setAllDoorsClosed(object);
		closedoor.execute();
		command = closedoor;
		object.update();
	}
	
	public static void undoCommand(DomoticaObject object) {
		command.undo();
		object.update();
	}

	public static void main(String[] args) throws DomoticaException {
		House house = new House("house");
		new HouseView(house);
		
		Bedroom bedroom = house.getBedroom();
		openDoors(bedroom);
		closeDoors(bedroom);
		undoCommand(bedroom);
		
		//for (int i = 0; i < randomInt(50, 75); i++) {
			//round(house);
		//}
	}
}
