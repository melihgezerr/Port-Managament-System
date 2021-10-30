
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import ports.Port;
import ships.Ship;

import java.util.ArrayList;
import java.util.Scanner;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.RefrigeratedContainer;
import containers.LiquidContainer;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * @author Melih Gezer
		 * @since 05.06.2021
		 */

		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 

		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));


		ArrayList<Port> ports = new ArrayList<Port>();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ArrayList<Container> allContainers = new ArrayList<Container>();
		int portID = 0;
		int shipID = 0;
		int containerID = 0;

		int N = in.nextInt();
		in.nextLine();
		for (int i=0; i<N; i++) {
			String inputLine = in.nextLine();;
			String[] inputs = inputLine.split("\\s+");
			int operation = Integer.parseInt(inputs[0]);;

			if (operation == 1) { //Creating a container
				int idOfPort = Integer.parseInt(inputs[1]);
				int weightOfContainer = Integer.parseInt(inputs[2]);
				if (inputs.length==3) {
					if (weightOfContainer<=3000) {
						//BasicContainer newBasic = new BasicContainer(containerID, weightOfContainer);
						allContainers.add(new BasicContainer(containerID, weightOfContainer));
						ports.get(idOfPort).getContainers().add(allContainers.get(containerID));
						containerID++;

					}
					else {
						//HeavyContainer newHeavy = new HeavyContainer(containerID, weightOfContainer);
						allContainers.add(new HeavyContainer(containerID, weightOfContainer));
						ports.get(idOfPort).getContainers().add(allContainers.get(containerID));
						containerID++;
					}
				}
				if (inputs.length==4) {
					if (inputs[3].compareTo("R") == 0) {
						//RefrigeratedContainer newRefrigerated = new RefrigeratedContainer(containerID, weightOfContainer);
						allContainers.add(new RefrigeratedContainer(containerID, weightOfContainer));
						ports.get(idOfPort).getContainers().add(allContainers.get(containerID));
						containerID++;
					}
					if (inputs[3].compareTo("L") == 0) {
						//LiquidContainer newLiquid = new LiquidContainer(containerID, weightOfContainer);
						allContainers.add(new LiquidContainer(containerID, weightOfContainer));
						ports.get(idOfPort).getContainers().add(allContainers.get(containerID));
						containerID++;
					}	
				}

			}
			if (operation == 2) { //Creating a ship
				int idOfPort = Integer.parseInt(inputs[1]);
				int maxWeight = Integer.parseInt(inputs[2]);
				int maxContainers = Integer.parseInt(inputs[3]);
				int maxHeavy = Integer.parseInt(inputs[4]);
				int maxRefrigerated = Integer.parseInt(inputs[5]);
				int maxLiquid = Integer.parseInt(inputs[6]);
				double fuelConsumptionPerKm = Double.parseDouble(inputs[7]);
				ships.add(new Ship(shipID, ports.get(idOfPort), maxWeight, maxContainers, maxHeavy,
						maxRefrigerated, maxLiquid, fuelConsumptionPerKm));
				ports.get(idOfPort).getCurrent().add(ships.get(shipID));
				shipID++;
			}

			if (operation == 3) { //Creating a port
				double cordinateX = Double.parseDouble(inputs[1]);
				double cordinateY = Double.parseDouble(inputs[2]);
				ports.add(new Port(portID, cordinateX, cordinateY));
				portID++;
			}

			if (operation == 4) { //Loading a container to a ship
				int loadshipID = Integer.parseInt(inputs[1]);
				int loadcontainerID = Integer.parseInt(inputs[2]);
				ships.get(loadshipID).loadingContainer(allContainers.get(loadcontainerID));
			}
			if (operation == 5) { //Unloading a container to a ship
				int loadshipID = Integer.parseInt(inputs[1]);
				int loadcontainerID = Integer.parseInt(inputs[2]);
				ships.get(loadshipID).unloadingContainer(allContainers.get(loadcontainerID));
			}
			if (operation == 6) { //Ship sails to another port
				int sailingshipID = Integer.parseInt(inputs[1]);
				int destinationPortID = Integer.parseInt(inputs[2]);
				ships.get(sailingshipID).sailingToPort(ports.get(destinationPortID));

			}
			if (operation == 7) { //Ship is refueled
				int fuelShipID = Integer.parseInt(inputs[1]);
				double amountOfFuel = Double.parseDouble(inputs[2]);
				ships.get(fuelShipID).reFuel(amountOfFuel);

			}
		}

		for (int port = 0; port<ports.size(); port++) {
			out.print("Port "+ port + ": " + "(" + String.format("%.2f", ports.get(port).getX())+", " 
					+ String.format("%.2f", ports.get(port).getY()) + ")\n" );
			String myBasic = "";
			String myHeavy = "";
			String myLiquid = "";
			String myRefrigerated = "";
			for (int portScontainer = 0; portScontainer < allContainers.size(); portScontainer++) { //ports.get(port).getContainers().size()
				if (ports.get(port).isInsidePort(allContainers.get(portScontainer))) {
					if (allContainers.get(portScontainer) instanceof BasicContainer) {
						myBasic += " "+portScontainer;
					}
					else if (allContainers.get(portScontainer) instanceof RefrigeratedContainer) {
						myRefrigerated += " "+portScontainer;
					}
					else if (allContainers.get(portScontainer) instanceof LiquidContainer) {
						myLiquid += " "+portScontainer;
					}
					else if (allContainers.get(portScontainer) instanceof HeavyContainer) {
						myHeavy += " "+portScontainer;
					}		
				}
			}
			if (myBasic.length()>0) {
				out.print("  BasicContainer:" + myBasic + "\n");
			}
			if (myHeavy.length()>0) {
				out.print("  HeavyContainer:" + myHeavy + "\n");
			}
			if (myRefrigerated.length()>0) {
				out.print("  RefrigeratedContainer:" + myRefrigerated + "\n");
			}
			if (myLiquid.length()>0) {
				out.print("  LiquidContainer:" + myLiquid + "\n");
			}

			for (Ship spesificShip : ships) {
				if (spesificShip.getCurrentPort().getID() == port) {
					String myBasicc = "";
					String myHeavyy = "";
					String myLiquidd = "";
					String myRefrigeratedd = "";
					out.print("  Ship "+spesificShip.getID() +": "+  String.format("%.2f", spesificShip.getFuel())+"\n");
					for (Container spesicifContainer : allContainers) {
						if (spesificShip.isInsideShip(spesicifContainer)) {
							if (spesicifContainer instanceof BasicContainer) {
								myBasicc += " "+spesicifContainer.getID();
							}
							else if (spesicifContainer instanceof RefrigeratedContainer) {
								myRefrigeratedd += " "+spesicifContainer.getID();
							}
							else if (spesicifContainer instanceof LiquidContainer) {
								myLiquidd += " "+spesicifContainer.getID();
							}
							else if (spesicifContainer instanceof HeavyContainer) {
								myHeavyy += " "+spesicifContainer.getID();
							}
						}
					}

					if (myBasicc.length()>0) {
						out.print("    BasicContainer:" + myBasicc + "\n");
					}
					if (myHeavyy.length()>0) {
						out.print("    HeavyContainer:" + myHeavyy + "\n");
					}
					if (myRefrigeratedd.length()>0) {
						out.print("    RefrigeratedContainer:" + myRefrigeratedd + "\n");
					}
					if (myLiquidd.length()>0) {
						out.print("    LiquidContainer:" + myLiquidd + "\n");
					}
				}

			}

		}



		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

