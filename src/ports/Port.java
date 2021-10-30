
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import java.util.ArrayList;

import containers.Container;
import interfaces.IPort;
import ships.Ship;
import java.lang.Math;

public class Port implements IPort{
	/**
	 * @author Melih Gezer
	 * @since 05.06.2021
	 * This class represent a real port. It can check whether a ship is in the port or not.
	 * It has method that calculates the distance between two ports,
	 * and has a checker method that checks a spesific container is in the port or not.
	 */
	private int ID;
	private double X,Y;
	private ArrayList<Container> containers  = new ArrayList<Container>();
	private ArrayList<Ship> history = new ArrayList<Ship>();
	private ArrayList<Ship> current = new ArrayList<Ship>();
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	/**
	 * Adds the ship into "curent" arraylist.
	 * If the ship is already in "current" arraylist, does nothing.
	 * @param s
	 */
	@Override
	public void incomingShip(Ship s) {
		// TODO Auto-generated method stub
		boolean flag = true;
		for (Ship same : current) {
			if (s.getID()==same.getID()) {
				flag = false;
			}
		}
		if (flag) {
			current.add(s);
		}			
	}
	/**
	 * Adds the ship into "history" arraylist. Removes it from "current".
	 * If the ship is already in "history" arraylist, does nothing.
	 * @param s
	 */
	@Override
	public void outgoingShip(Ship s) {
		// TODO Auto-generated method stub
		boolean flag = true;
		for (Ship same : current) {
			if (s.getID()==same.getID()) {
				flag = false;
			}
		}
		if (flag) {
			history.add(s);
		}
		current.remove(s);

	}
	/**
	 * Finds the distance between two ports.
	 * @param other
	 * @return distance
	 */
	public double getDistance(Port other) {
		double xSquare = (this.X- other.X)*(this.X- other.X);
		double ySquare = (this.Y- other.Y)*(this.Y- other.Y);
		return Math.sqrt(xSquare+ySquare);

	}
	/**
	 * @return the containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}
	/**
	 * @return the history
	 */
	public ArrayList<Ship> getHistory() {
		return history;
	}
	/**
	 * @return the current
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return X;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return Y;
	}
	
	public int getID() {
		return ID;
	}
	
	
	public boolean isInsidePort(Container cont) {
		for (Container i : this.containers) {
			if (cont.equals(i)) {
				return true;
			}
		}		
		return false;	
	}
	





}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

