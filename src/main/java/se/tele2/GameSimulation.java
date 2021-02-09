package se.tele2;

import java.util.Random;

import se.tele2.domain.Game;

public class GameSimulation {

	private static final int MATCHES = 1000000;
	private static final String NOT_CHANGING_THE_DOOR_MADE_HIM_WIN_D_OF_D_MATCHES = "Not changing the door made him win %d of %d matches!\n";
	private static final String CHANGING_THE_DOOR_MADE_HIM_WIN_D_OF_D_MATCHES = "Changing the door made him win %d of %d matches!\n";

	public static void main(String[] args) {
		int wins = 0;
		var random = new Random();

		for (int i = 0; i < MATCHES; i++) {
			var game = new Game();
			game.userChoice(random.nextInt(Game.MAX_DOORS));
			game.getWrongDoor();
			if (game.isUserWinner()) {
				wins++;
			}
		}

		System.out.printf(NOT_CHANGING_THE_DOOR_MADE_HIM_WIN_D_OF_D_MATCHES, wins, MATCHES);

		wins = 0;

		for (int i = 0; i < MATCHES; i++) {
			var game = new Game();
			game.userChoice(random.nextInt(Game.MAX_DOORS));
			game.getWrongDoor();
			game.swapDoor();
			if (game.isUserWinner()) {
				wins++;
			}
		}

		System.out.printf(CHANGING_THE_DOOR_MADE_HIM_WIN_D_OF_D_MATCHES, wins, MATCHES);
	}
}
