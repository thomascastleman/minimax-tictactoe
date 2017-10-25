package minimax;

import minimax.Main.Symbol;
import java.util.*;

public class TicTacToeState extends TicTacToeGame {
	
	public Symbol[][] boardState;				// array of symbols to represent state
	public float cost; // for minimax			// cost of state (float for support with infinity)
	public BoardPosition moveFromPrevious;		// board position of move that led to this state
	
	public TicTacToeState() {
		
	}
	
	public TicTacToeState(BoardPosition positionOfChange, Symbol changeTo, TicTacToeState prevState) {
		this.moveFromPrevious = positionOfChange;
		this.boardState = copyBoard(prevState.boardState);
		
		// if move legal
		if (positionOfChange.isLegalOnSizeOf(this.boardState.length) && this.boardState[positionOfChange.row][positionOfChange.col] == null) {
			this.boardState[positionOfChange.row][positionOfChange.col] = changeTo;
		} else {
			System.out.println("Illegal Move (TicTacToeState())");
		}
	}
	
	// log state to console
	public void logState() {
		System.out.println("\n");
		for (int i = -1; i < this.boardState.length; i++) {
			for (int j = 0; j < this.boardState.length; j++) {
				if (i == -1) {
					System.out.print(j + " ");
				} else {
					Symbol s = this.boardState[i][j];
					System.out.print((s == null ? " " : s) + " ");
				}
			}

			System.out.println(i > -1 ? i : "");
		}
	}
	
	// copy a board state
	public static Symbol[][] copyBoard(Symbol[][] board) {
		Symbol[][] copy = new Symbol[board.length][board.length];
		
		for (int i = 0; i < board.length; i++) {
			copy[i] = board[i].clone();
		}
		
		return copy;
	}
	
	// check if state is a win state
	public boolean isWinState(BoardPosition lastMove, Symbol winSymbol) {
		if (lastMove == null) {
			// check all possible wins
			for (int i = 0; i < this.boardState.length; i++) {
				BoardPosition pos = new BoardPosition(i, i);
				if (this.checkForRowColWins(pos, winSymbol)) {
					return true;
				}
			}
			
			return this.checkForDiagonalWins(winSymbol);

		} else {
			// only check wins surrounding lastmove
			if (this.checkForRowColWins(lastMove, winSymbol)) {
				return true;
			} else {
				// if diagonal win possible
				if (lastMove.row == lastMove.col || lastMove.row + lastMove.col == this.boardState.length - 1) {
					return this.checkForDiagonalWins(winSymbol);
				} else {
					return false;
				}
			}
		}
	}
	
	// check this state for row / col wins of a given symbol
	public boolean checkForRowColWins(BoardPosition position, Symbol sym) {
		// check row / col win possibilities
		boolean rowWinFound = true;	// assume win
		boolean colWinFound = true;
		for (int i = 0; i < this.boardState.length; i++) {
			if (this.boardState[position.row][i] != sym) {
				rowWinFound = false;
			}
			if (this.boardState[i][position.col] != sym) {
				colWinFound = false;
			}
		}
					
		return rowWinFound || colWinFound;
	}
	
	// check this state for diagonal wins of a given symbol
	public boolean checkForDiagonalWins(Symbol sym) {
		boolean topDiagWin = true;			// diagonal from top left to bottom right
		boolean bottomDiagWin = true;		// diagonal from bottom left to top right

		for (int i = 0; i < this.boardState.length; i++) {
			if (this.boardState[i][i] != sym) {
				topDiagWin = false;
			}
			if (this.boardState[(this.boardState.length - 1) - i][i] != sym) {
				bottomDiagWin = false;
			}
		}
		
		return topDiagWin || bottomDiagWin;
	}
	
	public ArrayList<TicTacToeState> getSuccessorStates(Symbol currentMove) {
		ArrayList<TicTacToeState> successors = new ArrayList<TicTacToeState>();
		
		for (int i = 0; i < this.boardState.length; i++) {
			for (int j = 0; j < this.boardState.length; j++) {
				// if available position
				if (this.boardState[i][j] == null) {
					// add new state to successors
					BoardPosition pos = new BoardPosition(i, j);
					successors.add(new TicTacToeState(pos, currentMove, this));
				}
			}
		}
		
		return successors;
	}
	
}







