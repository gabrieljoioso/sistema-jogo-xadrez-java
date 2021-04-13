// Nessa classe temos as regras do jogo.

package chess;

import boardgame.Board;
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
	
	public ChessPiece[][] getPieces() { // Percorre a matriz de pe�as do board e para cada pe�a fazemos um downcasting para ChessPiece.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for	(int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // para cada posi��o ij do tabuleiro minha matriz mat 
			}	// receba board.piece ij com downcasting para ChessPiece, interpretando como pe�a de xadrez e n�o como uma pe�a comum.
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) { // Movimentos possiveis para a pe�a.
		Position position = sourcePosition.toPosition(); // converte posi��o de xadrez para posi��o normal
		validateSourcePosition(position); // validar posi��o de origem
		return board.piece(position).possibleMoves(); // retorna os movimentos possiveis da pe�a.
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { // posi��o de origem e destino.
		Position source = sourcePosition.toPosition(); // converte para posi��o da Matriz.
		Position target = targetPosition.toPosition();	// converte para posi��o da Matriz.
		validateSourcePosition(source); // Validar a posi��o de origem, se n�o existir, lan�a exception.
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target); // recebe o resultado da opera��o makemove que realiza o movimento da pe�a.
		return (ChessPiece)capturedPiece; // downcasting para ChessPiece, antes era Piece.
	}
	
	private Piece makeMove(Position source, Position target) { // Realizando um movimento.
		Piece p = board.removePiece(source); // Remove a pe�a da posi��o de origem
		Piece capturedPiece = board.removePiece(target); // Remover a possivel pe�a que est� na posi��o de destino, sendo capturada.
		board.placePiece(p, target); // Pe�a p: Coloca a pe�a da posi��o de origem na posi��o de destino
		return capturedPiece; // retorna a pe�a capturada.
	}
	
	private void validateSourcePosition(Position position) { 
		if (!board.thereIsAPiece(position)) { // Se n�o existir uma pe�a nessa posi��o, exception.
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { // Se n�o haver nenhum movimento poss�vel, exception.
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) { // Se para pe�a de origem a posi��o de destino n�o � um movimento possivel, exception.
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { // Colocar pe�a passando a posi��o nas coordenadas do xadrez.
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		
	}
	
	private void initialSetup() { //Iniciar a partida colocando as pe�as no tabuleiro.
		placeNewPiece('c', 1, new Rook(board, Color.WHITE)); // Instancia da pe�a no tabuleiro com cor branca e posi��o 'b', 6.
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
