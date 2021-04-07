// Classe que converte a posição em char/int para a posição na Matriz.

package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) { // Programação defensiva
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}

	// Apague os sets para não permitir que coluna e linha sejam livremente alteradas.
	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(8 - row, column - 'a'); // Ex: 8 - 8: Posição 0 da linha
	}
	
	protected static ChessPosition fromPosition(Position position ) { // Operação inversa, convertendo posição na matriz para pos no xadrez.
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row; // Forçar uma concatenação de strings.
	}
	
}
