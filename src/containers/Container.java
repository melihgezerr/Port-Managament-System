
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

import ports.Port;

public abstract class Container {
	int ID;
	int weight;
	/**
	 * @param ID
	 * @param weight
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	double consumption() {
		return 0.0;
	}
	 public boolean equals(Container other) {
		if (this.ID==other.ID && this.weight==other.weight && this.getClass()==other.getClass() )
			return true;
		else
			return false;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	
	public double getConsumptionPerKM() {
		return consumption();
	}
	
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

