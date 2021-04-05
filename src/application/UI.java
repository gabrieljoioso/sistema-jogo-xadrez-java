package application;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " "); // Imprime a numeração da linha
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]); // Imprime a peça.
			}
			System.out.println(); // quebra de linha
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) {
		if (piece == null) { // se peça for igual a null, não há peça nessa posição do tabuleiro.
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
