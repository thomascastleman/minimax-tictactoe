package minimax;

import minimax.Main.Symbol;

public class TicTacToeGame extends Main {
	public Agent player1;
	public Agent player2;
	
	public int boardDimension;		// dimension of board
	
	public TicTacToeState currentBoardState;	// current state of the game board
	
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
		this.currentBoardState = new TicTacToeState();
		this.currentBoardState.boardState = new Symbol[this.boardDimension][this.boardDimension];
		
		for (int i = 0; i < this.boardDimension; i++) {
			for (int j = 0; j < this.boardDimension; j++) {
				this.currentBoardState.boardState[i][j] = null;
			}
		}
	}
	
	// if legal, update board to reflect a given move
	public void updateBoard(BoardPosition pos, Symbol sym) {
		if (pos != null && this.currentBoardState.boardState != null) {
			if (pos.isLegalOnSizeOf(this.boardDimension) && this.currentBoardState.boardState[pos.row][pos.col] == null) {
				this.currentBoardState.boardState[pos.row][pos.col] = sym;
			} else {
				System.out.println("Illegal Move (TicTacToeGame.updateBoard())");
			}
		} else {
			System.out.println("\n\nNULL position or boardstate (TicTacToeGame.updateBoard())");
		}
	}
	
	// begin game of tic tac toe
	public void startGame() {
		
		while (true) {
			BoardPosition p1move = player1.getMove(this.currentBoardState, this.boardDimension);
			this.updateBoard(p1move, player1.symbol);
			
			System.out.println("BOARDSTATE: ");
			this.currentBoardState.logState();
			
			if (currentBoardState.isWinState(p1move, player1.symbol)) {
				System.out.println("PLAYER 1 WINS");
				break;
			} else if (currentBoardState.checkIfAllFilled()) {
				System.out.println("TIE");
				break;
			}
			
			BoardPosition p2move = player2.getMove(this.currentBoardState, this.boardDimension);
			this.updateBoard(p2move, player2.symbol);
			
			System.out.println("BOARDSTATE: ");
			this.currentBoardState.logState();
			
			if (currentBoardState.isWinState(p2move, player2.symbol)) {
				System.out.println("PLAYER 2 WINS");
				break;
			} else if (currentBoardState.checkIfAllFilled()) {
				System.out.println("TIE");
				break;
			}
			
		}
	}
}
