package minimax;

import minimax.Main.Symbol;

public class TicTacToeGame extends Main {
	public Agent player1;
	public Agent player2;
	
	public int boardDimension;		// dimension of board
	
	public TicTacToeState currentBoardState;
	
	// empty constructor
	public TicTacToeGame() {
		
	}
	
	public TicTacToeGame(int dimension, Agent p1, Agent p2) {
		this.boardDimension = dimension;
		this.player1 = p1;
		this.player2 = p2;
	}
	
	// generate the starting empty board configuration
	public void initializeBoardState() {
		
	}
	
	// begin game of tic tac toe
	public void startGame() {
		
	}
}
