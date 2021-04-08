package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// codigos especiais de cores para imprimir no console. Cores de texto / cores de fundo.
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		 System.out.print("\033[H\033[2J");
		 System.out.flush();
	} 
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
		String s = sc.nextLine();
		char column = s.charAt(0); // coluna da string � o primeiro caracter, ex: a1
		int row = Integer.parseInt(s.substring(1)); // ler a linha recortando a string a partir da posi��o 1 e converter o resultado para Int.
		return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8."); // exception de entrada de dados
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " "); // Imprime a numera��o da linha
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]); // Imprime a pe�a.
			}
			System.out.println(); // quebra de linha
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) {
    	if (piece == null) {
            System.out.print("-");
        }
        else {
            if (piece.getColor() == Color.WHITE) { // se a pe�a for branca imprime a cor com o codigo branco.
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET); // se n�o utilizamos a cor amarela para as pe�as pretas.
            }
        }
        System.out.print(" ");
	}
}
