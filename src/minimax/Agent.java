package minimax;

import minimax.Main.Symbol;
import minimax.Main.AgentType;

public class Agent extends TicTacToeGame {
	
	public Symbol symbol;
	public AgentType type;
	
	public AI ai;
	public Human human;
	
	// empty constructor
	public Agent() {
		
	}
	
	public Agent(Symbol symbol_, AgentType type_, int aiDepth) {
		this.symbol = symbol_;
		this.type = type_;
		
		if (this.type == AgentType.AI) {
			this.ai = new AI(aiDepth);
		} else if (this.type == AgentType.HUMAN) {
			this.human = new Human();
		}
		
	}
	
	// get move from this agent
	public BoardPosition getMove(TicTacToeState state, int dimension) {
		if (this.type == AgentType.AI) {
			return this.ai.getMove(state, dimension);
		} else if (this.type == AgentType.HUMAN) {
			return this.human.getMove(state, dimension);
		}
		
	}
	
}
