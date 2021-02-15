package com.sravan.game.util;

import java.util.Random;

import com.sravan.game.Player;

public class Util {

	public Player[] createPlayers(String player1, String player2) {

		boolean swap = new Random().nextInt(2) == 1;

		Player p1 = new Player(swap ? player2 : player1, 'X');
		Player p2 = new Player(swap ? player1 : player2, 'O');

		return new Player[] { p1, p2 };
	}

}
