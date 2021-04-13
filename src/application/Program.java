package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			try {
				UI.clearScreen(); // Limpa a tela a cada vez que retorna no While.
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc); // L� a posi��o de origem.
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source); // Imprime as posi��es possiveis a partir da posi��o de origem.
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc); // L� a posic��o de destino
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target); // Chamada movendo da origem para o destino.
		
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); // aguarda o usuario apertar enter
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); // aguarda o usuario apertar enter
			}
		}
	}
}
