
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class HeavyContainer extends Container{
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	double consumption() {
		// TODO Auto-generated method stub
		return 3.00*(double)weight;
	}
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

