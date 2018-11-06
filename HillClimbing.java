import java.util.*;

public class HillClimbing {
    EightQueens QueensClass = new EightQueens();
    int N = QueensClass.getN();
    private Queen[] startState;
    private Node start; //start state
    private Node currentNode;
    private int steps;
    private int successSteps;
    private int failureSteps;
    private int moves;
    private int printCount;

    public HillClimbing() {
        start = new Node(); //empty start node
        startState = new Queen[N]; //empty start state
        startState();
    }

    public int getMoves() {
        return moves;
    }

    // Constructs HillClimbing with a starting board and it computes the heuristic of the given board
    public HillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[N];
        for (int i = 0; i < s.length; i++) {
            startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    // Sets the starting state
    public void startState() {
        //sets up a pseudo random start state
        Random gen = new Random();
        for (int i = 0; i < N; i++) {
            startState[i] = new Queen(gen.nextInt(N), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public int getSuccessSteps() {
        return successSteps;
    }

    public int getFailureSteps() {
        return failureSteps;
    }

    //Gives the least heuristic cost among the successors
    public int getLeastHeurCost(ArrayList<Node> successors, int LeastHeur) {
        for (int i = 0; i < successors.size(); i++) {
            if (successors.get(i).getHeuristic() < LeastHeur) {
                LeastHeur = successors.get(i).getHeuristic();
            }
        }
        return LeastHeur;
    }

    //stores all the children with least heuristic into bestChildren arraylist
    public ArrayList<Node> storeBestChildren(ArrayList<Node> successors, int leastHeur) {
        ArrayList<Node> bestChildren = new ArrayList<Node>();
        for (int i = 0; i < successors.size(); i++) {
            if (successors.get(i).getHeuristic() == leastHeur) {
                bestChildren.add(successors.get(i));
            }
        }
        return bestChildren;
    }

    //selects random best children from bestChildren arraylist
    public boolean chooseBestChild(ArrayList<Node> bestChildren) {
        Random gen = new Random();
        int randInteger = 0;

        if (bestChildren.size() != 0) {
            randInteger = gen.nextInt(bestChildren.size());
            currentNode = bestChildren.get(randInteger);

            if (currentNode.getHeuristic() == 0) {
                return true;
            }
        }
        return false;
    }


    // The hill climbing Steepest algorithm
    public Node hillClimbing_Steepest(String print) {
        currentNode = start;
        ArrayList<Node> bestChildren;
        while (true){
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            int leastHeur = getLeastHeurCost(successors, currentNode.getHeuristic());
            if (leastHeur >= currentNode.getHeuristic()) {
                failureSteps = moves;
                return currentNode;
            }
            bestChildren = storeBestChildren(successors, leastHeur);
            if (chooseBestChild(bestChildren)) {
                successSteps = moves;
                return currentNode;
            }
            else {
                moves++;
                bestChildren.clear();
            }
            if(print.equals("Print"))
            {
                System.out.println(currentNode);
                System.out.println("heuristic Value of above board is "+currentNode.getHeuristic());
            }
        }
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    // Hill Climbing Side Ways algorithm
    public Node hillClimbing_SideWays(String print) {
        currentNode = start;
        int counter = 0;
        ArrayList<Node> bestChildren;
        while (true) {
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            int leastHeur = getLeastHeurCost(successors, currentNode.getHeuristic());
            if (leastHeur == currentNode.getHeuristic()) {
                counter++;
                if (counter == 100) {
                    failureSteps = moves;
                    return currentNode;
                }
            } else if (leastHeur > currentNode.getHeuristic()) {
                failureSteps = moves;
                return currentNode;
            } else {
                counter = 0;
            }
            bestChildren = storeBestChildren(successors, leastHeur);
            if (chooseBestChild(bestChildren)) {
                successSteps = moves;
                return currentNode;
            } else {
                bestChildren.clear();
                moves++;
            }
            if(print.equals("Print"))
            {
                System.out.println(currentNode);
                System.out.println("heuristic Value of above board is "+currentNode.getHeuristic());
            }
        }
    }
    //Returns the Node's state
    public Node getStartNode() {
        return start;
    }
}
