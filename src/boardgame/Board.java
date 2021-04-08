package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces; // Matriz de pe�as
	
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) { // Programa��o defensiva
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // Matriz de pe�as instanciada na quantidade de linhas e colunas informadas.
	}


	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	
	public Piece piece(int row, int column) {  // retorna a matriz pieces na linha row e coluna column
		if (!positionExists(row, column)) { // se a posi��o n�o existe, lance a exception
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { // sobrecarga
		if (!positionExists(position)) { // se a posi��o n�o existe, lance a exception
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) { // se j� existe uma pe�a nessa posi��o lan�a a exception
			throw new BoardException("There is already a piece on position: " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; // Matriz declarada no tabuleiro e instanciada no construtor. Atribui a pe�a na posi��o linha/coluna da matriz. 
		piece.position = position; // Posi��o n�o � mais nula.
	}	
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) { // se a posi��o n�o existe, lance a exception
			throw new BoardException("Position not on the board");
		}
		if (piece(position) == null) { // se a posi��o for nula, n�o h� uma pe�a.
			return null;
		}
		Piece aux = piece(position);
		aux.position = null; // pe�a foi retirada do tabuleiro
		pieces[position.getRow()][position.getColumn()] = null; // N�o h� mais pe�a na posi��o da matriz.
		return aux;
	}
	
	private boolean positionExists(int row, int column) { // teste pela linha e coluna, existe quando a posi��o est� dentro do tabuleiro
		return row >= 0 && row < rows && column >= 0 && column < columns; // rows --> altura do tabuleiro | columns --> quantidade de colunas
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn()); // reaproveitando o metodo de cima para testar se a posi��o existe
	}
	
	public boolean thereIsAPiece(Position position) { // teste para testar se h� uma pe�a.
		if (!positionExists(position)) { // se a posi��o n�o existe, lance a exception
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null; // se a pe�a for diferente de nulo, h� uma pe�a na posi��o.
	}

	
}
