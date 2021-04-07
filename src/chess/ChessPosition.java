// Classe que converte a posi��o em char/int para a posi��o na Matriz.

package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) { // Programa��o defensiva
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	// Apague os sets para n�o permitir que coluna e linha sejam livremente alteradas.
	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(8 - row, column - 'a'); // Ex: 8 - 8: Posi��o 0 da linha
	}
	
	protected static ChessPosition fromPosition(Position position ) { // Opera��o inversa, convertendo posi��o na matriz para pos no xadrez.
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row; // For�ar uma concatena��o de strings.
	}
	
}
