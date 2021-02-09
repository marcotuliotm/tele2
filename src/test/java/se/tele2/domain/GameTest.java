package se.tele2.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	@DisplayName("userChoice -> Should be the door set!")
	void userChoice() {
		final var game = new Game();
		game.userChoice(1);
		assertEquals(1, game.getDoorUser().getDoorNumber());
	}

	@Test
	@DisplayName("userChoice -> Door not exist!")
	void userChoiceNoSuchElementException() {
		final var game = new Game();
		Throwable exception = assertThrows(NoSuchElementException.class, () -> game.userChoice(4));
		assertEquals(exception.getMessage(), "Door not exist!");
	}

	@Test
	@DisplayName("swapDoor -> Presenter needs to remove a door first!")
	void swapDoorNoSuchElementException() {
		final var game = new Game();
		Throwable exception = assertThrows(IllegalCallerException.class, game::swapDoor);
		assertEquals(exception.getMessage(), "Presenter needs to remove a door first!");
	}

	@Test
	@DisplayName("swapDoor -> Should be the door change")
	void swapDoor() {
		final var game = new Game();
		game.userChoice(1);
		game.getWrongDoor();
		game.swapDoor();
		assertNotEquals(1, game.getDoorUser().getDoorNumber());
	}

	@Test
	@DisplayName("WrongDoor -> The door has already been shown!")
	void getWrongDoorIllegalCallerExceptionAlreadyBeenShown() {
		final var game = new Game();
		game.userChoice(1);
		game.getWrongDoor();
		Throwable exception = assertThrows(IllegalCallerException.class, game::getWrongDoor);
		assertEquals(exception.getMessage(), "The door has already been shown!");
	}

	@Test
	@DisplayName("WrongDoor -> User needs to choose the port first!")
	void getWrongDoorIllegalCallerExceptionUserChooseFirst() {
		final var game = new Game();
		Throwable exception = assertThrows(IllegalCallerException.class, game::getWrongDoor);
		assertEquals(exception.getMessage(), "User needs to choose the port first!");
	}

	@Test
	@DisplayName("WrongDoor -> Get Wrong door!")
	void getWrongDoor() {
		final var game = new Game();
		game.userChoice(1);
		final var wrongDoor = game.getWrongDoor();
		assertFalse(wrongDoor.isWinner());
	}

	@Test
	@DisplayName("isUserWinner -> Presenter needs to remove a door first!")
	void isUserWinnerIllegalCallerException() {
		final var game = new Game();
		Throwable exception = assertThrows(IllegalCallerException.class, game::isUserWinner);
		assertEquals(exception.getMessage(), "Presenter needs to remove a door first!");
	}
}