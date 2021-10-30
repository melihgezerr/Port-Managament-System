
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class BasicContainer extends Container{

	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	double consumption() {
		return 2.50*(double)weight;
	}
	

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

