package minimax;

public class BoardPosition {
	public int row;
	public int col;
	
	public BoardPosition(int row_, int col_) {
		this.row = row_;
		this.col = col_;
	}
	
	// check if board position is legal on an n x n board
	public boolean isLegalOnSizeOf(int n) {
		return this.row < n && this.col < n;
	}
}
