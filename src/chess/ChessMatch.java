// Nessa classe temos as regras do jogo.

package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	
	public ChessMatch() {
		board = new Board(8, 8); // Tabuleiro 8x8
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) { // Movimentos possiveis para a peça.
		Position position = sourcePosition.toPosition(); // converte posição de xadrez para posição normal
		validateSourcePosition(position); // validar posição de origem
		return board.piece(position).possibleMoves(); // retorna os movimentos possiveis da peça.
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { // posição de origem e destino.
		Position source = sourcePosition.toPosition(); // converte para posição da Matriz.
		Position target = targetPosition.toPosition();	// converte para posição da Matriz.
		validateSourcePosition(source); // Validar a posição de origem, se não existir, lança exception.
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target); // recebe o resultado da operação makemove que realiza o movimento da peça.
		
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false; // se o oponente ficou e check ela recebe true, se não, false.
		
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		return (ChessPiece)capturedPiece; // downcasting para ChessPiece, antes era Piece.
	}
	
	private Piece makeMove(Position source, Position target) { // Realizando um movimento.
		ChessPiece p = (ChessPiece)board.removePiece(source); // Remove a peça da posição de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target); // Remover a possivel peça que está na posição de destino, sendo capturada.
		board.placePiece(p, target); // Peça p: Coloca a peça da posição de origem na posição de destino
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece); // retira a peça da lista de tabuleiro
			capturedPieces.add(capturedPiece); // add a peça na lista de peças capturadas
		}
		
		return capturedPiece; // retorna a peça capturada.
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) { //Logica do check
		ChessPiece p = (ChessPiece)board.removePiece(target); // remove a peça do destino
		p.decreaseMoveCount();
		board.placePiece(p, source); // devolve a peça na posicção de origem
		
		if (capturedPiece != null) { // volta a peça para o tab na posição de destino. Desfaz a jogada.
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece); // remove a peça da lista de peças capturadas
			piecesOnTheBoard.add(capturedPiece); // add a peça na lista de peças do tabuleiro
		}
	}
	
	private void validateSourcePosition(Position position) { 
		if (!board.thereIsAPiece(position)) { // Se não existir uma peça nessa posição, exception.
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // Downcasting para ChessPiece e testa a cor da peça.
			throw new ChessException("The chosen piece is not yours");
	}
		if (!board.piece(position).isThereAnyPossibleMove()) { // Se não haver nenhum movimento possível, exception.
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) { // Se para peça de origem a posição de destino não é um movimento possivel, exception.
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; // se jogador atual for igual a color White, agora será color Black, caso contrario color.White.
	}
	
	private Color opponent(Color color) { //devolver o opponent de uma cor.
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE; 
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); // filtrando a lista
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p; //downcasting returnando como ChessPiece
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition(); // localiza a posição do rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); // lista de peças do oponente
		for (Piece p : opponentPieces) { // para cada peça p na lista de peças do oponente
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) { // testa se essa cor não está em check
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves(); // movimentos possiveis da peça p
			for (int i=0; i<board.getRows(); i++) { // percorre linha da matriz
				for (int j=0; j<board.getColumns(); j++) { // percorre coluna da matriz
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition(); // downcasting para ChessPiece e chama get.ChessPosition convertendo para toPosition				
						Position target = new Position(i, j); 
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color); // Faz o movimento para testar o checkMate.
						undoMove(source, target, capturedPiece); // desfaz o movimento
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { // Colocar peça passando a posição nas coordenadas do xadrez.
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
		
	}
	
	private void initialSetup() { //Iniciar a partida colocando as peças no tabuleiro.
		placeNewPiece('a', 1, new Rook(board, Color.WHITE)); // Instancia da peça no tabuleiro com cor branca e posição 'b', 6.
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
}
