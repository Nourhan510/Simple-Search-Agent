import java.awt.Point;
import java.util.*;


//Run this class ;)
public class HelpR2D2 extends AbstractSearch{
	
	static int row = 3;
	static int column= 3;
	static char [][] grid={{'x',' ',' '},{' ','r','p'},{'o',' ','t'}};
	
	static HashSet<ArrayList<Point>> visited = new HashSet<ArrayList<Point>>();
	static Point intialloc= new Point(0,0);
	static Point telloc= new Point(2,2);
	
	static int noOfRock=1;
	static int noOfPP=1;
	static int noOfO=1;
	
	static Point [] RockLoc= {new Point(1,1)};
	static Point [] PPLoc=  {new Point(1,2)};
	static Point [] ObsLoc= {new Point(2,0)};
	
	static boolean heurstic = false;
	
	public HelpR2D2(State state) {
		// TODO Auto-generated constructor stub
		this.intialState= state;
	}
	public static void GenGrid(){
		int max = 6;
		int min = 2;
		row = new Random().nextInt(max-min) +min;
		column= new Random().nextInt(max-min) +min;
		grid = new char[row][column];
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				grid[i][j]=' ';
			}
		}
		telloc= new Point ((new Random().nextInt(row)),(new Random().nextInt(column)));
		intialloc= new Point ((new Random().nextInt(row)),(new Random().nextInt(column)));
		System.out.println("row "+row);
		System.out.println("Column "+column);
		System.out.println("gridrow "+grid.length);
		System.out.println("gridcol "+grid[0].length);
		System.out.println("intialloc "+intialloc.x+" "+intialloc.y);
		System.out.println("telloc "+telloc.x+" "+telloc.y);
		System.out.println();
		grid[intialloc.x][intialloc.y]='x'; 
		
		while(true){
			telloc= new Point ((new Random().nextInt(row)),(new Random().nextInt(column)));
			if(grid[telloc.x][telloc.y]==' '){
					grid[telloc.x][telloc.y]='t';
					break;
				}
			}
		
		while(true){
			noOfRock= new Random().nextInt(min-1)+1;
			noOfPP= noOfRock;
			if(noOfRock>0){
				break;
			}
		}
		
		while(true){
			noOfO= new Random().nextInt(min-1)+1;
			if(noOfO>0){
				break;
			}
		}
		
		RockLoc = new Point [noOfRock];
		PPLoc= new Point [noOfPP];
		ObsLoc=new Point [noOfO];
		
		int indexR =0;
		while(true){
			int locRow =new Random().nextInt(row);
			int locColumn =new Random().nextInt(column);
			if(grid[locRow][locColumn]==' '){
				RockLoc[indexR]= new Point(locRow,locColumn);
				grid[locRow][locColumn]='r';
				indexR++;
				if(indexR>=noOfRock)
					break;
			}
		}
		
		int indexO =0;
		while(true){
			int locRow =new Random().nextInt(row);
			int locColumn =new Random().nextInt(column);
			if(grid[locRow][locColumn]==' '){
				grid[locRow][locColumn]='o';
				ObsLoc[indexO]= new Point(locRow,locColumn);
				indexO++;
				if(indexO>=noOfO)
					break;
			}
		}
	
		int indexP =0;
		while(true){
			int locRow =new Random().nextInt(row);
			int locColumn =new Random().nextInt(column);
			if(grid[locRow][locColumn]==' '){
				grid[locRow][locColumn]='p';
				PPLoc[indexP]= new Point(locRow,locColumn);
				indexP++;
				if(indexP>=noOfPP)
					break;
			}
		}
	}
	public static void gridtoString(){
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void gridtoString(char[][] grid1){
		System.out.println("----------------");
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				System.out.print(grid1[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
	public static void StategridtoString(ArrayList State){
		char [][] grid1 = new char[row][column];
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
			  grid1[i][j]=' ';
			}
		}
		for(int i=0;i<State.size();i++){
			if(i==0)
				grid1[((Point)State.get(0)).x][((Point)State.get(0)).y]='x';
			else grid1[((Point)State.get(i)).x][((Point)State.get(i)).y]='r';
		}
		for(int i=0;i<PPLoc.length;i++){
			grid1[PPLoc[i].x][PPLoc[i].y]='p';
		}
		for(int i=0;i<ObsLoc.length;i++){
			grid1[ObsLoc[i].x][ObsLoc[i].y]='o';
		}
		grid1[telloc.x][telloc.y]='t';
		
		gridtoString(grid1);
	}
	public static String[] Search(char [][] grid, String strategy,boolean visualize){
		String[]result = new String[3];
		ArrayList<Point> state = new ArrayList<Point>();
		state.add(intialloc);
		for(int i =0; i < noOfRock ; i++)
			state.add(RockLoc[i]);
		
		HelpState Newstate= new HelpState(state);
		HelpR2D2 problem = new HelpR2D2(Newstate);
		
		node goal=null;
		int depth=0;
		if(strategy.equals("ID")){
			while(true){
				visited = new HashSet<ArrayList<Point>>();
				System.out.println("Depth"+depth);
				GeneralSearch.limit=depth;
				 goal =GeneralSearch.search(problem,strategy);
				 if(goal!=null) break;
				 depth++;
			}
		}else{
			 goal =GeneralSearch.search(problem,strategy);
		}
			
//		if(goal!=null)
//		 {
//			HelpState s = (HelpState)goal.getState();
//			System.out.println();
//			System.out.println(s.State.get(0));
//		 }
//		 while(goal!=null){
//			HelpState s = (HelpState)goal.getState();
//			StategridtoString(s.State);
//			System.out.println("----------------");
//			goal = goal.getParentnode();
//		 }
		 System.out.println("\n\n\n\n\n\n\nAnswer");
		 LinkedList<node> sequence= new LinkedList<node>();
		 String seq="";
		 while(goal!=null){
			    result[1]= ""+goal.getPathCost();
//				HelpState s = (HelpState)goal.getState();
				sequence.addFirst(goal);
				//StategridtoString(s.State);
				//System.out.println("----------------");
				goal = goal.getParentnode();
			 }
		 for(int i =0;i<sequence.size();i++){
			 HelpState s = (HelpState)(sequence.get(i)).getState();
			if(visualize){
				 StategridtoString(s.State);
			}
			seq+=(sequence.get(i).getOperator()!=null)?sequence.get(i).getOperator():"";
//			System.out.println((sequence.get(i).getOperator()!=null)?sequence.get(i).getOperator():"");
		 }
		 result[0]=seq;
		 result[2]=""+GeneralSearch.expantion;
		 return result;
	}
	public ArrayList<node> operation(node temp) {
		// TODO Auto-generated method stub
		visited.add(((HelpState)temp.getState()).State);
		ArrayList<node> nodes= new ArrayList();
		ArrayList<Point> state = ((HelpState)temp.getState()).State;
		
		
		if(state.get(0).x+1<row){//east
			String next= GetNext(state.get(0).x+1,state.get(0).y,state);
			if(next.equals("space")||next.equals("tele")||next.equals("pp")){
				if(next.equals("pp")){
					boolean flag=false;
					for(int i=1;i<state.size();i++){
						if(state.get(0).x+1== state.get(i).x && state.get(0).y== state.get(i).y){//rock found
							flag=true;
							break;
						}
					}
					if(!flag){
						ArrayList<Point> childEastState = new ArrayList();
						childEastState.add(new Point(state.get(0).x+1,state.get(0).y));
						for(int i =1;i<state.size();i++){
							childEastState.add(i,state.get(i));
						}
						HelpState HelpchildEastState = new HelpState(childEastState);
						node childEast = new node (HelpchildEastState, temp, "E" ,temp.getDepth()+1, temp.getPathCost()+1);
						childEast.setH1((heurstic)?heurstic2(childEast):heurstic1(childEast));
						if(!visited.contains(childEastState)){
							nodes.add(childEast);
						}
					}
				}else{
					ArrayList<Point> childEastState = new ArrayList();
					childEastState.add(new Point(state.get(0).x+1,state.get(0).y));
					for(int i =1;i<state.size();i++){
						childEastState.add(i,state.get(i));
					}
					HelpState HelpchildEastState = new HelpState(childEastState);
					node childEast = new node (HelpchildEastState, temp, "E" ,temp.getDepth()+1, temp.getPathCost()+1);
					childEast.setH1((heurstic)?heurstic2(childEast):heurstic1(childEast));
					if(!visited.contains(childEastState)){
						nodes.add(childEast);
					}
				}
				
			}else{
				if(next.equals("rock")){
					if(state.get(0).x+2<row){
						String nextrock = GetNext(state.get(0).x+2,state.get(0).y,state);
						if(nextrock.equals("space")){
							ArrayList<Point> childEastPushState= new ArrayList();
							childEastPushState.add(new Point(state.get(0).x+1,state.get(0).y));
							for(int i =1;i<state.size();i++){
								if(state.get(i).x==state.get(0).x+1 && state.get(i).y==state.get(0).y){
									childEastPushState.add(i,new Point(state.get(i).x+1,state.get(i).y));
								}else{
									childEastPushState.add(i,state.get(i));
								}
							}
							HelpState HelpchildPushEastState = new HelpState(childEastPushState);
							node childPushEast = new node (HelpchildPushEastState, temp, "EP" ,temp.getDepth()+1, temp.getPathCost()+1);
							childPushEast.setH1((heurstic)?heurstic2(childPushEast):heurstic1(childPushEast));
							if(!visited.contains(childEastPushState)){
								nodes.add(childPushEast);
						//		System.out.println(nodes.get(0));
							}
						}else{
							if(nextrock.equals("pp")){
								ArrayList<Point> childEastPushState= new ArrayList();
								childEastPushState.add(new Point(state.get(0).x+1,state.get(0).y));
								for(int i =1;i<state.size();i++){
									if(state.get(i).x==state.get(0).x+1 && state.get(i).y==state.get(0).y){
										childEastPushState.add(i,new Point(state.get(i).x+1,state.get(i).y));
									}else{
										childEastPushState.add(i,state.get(i));
									}
								}
								HelpState HelpchildPushEastState = new HelpState(childEastPushState);

								node childPushEast = new node (HelpchildPushEastState, temp, "EP" ,temp.getDepth()+1, temp.getPathCost()+1);
								childPushEast.setH1((heurstic)?heurstic2(childPushEast):heurstic1(childPushEast));;
								if(!visited.contains(childEastPushState)){
									nodes.add(childPushEast);
					//	        	System.out.println(nodes.get(0));
								}
							}
						}
					}
				}
			}
		}
		
		
		
		if(state.get(0).x-1>=0){ //west
			String next= GetNext(state.get(0).x-1,state.get(0).y,state);
			if(next.equals("space")||next.equals("tele")||next.equals("pp")){
				if(next.equals("pp")){
					boolean flag=false;
					for(int i=1;i<state.size();i++){
						if(state.get(0).x-1== state.get(i).x && state.get(0).y== state.get(i).y){//rock found
							flag=true;
							break;
						}
					}
					if(!flag){
						ArrayList<Point> childWestState= new ArrayList();
						childWestState.add(new Point(state.get(0).x-1,state.get(0).y));
						for(int i =1;i<state.size();i++){
							childWestState.add(i,state.get(i));
						}
						HelpState HelpchildWestState = new HelpState(childWestState);

						node childWest = new node (HelpchildWestState, temp, "W" ,temp.getDepth()+1, temp.getPathCost()+1);
						childWest.setH1((heurstic)?heurstic2(childWest):heurstic1(childWest));

						if(!visited.contains(childWestState)){
							nodes.add(childWest);
			//				System.out.println(nodes.get(0));
						}
					}
				}else{
					ArrayList<Point> childWestState= new ArrayList();
					childWestState.add(new Point(state.get(0).x-1,state.get(0).y));
					for(int i =1;i<state.size();i++){
						childWestState.add(i,state.get(i));
					}
					HelpState HelpchildWestState = new HelpState(childWestState);

					node childWest = new node (HelpchildWestState, temp, "W" ,temp.getDepth()+1, temp.getPathCost()+1);
					childWest.setH1((heurstic)?heurstic2(childWest):heurstic1(childWest));

					if(!visited.contains(childWestState)){
						nodes.add(childWest);
		//				System.out.println(nodes.get(0));
					}
				}
				
			}else{
				if(next.equals("rock")){
					if(state.get(0).x-2>=0){
						String nextrock = GetNext(state.get(0).x-2,state.get(0).y,state);
						if(nextrock.equals("space")){
							ArrayList<Point> childWestPushState= new ArrayList();
							childWestPushState.add(new Point(state.get(0).x-1,state.get(0).y));
							for(int i =1;i<state.size();i++){
								if(state.get(i).x==state.get(0).x-1 && state.get(i).y==state.get(0).y){
									childWestPushState.add(i,new Point(state.get(i).x-1,state.get(i).y));
								}else{
									childWestPushState.add(i,state.get(i));
								}
							}
							HelpState HelpchildWestPushState = new HelpState(childWestPushState);

							node childPushWest = new node (HelpchildWestPushState, temp, "WP" ,temp.getDepth()+1, temp.getPathCost()+1);
							childPushWest.setH1((heurstic)?heurstic2(childPushWest):heurstic1(childPushWest));
							if(!visited.contains(childWestPushState)){
								nodes.add(childPushWest);
		//						System.out.println(nodes.get(0));
							}
							
						}else{
							if(nextrock.equals("pp")){
								ArrayList<Point> childWestPushState= new ArrayList();
								childWestPushState.add(new Point(state.get(0).x-1,state.get(0).y));
								for(int i =1;i<state.size();i++){
									if(state.get(i).x==state.get(0).x-1 && state.get(i).y==state.get(0).y){
										childWestPushState.add(i,new Point(state.get(i).x-1,state.get(i).y));
									}else{
										childWestPushState.add(i,state.get(i));
									}
								}
								HelpState HelpchildWestPushState = new HelpState(childWestPushState);

								node childPushWest = new node (HelpchildWestPushState, temp, "WP" ,temp.getDepth()+1, temp.getPathCost()+1);
								childPushWest.setH1((heurstic)?heurstic2(childPushWest):heurstic1(childPushWest));

								if(!visited.contains(childWestPushState)){
									nodes.add(childPushWest);
			//						System.out.println(nodes.get(0));
								} 
							
								
							}
						}
					}
				}
			}
		}
		
		
		
		
		if(state.get(0).y+1<column){//south
			String next = GetNext(state.get(0).x,state.get(0).y+1,state);
			if(next.equals("space") || next.equals("tele")||next.equals("pp")){
				if(next.equals("pp")){
					boolean flag=false;
					for(int i=1;i<state.size();i++){
						if(state.get(0).x== state.get(i).x && state.get(0).y+1== state.get(i).y){//rock found
							flag=true;
							break;
						}
					}
					if(!flag){
						ArrayList<Point> childSouthState= new ArrayList();
						childSouthState.add(new Point(state.get(0).x,state.get(0).y+1));
						for(int i =1;i<state.size();i++){
							childSouthState.add(i,state.get(i));
						}
						HelpState HelpchildSouthState= new HelpState(childSouthState);

						node childSouth = new node (HelpchildSouthState, temp, "S" ,temp.getDepth()+1, temp.getPathCost()+1);
						childSouth.setH1((heurstic)?heurstic2(childSouth):heurstic1(childSouth));
						if(!visited.contains(childSouthState)){
							nodes.add(childSouth);
//							System.out.println(nodes.get(0));
						}
					}
				}else{
					ArrayList<Point> childSouthState= new ArrayList();
					childSouthState.add(new Point(state.get(0).x,state.get(0).y+1));
					for(int i =1;i<state.size();i++){
						childSouthState.add(i,state.get(i));
					}
					HelpState HelpchildSouthState= new HelpState(childSouthState);

					node childSouth = new node (HelpchildSouthState, temp, "S" ,temp.getDepth()+1, temp.getPathCost()+1);
					childSouth.setH1((heurstic)?heurstic2(childSouth):heurstic1(childSouth));
					if(!visited.contains(childSouthState)){
						nodes.add(childSouth);
//						System.out.println(nodes.get(0));
					}
				}
				
			}else{
				if(next.equals("rock")){
					if(state.get(0).y+2<column){
						String nextrock = GetNext(state.get(0).x,state.get(0).y+2,state);
						if(nextrock.equals("space")){
							ArrayList<Point> childSouthPushState= new ArrayList();
							childSouthPushState.add(new Point(state.get(0).x,state.get(0).y+1));
							for(int i =1;i<state.size();i++){
								if(state.get(i).x==state.get(0).x && state.get(i).y==state.get(0).y+1){
									childSouthPushState.add(i,new Point(state.get(i).x,state.get(i).y+1));
								}else{
									childSouthPushState.add(i,state.get(i));
								}
							}
							HelpState HelpchildSouthPushState= new HelpState(childSouthPushState);
							node childPushSouth = new node (HelpchildSouthPushState, temp, "SP" ,temp.getDepth()+1, temp.getPathCost()+1);
							childPushSouth.setH1((heurstic)?heurstic2(childPushSouth):heurstic1(childPushSouth));

							if(!visited.contains(childSouthPushState)){
								nodes.add(childPushSouth);
			//					System.out.println(nodes.get(0));
							}
						}else{
							if(nextrock.equals("pp")){
								ArrayList<Point> childSouthPushState= new ArrayList();
								childSouthPushState.add(new Point(state.get(0).x,state.get(0).y+1));
								for(int i =1;i<state.size();i++){
									if(state.get(i).x==state.get(0).x && state.get(i).y==state.get(0).y+1){
										childSouthPushState.add(i,new Point(state.get(i).x,state.get(i).y+1));
									}else{
										childSouthPushState.add(i,state.get(i));
									}
								}
								HelpState HelpchildSouthPushState= new HelpState(childSouthPushState);

								node childPushSouth = new node (HelpchildSouthPushState, temp, "SP" ,temp.getDepth()+1, temp.getPathCost()+1);
								childPushSouth.setH1((heurstic)?heurstic2(childPushSouth):heurstic1(childPushSouth));
								if(!visited.contains(childSouthPushState)){
									nodes.add(childPushSouth);
		//							System.out.println(nodes.get(0));
								}	
							}
						}
					}
				}
			}
		}
		
		
		if(state.get(0).y-1>=0){//north
			String next = GetNext(state.get(0).x,state.get(0).y-1,state);
			if(next.equals("space")||next.equals("tele")||next.equals("pp")){
				if(next.equals("pp")){
					boolean flag=false;
					for(int i=1;i<state.size();i++){
						if(state.get(0).x== state.get(i).x && state.get(0).y-1== state.get(i).y){//rock found
							flag=true;
							break;
						}
					}
					if(!flag){
						ArrayList<Point> childNorthState= new ArrayList();
						childNorthState.add(new Point(state.get(0).x,state.get(0).y-1));
						for(int i =1;i<state.size();i++){
							childNorthState.add(i,state.get(i));
						}
						HelpState HelpchildNorthState= new HelpState(childNorthState);

						node childNorth = new node (HelpchildNorthState, temp, "N" ,temp.getDepth()+1, temp.getPathCost()+1);
						childNorth.setH1((heurstic)?heurstic2(childNorth):heurstic1(childNorth));

						if(!visited.contains(childNorthState)){
							nodes.add(childNorth);
				//			System.out.println(nodes.get(0));
						}
					}
				}else{
					ArrayList<Point> childNorthState= new ArrayList();
					childNorthState.add(new Point(state.get(0).x,state.get(0).y-1));
					for(int i =1;i<state.size();i++){
						childNorthState.add(i,state.get(i));
					}
					HelpState HelpchildNorthState= new HelpState(childNorthState);

					node childNorth = new node (HelpchildNorthState, temp, "N" ,temp.getDepth()+1, temp.getPathCost()+1);
					childNorth.setH1((heurstic)?heurstic2(childNorth):heurstic1(childNorth));

					if(!visited.contains(childNorthState)){
						nodes.add(childNorth);
			//			System.out.println(nodes.get(0));
					}
				}
				
			}else{
				if(next.equals("rock")){
					if(state.get(0).y-2>=0){
						String nextrock = GetNext(state.get(0).x,state.get(0).y-2,state);
						if(nextrock.equals("space")){
							ArrayList<Point> childNorthPushState= new ArrayList();
							childNorthPushState.add(new Point(state.get(0).x,state.get(0).y-1));
							for(int i =1;i<state.size();i++){
								if(state.get(i).x==state.get(0).x && state.get(i).y==state.get(0).y-1){
									childNorthPushState.add(i,new Point(state.get(i).x,state.get(i).y-1));
								}else{
									childNorthPushState.add(i,state.get(i));
								}
							}
							HelpState HelpchildNorthPushState= new HelpState(childNorthPushState);
							node childPushNorth = new node (HelpchildNorthPushState, temp, "NP" ,temp.getDepth()+1, temp.getPathCost()+1);
							childPushNorth.setH1((heurstic)?heurstic2(childPushNorth):heurstic1(childPushNorth));

							if(!visited.contains(childNorthPushState)){
								nodes.add(childPushNorth);
		//						System.out.println(nodes.get(0));
							}
						}else{
							if(nextrock.equals("pp")){
								ArrayList<Point> childNorthPushState= new ArrayList();
								childNorthPushState.add(new Point(state.get(0).x,state.get(0).y-1));
								for(int i =1;i<state.size();i++){
									if(state.get(i).x==state.get(0).x && state.get(i).y==state.get(0).y-1){
										childNorthPushState.add(i,new Point(state.get(i).x,state.get(i).y-1));
									}else{
										childNorthPushState.add(i,state.get(i));
									}
								}
								HelpState HelpchildNorthPushState= new HelpState(childNorthPushState);
								node childPushNorth = new node (HelpchildNorthPushState, temp, "NP" ,temp.getDepth()+1, temp.getPathCost()+1);
								childPushNorth.setH1((heurstic)?heurstic2(childPushNorth):heurstic1(childPushNorth));
								if(!visited.contains(childNorthPushState)){
									nodes.add(childPushNorth);
			//						System.out.println(nodes.get(0));
								}
							}
							
						}
					}
				}
			}
		}
		
//		System.out.print(visited.size() + "\n");
		
		if(nodes.size()>0){
			return nodes;
		}
		return null;
	}
	private String GetNext(int i, int j, ArrayList<Point> state) {
		// TODO Auto-generated method stub
		for(int k = 1;k<state.size();k++){
			if(state.get(k).x==i && state.get(k).y== j){
				return "rock";
			}
		}
		if(grid[i][j]==' '){
			return "space";
		}else{
			if(grid[i][j]=='p'){
				return "pp";
			}else{
				if(grid[i][j]=='t'){
					return "tele";
				}
			}
		}
		return "occup";
	}
    public boolean Goal(node node){
		boolean flag=false; 
		for(int i=0;i<PPLoc.length;i++){
			flag =false;
			for(int j=0 ; j < ((HelpState)node.getState()).State.size() ; j++){
				if(j>0){
					Point point = (Point)((HelpState)node.getState()).State.get(j);
					if(PPLoc[i].x==point.x && PPLoc[i].y==point.y){
						flag =true;
					}
				}
			}
			if(flag == false)
				return false;
		}
		//System.out.print("flag  "+flag);
		if(((Point)((HelpState)node.getState()).State.get(0)).x==telloc.x && ((Point)((HelpState)node.getState()).State.get(0)).y==telloc.y)
			return true;
		
		return false;
	}
   	@Override
   	public int PathCost(node node) {
   		// TODO Auto-generated method stub
   		return node.getDepth()+1;
   	}   
   	public static int heurstic1(node node){
		return (Math.abs(((HelpState)node.getState()).State.get(0).x-telloc.x)+Math.abs(((HelpState)node.getState()).State.get(0).y-telloc.y));
   	}
	public static int heurstic2(node node){
   		int heu=0;
		ArrayList <Point> PPcopy= new ArrayList<Point>();
		for(int i=0;i<PPLoc.length;i++){
			PPcopy.add(PPLoc[i]);
		}
		
		int index=0;
		int  min=1000;
		for(int i=1;i<((HelpState)node.getState()).State.size();i++){
			//System.out.println("Statesss "+((HelpState)node.getState()).State.size());
			for(int j=0;j<PPcopy.size();j++){
				int currentDistance=(Math.abs(((HelpState)node.getState()).State.get(i).x-PPcopy.get(j).x)+Math.abs(((HelpState)node.getState()).State.get(i).y-PPcopy.get(j).y));
//				System.out.println("current distance "+currentDistance);
//				System.out.println("current rock "+((HelpState)node.getState()).State.get(i).x+" "+((HelpState)node.getState()).State.get(i).y);
//				System.out.println("current pp "+PPcopy.get(j).x+" "+PPcopy.get(j).y);
				if(currentDistance<=min){
					min=currentDistance;
					index=j;
					//System.out.println("MINIMUM "+min+" "+index);
				}
			}
			if(PPcopy.size()>1)
			PPcopy.remove(index);
		}
		heu+=min;
	//	System.out.println("Heuuuu"+heu);
		return heu;

   	}
   	public static void main(String[]args){
   		//GenGrid();
   	 //heurstic = true;
	 String[]result=Search(grid , "A*", false);
	 System.out.println("Row  "+ row +" Column "+  column);
	 System.out.println("Exapanded nodes "+ result[2]);
	
 }
}
