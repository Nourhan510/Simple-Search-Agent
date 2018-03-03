import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class GeneralSearch {
	
	static int limit =  Integer.MAX_VALUE;
	static int expantion =0;
public static node search(AbstractSearch p, String strategy){

	ArrayList<node> Nodes= new ArrayList<node>();
	node root = new node(p.intialState,0,0);
	Nodes.add(root);
	
//	if(strategy.equals("ID")){
//		for(int depth =0; depth<10;depth++){
//			System.out.println("Restarting with depth= "+depth);
//			ArrayList<node> Nodes= new ArrayList<node>();
//			node root = new node(p.intialState,0,0);
//			HelpR2D2.visited = new HashSet<ArrayList<Point>>();
//			Nodes.add(root);
//			System.out.println("Nodes size "+Nodes.size());
//			while(!Nodes.isEmpty()){
//				node temp = Nodes.remove(0);
//				System.out.println("Parent "+((HelpState)temp.getState()).State.get(0).x+" "+((HelpState)temp.getState()).State.get(0).y+" Depth  "+temp.depth);
//				//System.out.println(" Rock "+((HelpState)temp.getState()).State.get(1).x+" "+((HelpState)temp.getState()).State.get(1).y);
//				HelpR2D2.StategridtoString((((HelpState)temp.getState()).State));
//				
//				if(p.Goal(temp)) {
//					return temp;
//				}else{
//					ArrayList<node>expantion = p.operation(temp); 
//					if(expantion!=null){
//					System.out.println("Nodes expanded "+expantion.size());
//					for(int i=0;i<expantion.size();i++){
//						System.out.println("Child "+((HelpState)expantion.get(i).getState()).State.get(0).x+" "+((HelpState)expantion.get(i).getState()).State.get(0).y);
//						//System.out.println("  Rock "+((HelpState)expantion.get(i).getState()).State.get(1).x+" "+((HelpState)expantion.get(i).getState()).State.get(1).y);
//						HelpR2D2.StategridtoString((((HelpState)expantion.get(i).getState()).State));
//					}
//					System.out.println("------------------------");
//					DlS(Nodes,expantion,depth);
//					}else System.out.println("expanded nodes = zero ");
//					
//				}
//			}
//		}
//		System.out.println("Out of for loop");
//		return null;
//	}else{
		while(true){	
			
			if(Nodes.isEmpty()){
				System.out.println("No Solution");
				return null;
			}
			else{
				node temp = Nodes.remove(0);
				expantion++;
				System.out.print("Parent "+((HelpState)temp.getState()).State.get(0).x+" "+((HelpState)temp.getState()).State.get(0).y);
				System.out.print(" Rock "+((HelpState)temp.getState()).State.get(1).x+" "+((HelpState)temp.getState()).State.get(1).y);
				System.out.print("  heurstic "+temp.getH1());
				System.out.println("  A* "+(temp.getH1()+temp.getPathCost()));
				
				HelpR2D2.StategridtoString((((HelpState)temp.getState()).State));
			
				if(p.Goal(temp)) return temp;
				else{
					if(temp.getDepth()>limit){
						return null;
					}
						
					ArrayList<node>expantion = p.operation(temp); 
					if(expantion!=null){
					for(int i=0;i<expantion.size();i++){
						System.out.print("Child "+((HelpState)expantion.get(i).getState()).State.get(0).x+" "+((HelpState)expantion.get(i).getState()).State.get(0).y);
						System.out.print("  Rock "+((HelpState)expantion.get(i).getState()).State.get(1).x+" "+((HelpState)expantion.get(i).getState()).State.get(1).y);
						System.out.print("  heurstic "+expantion.get(i).getH1());
						System.out.println("  A* "+(expantion.get(i).getH1()+expantion.get(i).getPathCost()));
						HelpR2D2.StategridtoString((((HelpState)expantion.get(i).getState()).State));
					}
					System.out.println("------------------------");
					
						switch(strategy){
						case "BF":BF(Nodes,expantion);break;
						case "DF":DF(Nodes,expantion);break;
						case "UC":UC(Nodes,expantion);break;
						case "GR":GR(Nodes,expantion);break;
						case "A*":Astar(Nodes,expantion);break;
						case "ID":DlS(Nodes,expantion);break;
						}
					}
				}
			}
		}
//	}
}

public static void GR(ArrayList<node> nodes, ArrayList<node> expantion){
	for(int i=0;i<expantion.size();i++){
		nodes.add(expantion.get(i));
	}
	for(int i=0;i<nodes.size();i++){
		nodes.get(i).setEvaluation(nodes.get(i).getH1());
	}
	Collections.sort(nodes);
}
public static void Astar(ArrayList<node> nodes, ArrayList<node> expantion){
	for(int i=0;i<expantion.size();i++){
		nodes.add(expantion.get(i));
	}
	for(int i=0;i<nodes.size();i++){
		nodes.get(i).setEvaluation(nodes.get(i).getH1()+nodes.get(i).getPathCost());
	}
	Collections.sort(nodes);
}
public static void DF(ArrayList<node> nodes, ArrayList<node> expantion) {
	// TODO Auto-generated method stub
	for(int i=0;i<expantion.size();i++){
		nodes.add(i, expantion.get(i));
	}
}
public static void DlS(ArrayList<node> nodes, ArrayList<node> expantion ) {
	// TODO Auto-generated method stub
	for(int i=0;i<expantion.size();i++){
		if(expantion.get(i).depth<=limit){
			nodes.add(i, expantion.get(i));
		}
	}
}
public static void BF(ArrayList<node> nodes, ArrayList<node> expantion) {
	// TODO Auto-generated method stub
	
	for(int i=0;i<expantion.size();i++){
		nodes.add(expantion.get(i));
	}
		
		
}
public static void UC(ArrayList<node> nodes, ArrayList<node> expantion) {
	// TODO Auto-generated method stub
	for(int i=0;i<expantion.size();i++){
		nodes.add(expantion.get(i));
	}
	for(int i=0;i<nodes.size();i++){
		nodes.get(i).setEvaluation(nodes.get(i).getPathCost());
	}
	Collections.sort(nodes);
}

}
