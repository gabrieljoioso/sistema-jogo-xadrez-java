// Nessa classe temos as regras do jogo.

package chess;

import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8); // Tabuleiro 8x8
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() { // Percorre a matriz de peças do board e para cada peça fazemos um downcasting para ChessPiece.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for	(int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // para cada posição ij do tabuleiro minha matriz mat 
			}	// receba board.piece ij com downcasting para ChessPiece, interpretando como peça de xadrez e não como uma peça comum.
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { // posição de origem e destino.
		Position source = sourcePosition.toPosition(); // converte para posição da Matriz.
		Position target = targetPosition.toPosition();	// converte para posição da Matriz.
		validateSourcePosition(source); // Validar a posição de origem, se não existir, lança exception.
		Piece capturedPiece = makeMove(source, target); // recebe o resultado da operação makemove que realiza o movimento da peça.
		return (ChessPiece)capturedPiece; // downcasting para ChessPiece, antes era Piece.
	}
	
	private Piece makeMove(Position source, Position target) { // Realizando um movimento.
		Piece p = board.removePiece(source); // Remove a peça da posição de origem
		Piece capturedPiece = board.removePiece(target); // Remover a possivel peça que está na posição de destino, sendo capturada.
		board.placePiece(p, target); // Peça p: Coloca a peça da posição de origem na posição de destino
		return capturedPiece; // retorna a peça capturada.
	}
	
	private void validateSourcePosition(Position position) { 
		if (!board.thereIsAPiece(position)) { // Se não existir uma peça nessa posição, exception.
			throw new BoardException("Position not on the board");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { // Colocar peça passando a posição nas coordenadas do xadrez.
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
	
	private void initialSetup() { //Iniciar a partida colocando as peças no tabuleiro.
		placeNewPiece('c', 1, new Rook(board, Color.WHITE)); // Instancia da peça no tabuleiro com cor branca e posição 'b', 6.
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
