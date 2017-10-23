package minimax;

import java.util.*;

public class Main {
	
	public enum Symbol{X, O};
	public enum AgentType{AI, HUMAN};
	
	
	public static void main(String[] args) {
		
		Agent p1 = new Agent(Symbol.X, AgentType.HUMAN, 0);
		Agent p2 = new Agent(Symbol.O, AgentType.AI, 3);
		TicTacToeGame game = new TicTacToeGame(3, p1, p2);

		
		game.initializeBoardState();
		

	}

}
