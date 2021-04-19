package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

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
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); // Visualiza a peça na posição 
		return p != null && p.getColor() != color; // Verifica se a posição é diferente de nulo e verifica se a cor da peça é diferente da cor da minha peça (se é peça adversária).  
	}

}
