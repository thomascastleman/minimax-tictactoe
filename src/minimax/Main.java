package minimax;

import java.util.*;

public class Main {
	
	public enum Symbol{X, O};
	public enum AgentType{AI, HUMAN};
	
	
	public static void main(String[] args) {
		
		Agent p1 = new Agent(Symbol.X, AgentType.HUMAN);
		Agent p2 = new Agent(Symbol.O, AgentType.AI);
		TicTacToeGame game = new TicTacToeGame(10, p1, p2);
		
		game.initializeBoardState();
		
		for (int i = 0; i < 10; i++) {
			game.updateBoard(new BoardPosition(i, i), Symbol.X);
		}
		
		game.currentBoardState.logState();
		
		System.out.println(game.currentBoardState.isWinState(null, Symbol.X));
		
	}

}
