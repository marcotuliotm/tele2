package se.tele2.domain;

import java.util.Objects;

public class Door {
	private final int doorNumber;
	private final boolean winner;

	public Door(int doorNumber, boolean winner) {
		this.doorNumber = doorNumber;
		this.winner = winner;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Door)) {
			return false;
		}
		final Door door = (Door) object;
		return getDoorNumber() == door.getDoorNumber();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDoorNumber());
	}

	public int getDoorNumber() {
		return doorNumber;
	}

	public boolean isWinner() {
		return winner;
	}
}
