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

	// Apague o set para n�o deixar que a cor de uma pe�a seja modificada.
	public Color getColor() { // S� pode ser acessada.
		return color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position); // Visualiza a pe�a na posi��o 
		return p != null && p.getColor() != color; // Verifica se a posi��o � diferente de nulo e verifica se a cor da pe�a � diferente da cor da minha pe�a (se � pe�a advers�ria).  
	}

}
