package boardgame;

public abstract class Piece {
	
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
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) { // Hook Methods. Metodo concreto utilizando o metodo abstrato.
		return possibleMoves() [position.getRow()][position.getColumn()]; // chamando uma possivel implementação de uma subclasse concreta da classe Piece.
	}
	
	public boolean isThereAnyPossibleMove() { // chama o metodo possibleMoves, varre a matriz e verifica se pelo uma posição é verdadeira.
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
