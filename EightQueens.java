import java.sql.SQLOutput;
import java.util.*;
import java.text.NumberFormat;

public class EightQueens {
	public static int N;

    public int getN() {
        return N;
    }

    public static void main(String[] args){
		System.out.println("enter number of queens");
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		EightQueens board = new EightQueens();
		System.out.println("Enter the number of runs");
		int numberOfRuns = sc.nextInt();
		int hillClimbSideWaysSuccesses=0, randomRestartSuccesses=0;
		int hillClimbingSteepest_Successes = 0,randomRestartSuccessesSteepest =0;
		int successStepsSideWays = 0, failureStepsSideWays = 0;
		int successStepsSteepest =0, failureStepsSteepest = 0;
		int RandomRestartsSideWays =0, RandomStepsSideWays=0;
		int RandomRestartsSteepest =0, RandomStepsSteepest=0;


		for(int i=0; i<numberOfRuns; i++){
			Queen[] startBoard = board.generateBoard();

			HillClimbing hillClimber = new HillClimbing(startBoard);
			HillClimbing hillClimberSteepest = new HillClimbing(startBoard);
			RandomRestart randomRestart = new RandomRestart(startBoard);
			RandomRestart randomRestartSteepest = new RandomRestart(startBoard);
			Node hillSolvedSteepest = hillClimberSteepest.hillClimbing_Steepest("NoPrint");
			Node hillSolved = hillClimber.hillClimbing_SideWays("NoPrint");
			Node randomSolved = randomRestart.randomRestart();
			Node randomSolvedSteepest = randomRestartSteepest.randomRestartSteepest();

			//if heristic=0 increment the success cost of the steepest hill climbing
			if(hillSolvedSteepest.getHeuristic()==0){
				successStepsSteepest += hillClimberSteepest.getSuccessSteps();
				hillClimbingSteepest_Successes++;
			}

			//if heristic!=0 increment the failure cost of the steepest hill climbing
			else
			{
				failureStepsSteepest += hillClimberSteepest.getFailureSteps();
			}

			//if heristic=0 increment the success cost of the sideways steepest ascent hill climbing
			if(hillSolved.getHeuristic()==0){
				successStepsSideWays += hillClimber.getSuccessSteps();
				hillClimbSideWaysSuccesses++;
			}

			//if heristic!=0 increment the failure cost of the sideways steepest ascent hill climbing
			else
            {
				failureStepsSideWays += hillClimber.getFailureSteps();
            }

			//if heristic=0 increment the success cost of the random restart hill climbing with sideways
			if(randomSolved.getHeuristic()==0){
				RandomRestartsSideWays += randomRestart.getRandomRestartSteps();
				RandomStepsSideWays += randomRestart.getSuccessSteps();
				randomRestartSuccesses++;
			}

			//if heristic=0 increment the success cost of the random restart hill climbing without sideways
			if(randomSolvedSteepest.getHeuristic()==0)
			{
				RandomRestartsSteepest += randomRestartSteepest.getRandomRestartSteps();
				RandomStepsSteepest +=randomRestartSteepest.getSuccessSteps();
				randomRestartSuccessesSteepest++;
			}
		}
		NumberFormat fmt = NumberFormat.getPercentInstance();
		System.out.println("Hill climb Steepest successes: "+hillClimbingSteepest_Successes);
		double hillClimbSteepestPercent = (double)hillClimbingSteepest_Successes/(double)numberOfRuns;
		double hillClimbSteepestPercentFailure = (double)(numberOfRuns-hillClimbingSteepest_Successes)/(double)numberOfRuns;
		System.out.println("Percent successes: "+fmt.format(hillClimbSteepestPercent));
		System.out.println("Percent Failures: "+fmt.format(hillClimbSteepestPercentFailure));
		System.out.println("Average Success Steps for Steepest Hill Climbing: "+ (successStepsSteepest)/hillClimbingSteepest_Successes);
		System.out.println("Average Failure Steps for Steepest Hill Climbing: " + (failureStepsSteepest)/(numberOfRuns-hillClimbingSteepest_Successes));
		System.out.println();

		System.out.println("Hill climb Side Ways successes: "+hillClimbSideWaysSuccesses);
		double hillClimbPercent = (double)hillClimbSideWaysSuccesses/(double)numberOfRuns;
		double hillClimbPercentFailure = (double)(numberOfRuns-hillClimbSideWaysSuccesses)/(double)numberOfRuns;
		System.out.println("Percent successes: "+fmt.format(hillClimbPercent));
		System.out.println("Percent Failures: "+fmt.format(hillClimbPercentFailure));
		System.out.println("Average Success Steps for Side Ways: "+ Math.round((successStepsSideWays)/hillClimbSideWaysSuccesses));
		if(failureStepsSideWays!=0)
		System.out.println("Avearage Failure Steps for Side Ways: " + Math.round((failureStepsSideWays)/(numberOfRuns-hillClimbSideWaysSuccesses)));
		System.out.println();

		double randomRestartPercent = (double)(randomRestartSuccesses/numberOfRuns);
        System.out.println("Number of random restarts for side ways: " + Math.round(RandomRestartsSideWays)/(numberOfRuns-hillClimbSideWaysSuccesses));
		System.out.println("Average number of steps for random restart for side ways: "+ RandomStepsSideWays/numberOfRuns);
		System.out.println();

		double randomRestartPercentSteepest = (double)(randomRestartSuccessesSteepest/numberOfRuns);
		System.out.println("Number of random restarts without side ways moves: " + Math.round(RandomRestartsSteepest/numberOfRuns));
		System.out.println("Average number of steps for random restarts without side ways moves: "+ RandomStepsSteepest/numberOfRuns);
		System.out.println();

		// Printing Sequences
		System.out.println();
		System.out.println();

		//printing the three random initial configurations for steepest ascent hill climbing
		for(int i=1;i<=3;i++) {
			Queen[] startBoardPrint = board.generateBoard();
			HillClimbing hillClimberSteepestPrint = new HillClimbing(startBoardPrint);
			System.out.println("Printing Sequence "+i+" for the Steepest Ascent Hill Climbing");
			Node hillSolvedSteepestPrint = hillClimberSteepestPrint.hillClimbing_Steepest("Print");
			System.out.print(hillClimberSteepestPrint.getCurrentNode());
			System.out.println("heuristic Value of above board is " + hillSolvedSteepestPrint.getHeuristic());
			if(hillSolvedSteepestPrint.getHeuristic()==0)
			{
				System.out.println(i + " random Input Queen Board(s) is solved");
			}
			else
			{
				System.out.println(i + " random Input Queen Board(s) is Failed");
			}
			System.out.println();
			System.out.println();
		}

		//printing the three random initial configurations for steepest ascent hill climbing with sideways moves
		for(int i=1;i<=3;i++) {
			Queen[] startBoardPrint = board.generateBoard();
			HillClimbing hillClimbingSideWaysPrint = new HillClimbing(startBoardPrint);
			System.out.println("Printing the Sequence "+i+" for Side Ways Hill Climbing");
			Node hillSolvedSideWaysPrint = hillClimbingSideWaysPrint.hillClimbing_SideWays("Print");
			System.out.print(hillClimbingSideWaysPrint.getCurrentNode());
			System.out.println("heuristic Value of above board is " + hillSolvedSideWaysPrint.getHeuristic());
			if(hillSolvedSideWaysPrint.getHeuristic()==0)
			{
				System.out.println(i + " random Input Queen Board(s) is solved");
			}
			else
			{
				System.out.println(i + " random Input Queen Board(s) is Failed");
			}
			System.out.println();
			System.out.println();
		}


	}

	//generate the random queen board
	public Queen[] generateBoard(){
		Queen[] start = new Queen[N];
		Random gen = new Random();
		
		for(int i=0; i<N; i++){
			start[i] = new Queen(gen.nextInt(N),i);
		}
		return start;
	}
}
