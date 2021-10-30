
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class LiquidContainer extends HeavyContainer{

	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		// TODO Auto-generated constructor stub
	}

	@Override
	double consumption() {
		// TODO Auto-generated method stub
		return 4.00*(double)weight;
	}
	

}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

