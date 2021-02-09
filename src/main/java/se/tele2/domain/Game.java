package se.tele2.domain;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Game {
	public static final int MAX_DOORS = 3;
	private static final String DOOR_NOT_EXIST = "Door not exist!";
	private static final String THE_DOOR_HAS_ALREADY_BEEN_SHOWN = "The door has already been shown!";
	private static final String USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST = "User needs to choose the port first!";
	private static final String PRESENTER_NEEDS_TO_REMOVE_A_DOOR_FIRST = "Presenter needs to remove a door first!";
	private final Door doorWinner;
	private Door doorUser;
	private final Set<Door> doors;

	public Game() {
		doors = new HashSet<>();
		var random = new Random();
		doorWinner = new Door(random.nextInt(MAX_DOORS), true);
		doors.add(doorWinner);
		Door door;
		do {
			door = new Door(random.nextInt(MAX_DOORS), false);
			doors.add(door);
		} while (doors.size() < MAX_DOORS);
	}

	public void userChoice(int doorNumber) {
		doorUser = doors.stream()
				.filter(door -> door.getDoorNumber() == doorNumber)
				.findFirst().orElseThrow(() -> new NoSuchElementException(DOOR_NOT_EXIST));
	}

	public void swapDoor() {
		validateRemoveDoor();
		doorUser = doors.stream()
				.filter(door -> door != doorUser)
				.findFirst().orElseThrow(() -> new NoSuchElementException(DOOR_NOT_EXIST));
	}

	public Door getWrongDoor() {
		if (Objects.isNull(doorUser)) {
			throw new IllegalCallerException(USER_NEEDS_TO_CHOOSE_THE_PORT_FIRST);
		}
		if (doors.size() < MAX_DOORS) {
			throw new IllegalCallerException(THE_DOOR_HAS_ALREADY_BEEN_SHOWN);
		}
	   var wrongDoor =	doors.stream()
				.filter(door -> !door.isWinner() && door != doorUser)
				.findFirst().orElseThrow(NoSuchElementException::new);
		doors.remove(wrongDoor);
		return wrongDoor;
	}

	public boolean isUserWinner() {
		validateRemoveDoor();
		return doorWinner == doorUser;
	}

	public Door getDoorUser() {
		return doorUser;
	}

	private void validateRemoveDoor() {
		if (doors.size() > 2) {
			throw new IllegalCallerException(PRESENTER_NEEDS_TO_REMOVE_A_DOOR_FIRST);
		}
	}
}
