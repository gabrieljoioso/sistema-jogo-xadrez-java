package boardgame;

public abstract class Piece {
	
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
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) { // Hook Methods. Metodo concreto utilizando o metodo abstrato.
		return possibleMoves() [position.getRow()][position.getColumn()]; // chamando uma possivel implementa��o de uma subclasse concreta da classe Piece.
	}
	
	public boolean isThereAnyPossibleMove() { // chama o metodo possibleMoves, varre a matriz e verifica se pelo uma posi��o � verdadeira.
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
