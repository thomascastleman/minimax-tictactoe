package minimax;

import minimax.Main.Symbol;
import java.util.*;

public class AI extends Agent {
	
	public int depth;	// depth for minimax
	public Symbol symbol;
	
	public AI(int depth_, Symbol symbol_) {
		this.depth = depth_;
		this.symbol = symbol_;
	}
	
	public BoardPosition getMove(TicTacToeState state, int dimension) {
		TicTacToeState bestState = this.minimax(state, this.depth, true);
		return bestState.moveFromPrevious;
	}
	
	public TicTacToeState minimax(TicTacToeState currentState, int depth, boolean isMaximizing) {
		// if win, cost == positive infinity
		if (currentState.isWinState(currentState.moveFromPrevious, this.symbol)) {
			currentState.cost = Float.POSITIVE_INFINITY;
			return currentState;
		// if loss, cost == negative infinity
		} else if (currentState.isWinState(currentState.moveFromPrevious, this.symbol == Symbol.X ? Symbol.O : Symbol.X)) {
			currentState.cost = Float.NEGATIVE_INFINITY;
			return currentState;
		}
		
		// if at max depth, use heuristic evaluation
		if (depth == 0) {
			currentState.cost =  this.heuristic(currentState);
			return currentState;
		} else {
			
			// otherwise, evaluate recursively
			ArrayList<TicTacToeState> children = currentState.getSuccessorStates(isMaximizing ? this.symbol : (this.symbol == Symbol.X ? Symbol.O : Symbol.X));
			
			TicTacToeState bestState = children.get(0);
			for (int i = 1; i < children.size(); i++) {
				TicTacToeState childWithCost = this.minimax(children.get(i), depth - 1, !isMaximizing);
				
				if (isMaximizing) {
					if (childWithCost.cost > bestState.cost) {
						bestState = childWithCost;
					}
				} else {
					if (childWithCost.cost < bestState.cost) {
						bestState = childWithCost;
					}
				}	
			}
			
			return bestState;
		}
	}
	
	public float heuristic(TicTacToeState state) {
		return 0.0f;
	}
}
