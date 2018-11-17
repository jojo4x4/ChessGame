package application.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import application.model.Board.Type;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class King extends Piece {
	public boolean castlingAvailableRight;
	public boolean castlingAvailableLeft;
	public ArrayList<Coordinate> castlingMove;
	

	public ArrayList<Coordinate> getCastlingMove() {
		return castlingMove;
	}

	public void setCastlingMove(ArrayList<Coordinate> castlingMove) {
		this.castlingMove = castlingMove;
	}

	public King(Type color) {
		super(color);
		castlingAvailableRight = false;
		castlingAvailableLeft = false;
		castlingMove = new ArrayList<Coordinate>();
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//first number is the vertical direction, the second is the horizontal direction
		addMovement(availCoords, r - 1, c, board);// South direction
		addMovement(availCoords, r, c - 1, board);// West direction
		addMovement(availCoords, r + 1, c, board);// North direction
		addMovement(availCoords, r, c + 1, board);// East direction
		addMovement(availCoords, r - 1, c - 1, board);// Northwest direction
		addMovement(availCoords, r - 1, c + 1, board);// Northeast direction
		addMovement(availCoords, r + 1, c - 1, board);// Southwest direction
		addMovement(availCoords, r + 1, c + 1, board);// Southeast direction}

		//checks for castling is allowed and adds it to avail movements
		if (getType() == Type.WHITE && board.whiteEverChecked == false && getHasMoved() == false && (board.getPiece(7,0).toString().equals("Rook") || board.getPiece(7,7).toString().equals("Rook"))) {
			
			if(!board.hasPiece(7,5) && !board.hasPiece(7,6)){
				addMovement(availCoords, r, c + 2, board);// east direction
				this.castlingMove.add(new Coordinate(r, c + 2));
			}
			
			if(!board.hasPiece(7,1) && !board.hasPiece(7,2) && !board.hasPiece(7,3)){
				addMovement(availCoords, r, c - 2, board);// west direction
				addMovement(availCoords, r, c - 3, board);// west direction
				addMovement(availCoords, r, c - 4, board);// west direction
				this.castlingMove.add(new Coordinate(r, c + 2));
				this.castlingMove.add(new Coordinate(r, c + 3));
				this.castlingMove.add(new Coordinate(r, c + 4));
			}
			
			
		}
		else if (getType() == Type.BLACK && board.blackEverChecked == false && getHasMoved() == false && (board.getPiece(0,0).toString().equals("Rook") || board.getPiece(0,7).toString().equals("Rook"))) {
			
			if(!board.hasPiece(0,5) && !board.hasPiece(0,6)){
				addMovement(availCoords, r, c + 2, board);// east direction
				this.castlingMove.add(new Coordinate(r, c + 2));
			}
			
			if(!board.hasPiece(0,1) && !board.hasPiece(0,2) && !board.hasPiece(0,3)){
				addMovement(availCoords, r, c - 2, board);// west direction
				addMovement(availCoords, r, c - 3, board);// west direction
				addMovement(availCoords, r, c - 4, board);// west direction
				this.castlingMove.add(new Coordinate(r, c + 2));
				this.castlingMove.add(new Coordinate(r, c + 3));
				this.castlingMove.add(new Coordinate(r, c + 4));
			}
			
		}
	
		return availCoords;
	}

	

	public boolean isCastlingAvailableRight() {
		return castlingAvailableRight;
	}

	public void setCastlingAvailableRight(boolean castlingAvailableRight) {
		this.castlingAvailableRight = castlingAvailableRight;
	}

	public boolean isCastlingAvailableLeft() {
		return castlingAvailableLeft;
	}

	public void setCastlingAvailableLeft(boolean castlingAvailableLeft) {
		this.castlingAvailableLeft = castlingAvailableLeft;
	}

	public void addMovement(ArrayList<Coordinate> availCoords, int r, int c, Board board) {
		if (boundsChecker(r, c)) {
			if (board.hasPiece(new Coordinate(r, c))) {// a piece is on this location
				if (board.getPiece(new Coordinate(r, c)).getType() == this.otherType())// its an enemy piece
					availCoords.add(new Coordinate(r, c));
			} 
			else // This location is a free space
				availCoords.add(new Coordinate(r, c));
		}
	}
	
	public String toString() {
		return "K";
	}

}