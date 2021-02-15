package com.sravan.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.sravan.game.util.Util;

class TicTacToeTest {

	TicTacToe ticTacToe;

	Player player1;

	Player player2;

	Util util;

	@BeforeEach
	void init() {
		player1 = new Player("player1", 'X');
		player2 = new Player("player2", 'O');
		ticTacToe = new TicTacToe(player1, player2);
		util = new Util();
	}

//	@Test
//	void testPlayerTurn() {
//		char[][] board = { { '-', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };
//
//		Scanner scanner = new Scanner(System.in);
//
//		ticTacToe.playerTurn(board, scanner, player1);
//
//	}

	@RepeatedTest(5)
	void testPlayerCreation() {

		Player[] players = util.createPlayers("player1", "player2");

		boolean symbol = players[0].getSymbol() != players[1].getSymbol();
		boolean name = players[0].getName() != players[1].getName();

		assertEquals(true, symbol);
		assertEquals(true, name);
	}

	@Test
	void arrayOutOfBoundTest() throws Exception {

		char[][] board = { { '-', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };
		int[] input = { 3, 1 };

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> ticTacToe.placeMove(board, input, 'O'));
	}

	@Test
	void testValidMove() {

		char[][] board = { { 'O', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };

		assertEquals(true, ticTacToe.isValidMove(board, new int[] { 1, 1 }));

		assertEquals(false, ticTacToe.isValidMove(board, new int[] { 0, 0 }));

		assertEquals(false, ticTacToe.isValidMove(board, new int[] { 0, 3 }));

	}

	@Test
	void testRowWin() {
		char[][] rowWin = { { 'O', 'O', 'O' }, { '-', '-', '-' }, { '-', '-', '-' } };

		assertEquals(true, ticTacToe.hasWon(rowWin, player2.getSymbol()));

	}

	@Test
	void testColumnWin() {
		char[][] columnWin = { { 'O', '_', '_' }, { 'O', '-', '-' }, { 'O', '-', '-' } };

		assertEquals(true, ticTacToe.hasWon(columnWin, player2.getSymbol()));

	}

	@Test
	void testNoResult() {
		char[][] noResult = { { 'X', 'O', 'O' }, { '-', '-', '-' }, { '-', '-', '-' } };

		assertEquals(false, ticTacToe.hasWon(noResult, player1.getSymbol()));
	}

	@Test
	void testCrossWin() {
		char[][] crossWin = { { 'O', '_', '_' }, { '-', 'O', '-' }, { 'O', '-', 'O' } };

		assertEquals(true, ticTacToe.hasWon(crossWin, player2.getSymbol()));
	}

	@Test
	void testGameOver() {

		char[][] board = { { 'O', '-', '-' }, { '-', '-', '-' }, { '-', '-', '-' } };

		assertEquals(false, ticTacToe.isGameOver(board, player1));

	}

	@Test
	void testTie() {
		char[][] filledBoard = { { 'O', 'X', 'O' }, { 'X', 'O', 'O' }, { 'X', 'O', 'X' } };

		assertEquals(true, ticTacToe.isGameOver(filledBoard, player1));
	}

}
