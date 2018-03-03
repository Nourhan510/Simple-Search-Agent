import java.util.ArrayList;


public abstract class AbstractSearch {
	State intialState;
	String [] operators;
	public abstract ArrayList<node> operation(node temp);
	public abstract boolean Goal(node node);
	public abstract int PathCost(node node);
}
