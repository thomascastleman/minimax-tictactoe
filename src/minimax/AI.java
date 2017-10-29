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
		TicTacToeState bestState = this.minimax(state, true, 1, this.depth);
		System.out.println("Selecting state with cost " + bestState.cost + " from depth " + bestState.depth);
		bestState.logState();
		return bestState.moveFromPrevious;
	}
	
	public TicTacToeState minimax(TicTacToeState currentState, boolean isMaximizing, int depth, int maxDepth) {
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
		if (depth == maxDepth) {
			currentState.cost =  this.heuristic(currentState);
			return currentState;
		} else {
			
			// otherwise, evaluate recursively
			ArrayList<TicTacToeState> children = currentState.getSuccessorStates(isMaximizing ? this.symbol : (this.symbol == Symbol.X ? Symbol.O : Symbol.X));
			
			TicTacToeState bestState = children.get(0);
			for (int i = 1; i < children.size(); i++) {
				children.get(i).depth = depth;		// init child depth
				
				// recursively get minimax value of this child
				TicTacToeState childWithCost = this.minimax(children.get(i), !isMaximizing, depth + 1, maxDepth);
				
//				System.out.println("Child cost == " + childWithCost.cost);
//				System.out.println("child state: ");
//				childWithCost.logState();
				
				if (isMaximizing) {
					// if better cost found, replace
					if (childWithCost.cost > bestState.cost) {
						bestState = childWithCost;
					
					// if both wins, choose one with shallower depth
					} else if (childWithCost.cost == Float.POSITIVE_INFINITY && childWithCost.depth < bestState.depth) {
						bestState = childWithCost;
					}
				} else {
					// if better cost found, replace
					if (childWithCost.cost < bestState.cost) {
						bestState = childWithCost;
						
					// if both losses, choose one with deeper depth
					} else if (childWithCost.cost == Float.NEGATIVE_INFINITY && childWithCost.depth > bestState.depth) {
						bestState = childWithCost;
					}
				}	
			}
			
			return bestState;
		}
	}
	
	public float heuristic(TicTacToeState state) {
		// their min moves from win - our min moves from win
		return (float) this.getMinMovesFromWin(state, this.symbol);
	}
	
	public int getMinMovesFromWin(TicTacToeState state, Symbol symbol) {
		int min = Integer.MAX_VALUE;
		
		int moves;
		
		ArrayList<Symbol> ldiag = new ArrayList<Symbol>();
		ArrayList<Symbol> rdiag = new ArrayList<Symbol>();
		for (int i = 0; i < state.boardState.length; i++) {
			ArrayList<Symbol> row = new ArrayList<Symbol>(Arrays.asList(state.boardState[i]));
			moves = this.movesToWin(row, symbol);
			if (moves < min) { 
				min = moves;
			}
			
			ArrayList<Symbol> col = new ArrayList<Symbol>();
			for (int j = 0; j < state.boardState.length; j++) {
				col.add(state.boardState[i][j]);
			}
			moves = this.movesToWin(col, symbol);
			if (moves < min) { 
				min = moves;
			}
			
			ldiag.add(state.boardState[i][i]);
			rdiag.add(state.boardState[state.boardState.length - 1 - i][i]);
		}
		
		moves = this.movesToWin(ldiag, symbol);
		if (moves < min) { 
			min = moves;
		}
		
		moves = this.movesToWin(rdiag, symbol);
		if (moves < min) { 
			min = moves;
		}
		
		return min;
	}
	
	public int movesToWin(ArrayList<Symbol> section, Symbol symbol) {
		int count = 0;
		for (int i = 0; i < section.size(); i++) {
			if (section.get(i) != symbol && section.get(i) != null) {
				return Integer.MAX_VALUE;
			} else if (section.get(i) == null) {
				count++;
			}
		}
		return count;
	}
	
}

















