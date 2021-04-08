package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) { // Construtor repassando a chamada para a superclasse.
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// Verificar acima da pe�a
		p.setValues(position.getRow() - 1, position.getColumn()); // linha acima da posi��o da minha pe�a e a mesma coluna da minha pe�a.
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posi��o p existir e n�o houver uma pe�a l�
			mat[p.getRow()][p.getColumn()] = true; // a pe�a pode mover pela matriz
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Verificar a esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); 
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posi��o p existir e n�o houver uma pe�a l�
			mat[p.getRow()][p.getColumn()] = true; // a pe�a pode mover pela matriz
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Verificar a direita
		p.setValues(position.getRow(), position.getColumn() + 1); 
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posi��o p existir e n�o houver uma pe�a l�
			mat[p.getRow()][p.getColumn()] = true; // a pe�a pode mover pela matriz
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Verificar abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); 
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posi��o p existir e n�o houver uma pe�a l�
			mat[p.getRow()][p.getColumn()] = true; // a pe�a pode mover pela matriz
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

}
