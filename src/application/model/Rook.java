package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Rook extends Piece 
{
	public Rook(Type color)
	{
		super (color);
	}
	
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		boolean NBlocked = false, EBlocked = false, SBlocked = false, WBlocked = false;
		for (int i = 1; i < 8 ; i++){
			//north path
			if (!NBlocked && r - i >= 0) {
				availCoords.add(new Coordinate(r - i, c));
				if(board.getPiece(r - i, c) != null)
					NBlocked = true;
			}
			//east path
			if (!EBlocked && c + i <= 7) {
				availCoords.add(new Coordinate(r, c + i));
				if(board.getPiece(r, c + i) != null)
					NBlocked = true;
			}
			//south path
			if (!SBlocked && r + i <= 7) {
				availCoords.add(new Coordinate(r + i, c));
				if(board.getPiece(r + i, c) != null)
					NBlocked = true;
			}
			//west path
			if (!WBlocked && c - i >= 0) {
				availCoords.add(new Coordinate(r, c - i));
				if(board.getPiece(r, c - i) != null)
					NBlocked = true;
			}
		}
		return availCoords;
	}
}
