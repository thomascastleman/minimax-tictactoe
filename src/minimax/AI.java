package minimax;

import minimax.Main.Symbol;

public class AI extends Agent {
	
	public int depth;	// depth for minimax
	
	public AI(int depth_) {
		this.depth = depth_;
	}
	
	public BoardPosition getMove(TicTacToeState state, int dimension) {
		TicTacToeState bestState = this.minimax(state, this.depth, true);
		return bestState.moveFromPrevious;
	}
	
	public TicTacToeState minimax(TicTacToeState currentState, int depth, boolean isMaximizing) {
		
	}
}
