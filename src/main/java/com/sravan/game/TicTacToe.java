package com.sravan.game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	private Player p1;
	private Player p2;
	private Player p;

	public TicTacToe(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void play() {

		Scanner scanner = new Scanner(System.in);
		char[][] board = { { '-', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };

		System.out.println("Welcome! Here is your board:");

		printBoard(board);

		int beginWith = new Random().nextInt(2);

		if (beginWith == 1) {
			p = this.p1;
		} else {
			p = this.p2;
		}

		while (true) {

			playerTurn(board, scanner, p);

			if (isGameOver(board, p)) {
				break;
			}
			printBoard(board);
			if (p == p1) {
				p = p2;
			} else {
				p = p1;
			}
		}

		scanner.close();

	}

	public boolean isGameOver(char[][] board, Player p) {

		if (hasWon(board, p.getSymbol())) {
			System.out.println(p.getName() + " WINS!");
			return true;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '-')
					return false;
			}
		}

		printBoard(board);

		System.out.println("Game Over ...!!! it'a a Tie :(");

		return true;
	}

	public boolean hasWon(char[][] board, char symbol) {

		if (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol
				|| board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol
				|| board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol
				|| board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol ||

				board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol
				|| board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol
				|| board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol ||

				board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol
				|| board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
			return true;
		}

		return false;
	}

	public void playerTurn(char[][] board, Scanner scanner, Player p) {

		int[] input = { -1, -1 };

		System.out.println(p.getName() + " (" + p.getSymbol() + ") would you like to move");

		while (true) {

			try {
				for (int i = 0; i < input.length; i++) {
					input[i] = scanner.nextInt();
				}

				if (isValidMove(board, input)) {
					break;
				} else {
					System.out.println(p.getName() + " (" + p.getSymbol() + ") would you like to move");
				}
			} catch (InputMismatchException e) {
				System.out.println("Input MisMatch must be integer ..!! "+p.getName() + " (" + p.getSymbol() + ") would you like to move");
				scanner.next();
			} catch (Exception e) {
				System.out.println("Invalid Move ..!! "+p.getName() + " (" + p.getSymbol() + ") would you like to move");
				scanner.next();
			}

		}

		placeMove(board, input, p.getSymbol());
	}

	public void placeMove(char[][] board, int[] input, char symbol) throws ArrayIndexOutOfBoundsException{

			int x = input[0];
			int y = input[1];

			board[x][y] = symbol;
	}

	public boolean isValidMove(char[][] board, int[] input) {

		int x = input[0];
		int y = input[1];

		if (x < 0 || y < 0 || x >= 3 || y >= 3) {
			System.out.println("Invalid move...!!! values must be between 0 & 2");
			return false;
		}
		
		if(board[x][y] != '-') {
			System.out.println("the move has been taken already... ");
		}

		return board[x][y] == '-';
	}

	private void printBoard(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				String line1 = j == board.length - 1 ? board[i][j] + "" : board[i][j] + ",";
				System.out.print(line1);
			}
			System.out.println();
		}
	}

}
