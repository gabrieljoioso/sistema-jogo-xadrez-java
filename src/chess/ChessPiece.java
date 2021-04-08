package chess;

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board); // repassa a chamada para o construtor da superclasse
		this.color = color;
	}

	// Apague o set para não deixar que a cor de uma peça seja modificada.
	public Color getColor() { // Só pode ser acessada.
		return color;
	}

}
