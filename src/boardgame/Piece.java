package boardgame;

public class Piece {
	
	protected Position position; // Posi��o simples em matriz --> Posi��o n�o visivel na camada de xadrez.
	private Board board;
	
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//Remova o set para n�o permitir que o tabuleiro seja alterado.
	protected Board getBoard() { // Somente classes dentro do mesmo pacote e sub-classes poder�o acessar o tabuleiro de uma pe�a.
		return board;
	}

}
