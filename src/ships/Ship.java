
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import interfaces.IShip;
import ports.Port;

public class Ship implements IShip {
	/**
	 * @author Melih Gezer
	 * @since 05.06.2021
	 * This class represents for a real ship. It can load, unload a container and do all the necessary actions with them.
	 * It has method that calculates the remaining fuel and ýt can sail to another port with necessary actions. 
	 */

	private int ID;
	private double fuel = 0.0;
	private Port currentPort;


	private int totalWeightCapacity;
	private int maxNumberOfAllContainers;
	private int maxNumberOfHeavyContainers;
	private int maxNumberOfRefrigeratedContainers;
	private int maxNumberOfLiquidContainers;
	private double fuelConsumptionPerKM;


	private int totalWeightSum = 0;
	private int containerNum =0;
	private int heavyContNum =0;
	private int refrigeratedContNum =0;
	private int liquidContNum =0;

	ArrayList<Container> currentContainers = new ArrayList<Container>();

	/**
	 * @param ID
	 * @param currentPort
	 * @param totalWeightCapacity
	 * @param maxNumberOfAllContainers
	 * @param maxNumberOfHeavyContainers
	 * @param maxNumberOfRefrigeratedContainers
	 * @param maxNumberOfLiquidContainers
	 * @param fuelConsumptionPerKM
	 */
	public Ship(int ID, Port currentPort, int totalWeightCapacity, int maxNumberOfAllContainers,
			int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers,
			double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = currentPort;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
	}

	public ArrayList<Container> getCurrentContainers() {
		return currentContainers;

	}


	@Override
	public boolean sailTo(Port p) {

		return false;
	}
	/**
	 * Adds fuel to the ship.
	 */
	@Override
	public void reFuel(double newFuel) {
		// TODO Auto-generated method stub
		this.fuel += newFuel;

	}
	@Override
	/**
	 * Checks the capability of the ship to load a container.
	 * @return True if it can, false otherwise.
	 */
	public boolean load(Container cont) {
		if (cont instanceof BasicContainer) {
			if (this.maxNumberOfAllContainers >= this.containerNum+1 &&
					this.totalWeightCapacity >= this.totalWeightSum+cont.getWeight()) {
				return true;
			}
		}
		else if (cont instanceof RefrigeratedContainer) {
			if (this.maxNumberOfAllContainers >= this.containerNum+1 &&
					this.totalWeightCapacity >= this.totalWeightSum+cont.getWeight() &&
					this.maxNumberOfRefrigeratedContainers >= this.refrigeratedContNum+1 && 
					this.maxNumberOfHeavyContainers >= this.heavyContNum+1 ) {
				return true;
			}
		}
		else if (cont instanceof LiquidContainer) {
			if (this.maxNumberOfAllContainers >= this.containerNum+1 &&
					this.totalWeightCapacity >= this.totalWeightSum+cont.getWeight() &&
					this.maxNumberOfLiquidContainers >= this.liquidContNum+1 && 
					this.maxNumberOfHeavyContainers >= this.heavyContNum+1 ) {
				return true;
			}
		}
		else if (cont instanceof HeavyContainer) {
			if (this.maxNumberOfAllContainers >= this.containerNum+1 &&
					this.totalWeightCapacity >= this.totalWeightSum+cont.getWeight() &&
					this.maxNumberOfHeavyContainers >= this.heavyContNum+1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean unLoad(Container cont) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * Loads the container to the ship with checking the limits. If limits are exceed, does nothing.
	 * @param cont
	 * @return void
	 */

	public void loadingContainer(Container cont) {
		if (this.currentPort.isInsidePort(cont)) {
			if (cont instanceof BasicContainer) {
				if (load(cont)) {
					totalWeightSum += cont.getWeight();
					containerNum += 1;
					this.currentContainers.add(cont);
					this.currentPort.getContainers().remove(this.currentPort.getContainers().indexOf(cont));
				}
			}
			else if (cont instanceof RefrigeratedContainer) {
				if (load(cont)) {
					totalWeightSum += cont.getWeight();
					containerNum += 1;
					refrigeratedContNum += 1;
					heavyContNum += 1;
					this.currentContainers.add(cont);
					this.currentPort.getContainers().remove(this.currentPort.getContainers().indexOf(cont));
				}
			}
			else if (cont instanceof LiquidContainer) {
				if (load(cont)) {
					totalWeightSum += cont.getWeight();
					containerNum += 1;
					liquidContNum += 1;
					heavyContNum += 1;
					this.currentContainers.add(cont);
					this.currentPort.getContainers().remove(this.currentPort.getContainers().indexOf(cont));
				}
			}
			else if (cont instanceof HeavyContainer) {
				if (load(cont)) {
					totalWeightSum += cont.getWeight();
					containerNum += 1;
					heavyContNum += 1;
					this.currentContainers.add(cont);
					this.currentPort.getContainers().remove(this.currentPort.getContainers().indexOf(cont));	
				}
			}
		}

	}
	/**
	 * Unloads the container to the ship with checking the limits. If limits are exceed, does nothing.
	 * @param cont
	 * @return void
	 */
	public void unloadingContainer(Container cont) {
		if (this.isInsideShip(cont)) {
			this.currentPort.getContainers().add(cont);
			this.currentContainers.remove(this.currentContainers.indexOf(cont));
			if (cont instanceof BasicContainer) {
				totalWeightSum -= cont.getWeight();
				containerNum -= 1;
			}
			if (cont instanceof HeavyContainer) {
				totalWeightSum -= cont.getWeight();
				containerNum -= 1;
				heavyContNum -= 1;
			}
			if (cont instanceof LiquidContainer) {
				totalWeightSum -= cont.getWeight();
				containerNum -= 1;
				heavyContNum -= 1;
				liquidContNum -= 1;
			}
			if (cont instanceof RefrigeratedContainer) {
				totalWeightSum -= cont.getWeight();
				containerNum -= 1;
				heavyContNum -= 1;
				refrigeratedContNum -= 1;
			}	
		}
	}
	/**
	 * Calculates overall consumption per KM of whole ship. (includes containers' consumptions.)
	 * @return shipsCurrentConsumtion
	 */
	public double calculateConsumptionPerKM() {
		double shipsCurrentConsumtion = fuelConsumptionPerKM;
		for (Container cont : currentContainers) {
			shipsCurrentConsumtion += cont.getConsumptionPerKM();
		}
		return shipsCurrentConsumtion;
	}
	/**
	 * Does the necessary actions when the ship sails to another port. (Checks the fuel if exceed,does nothing.)
	 * @param port
	 * @return void
	 */
	public void sailingToPort (Port port) {
		if (currentPort.getDistance(port)> 0.001) {
			if (fuel >= calculateConsumptionPerKM()*currentPort.getDistance(port)) {
				fuel -= calculateConsumptionPerKM()*currentPort.getDistance(port);
				currentPort.outgoingShip(this);
				currentPort = port;
				currentPort.incomingShip(this);
			}
		}
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	public Port getCurrentPort() {
		return currentPort;
	}

	/**
	 * @return the fuel
	 */
	public double getFuel() {
		return fuel;
	}
	/**
	 * Checks whether the container is inside the ship or not.
	 * @param cont
	 * @return boolean
	 */
	public boolean isInsideShip(Container cont) {
		for (Container i : this.currentContainers) {
			if (cont.equals(i)) {
				return true;
			}
		}		
		return false;	
	}




}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

