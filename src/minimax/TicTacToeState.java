package minimax;
import minimax.Main.Symbol;

public class TicTacToeState extends TicTacToeGame {
	
	public Symbol[][] boardState;				// array of symbols to represent state
	public int cost; // for minimax				// cost of state
	public BoardPosition moveFromPrevious;		// board position of move that led to this state
	
	public TicTacToeState() {
		
	}
	
	public TicTacToeState(BoardPosition positionOfChange, Symbol changeTo, TicTacToeState prevState) {
		this.moveFromPrevious = positionOfChange;
		this.boardState = copyBoard(prevState.boardState);
		
		// if move legal
		if (positionOfChange.isLegalOnSizeOf(super.boardDimension) && this.boardState[positionOfChange.row][positionOfChange.col] == null) {
			this.boardState[positionOfChange.row][positionOfChange.col] = changeTo;
		} else {
			System.out.println("Illegal Move (TicTacToeState())");
		}
	}
	
	// log state to console
	public void logState() {
		for (int i = 0; i < this.boardState.length; i++) {
			for (int j = 0; j < this.boardState.length; j++) {
				System.out.print(this.boardState[i][j] + " ");
			}
			System.out.println("");
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
	public boolean isWinState(BoardPosition lastMove) {
		if (lastMove == null) {
			// check all possible wins
		} else {
			// only check wins surrounding lastmove
		}
	}
	
	public ArrayList<TicTacToeState> getSuccessorStates() {
		
	}
	
}
