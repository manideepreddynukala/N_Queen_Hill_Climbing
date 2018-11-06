public class RandomRestart {
	private HillClimbing hillClimber;
	private int nodesGenerated;
	private Node start;
    private int randomRestartSteps;
    private int moves;
    private int successSteps;
	int successStepsSteepest=0,hillClibingSteepest_Successes=0,failureSteepest=0;
	/**
	 * Constructor
	 */
	public RandomRestart(Queen[] startBoard){
		hillClimber = new HillClimbing(startBoard);
		nodesGenerated = 0;
	}

    public int getRandomRestartSteps() {
        return randomRestartSteps;
    }

	public int getSuccessSteps() {
		return successSteps;
	}

	//random restart algorithm for sideways moves
	public Node randomRestart(){
		Node currentNode = hillClimber.getStartNode();
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();

		while(heuristic!=0){
			Node nextNode = hillClimber.hillClimbing_SideWays("NoPrint");
			successSteps += hillClimber.getSuccessSteps();
			heuristic = nextNode.getHeuristic();

			if(heuristic != 0){ //restart
			    randomRestartSteps++;
				successSteps += hillClimber.getFailureSteps();
				moves++;
				hillClimber = new HillClimbing();
			}else {
				currentNode = nextNode;
			}
		}
		return currentNode;
	}

	public int getHillClimbingSteepest_Successes() {
		return successStepsSteepest;
	}

	//random restart algorithm for without sideways moves
	public Node randomRestartSteepest(){
		Node currentNode = hillClimber.getStartNode();
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();
		while(heuristic!=0){
			Node nextNode = hillClimber.hillClimbing_Steepest("NoPrint");
			successSteps += hillClimber.getSuccessSteps();
			heuristic = nextNode.getHeuristic();
			if(heuristic!=0){ //restart
				randomRestartSteps++;
				successSteps += hillClimber.getFailureSteps();
				hillClimber = new HillClimbing();
			}else {
				currentNode = nextNode;
			}
		}
		return currentNode;
	}

	
	//Sets the initial board
	public void setStartNode(Node n){
		start = n;
	}
	
	//get initial board
	public Node getStartNode(){
		return start;
	}

	//returns the number of nodes generated
	public int getNodesGenerated(){
		return nodesGenerated;
	}
}
