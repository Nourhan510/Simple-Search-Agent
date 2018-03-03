import java.util.ArrayList;


public class node implements Comparable<node>{
	State state;
	node Parentnode;
	String operator;
	int depth;
	int pathCost;
	int h1;
	int evaluation;
	
	public node(State state, node parentnode, String operator, int depth,
			int pathCost) {
		super();
		this.state = state;
		Parentnode = parentnode;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;

	}
	public node(State state,int depth,
			int pathCost) {
		super();
		this.state = state;
		this.depth = depth;
		this.pathCost = pathCost;
	}
	
	public node() {
		super();
		// TODO Auto-generated constructor stub
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public node getParentnode() {
		return Parentnode;
	}
	public void setParentnode(node parentnode) {
		Parentnode = parentnode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	public int getH1() {
		return h1;
	}
	public void setH1(int h1) {
		this.h1 = h1;
	}
	@Override
	public int compareTo(node o) {
		if (this.getEvaluation()!=o.getEvaluation()){
	        return this.getEvaluation()-o.getEvaluation();
	}
		return 0;
	}

	 
}
