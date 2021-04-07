// Nessa classe temos as regras do jogo.

package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8); // Tabuleiro 8x8
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() { // Percorre a matriz de pe�as do board e para cada pe�a fazemos um downcasting para ChessPiece.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for	(int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // para cada posi��o ij do tabuleiro minha matriz mat 
			}	// receba board.piece ij com downcasting para ChessPiece, interpretando como pe�a de xadrez e n�o como uma pe�a comum.
		}
		return mat;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { // Colocar pe�a passando a posi��o nas coordenadas do xadrez.
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
	
	private void initialSetup() { //Iniciar a partida colocando as pe�as no tabuleiro.
		placeNewPiece('b', 6, new Rook(board,  Color.WHITE)); // Instancia da pe�a no tabuleiro com cor branca e posi��o 'b', 6.
		placeNewPiece('e', 8, new King(board,  Color.BLACK));
		placeNewPiece('e', 1, new King(board,  Color.WHITE));
	}
}
