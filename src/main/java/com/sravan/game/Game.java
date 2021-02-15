package com.sravan.game;

import com.sravan.game.util.Util;

public class Game {

	public static void main(String[] args) {

		Util util = new Util();
		
		Player[] players= util.createPlayers("player1", "player2");
		
		TicTacToe ticTacToe = new TicTacToe(players[0], players[1]);
		
		ticTacToe.play();
		
	}


}


