package boardgame;

public class Piece {
	
	protected Position position; // Posição simples em matriz --> Posição não visivel na camada de xadrez.
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//Remova o set para não permitir que o tabuleiro seja alterado.
	protected Board getBoard() { // Somente classes dentro do mesmo pacote e sub-classes poderão acessar o tabuleiro de uma peça.
		return board;
	}

}
