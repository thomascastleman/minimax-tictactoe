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
		// if tie, cost == 0
		} else if (currentState.checkIfAllFilled()) { 
			currentState.cost = 0;
			return currentState;
		}
		
		// if at max depth, use heuristic evaluation
		if (depth == maxDepth) {
			currentState.cost =  this.heuristic(currentState);
			return currentState;
		} else {
			
			// otherwise, evaluate recursively
			ArrayList<TicTacToeState> children = currentState.getSuccessorStates(isMaximizing ? this.symbol : (this.symbol == Symbol.X ? Symbol.O : Symbol.X));
			
			
			// initialize beststate with either really low cost if we're maximizing and really high cost if we're minimizing
			TicTacToeState bestState = new TicTacToeState();
			
			
			
			
			// DEBUGE
			ArrayList<TicTacToeState> costs = new ArrayList<TicTacToeState>();
			
			// for each successor
			for (int i = 0; i < children.size(); i++) {
				
				children.get(i).depth = depth;
				
				// recursively get minimax value of this child
				TicTacToeState childWithCost = this.minimax(children.get(i), !isMaximizing, depth + 1, maxDepth);
				// restore integrity of move, which changed from minimax call
				childWithCost.moveFromPrevious = children.get(i).moveFromPrevious;
				
				costs.add(childWithCost);
				
				if (i == 0) {
					bestState = childWithCost;
				}
				
				if (isMaximizing) {
					// if better cost found, replace
					if (childWithCost.cost > bestState.cost) {
						bestState = childWithCost;
					
					// if both wins, choose one with shallower depth
					} else if (childWithCost.cost == bestState.cost && childWithCost.depth < bestState.depth) {
						bestState = childWithCost;
					}
				} else {
					// if better cost found, replace
					if (childWithCost.cost < bestState.cost) {
						bestState = childWithCost;
						
					// if both losses, choose one with deeper depth
					} else if (childWithCost.cost == bestState.cost && childWithCost.depth < bestState.depth) {
						bestState = childWithCost;
					}
				}	
			}
			
			if (depth == 1) {
				for (int i = 0; i < costs.size(); i++) {
					System.out.println("Move: " + children.get(i).moveFromPrevious.row + ", " + children.get(i).moveFromPrevious.col + " Cost: " + costs.get(i).cost + " Depth: " + costs.get(i).depth);
				}
				System.out.println("CHOICE: " + bestState.moveFromPrevious.row + ", " + bestState.moveFromPrevious.col);
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

















