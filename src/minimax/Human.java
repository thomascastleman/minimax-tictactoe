package minimax;

import minimax.Main.Symbol;
import java.util.*;

public class Human extends Agent {
	
	public static Scanner input = new Scanner(System.in);

	public BoardPosition getMove(TicTacToeState state, int dimension) {
		while (true) {
			System.out.println("\nEnter Board Position to move to");

			String inp = "";
			if (input.hasNextLine()) {
	        	inp = input.nextLine();
	        }
			
			String[] split = inp.split(" ");
			if (split.length > 2) {
				System.out.println("Error Processing Input");
			} else {
				int row = Integer.valueOf(split[0]);
				int col = Integer.valueOf(split[1]);
				BoardPosition p = new BoardPosition(row, col);
				// return only if legal move
				if (p.isLegalOnSizeOf(dimension)) {
					return p;
				}
			}
		}
	}
}
