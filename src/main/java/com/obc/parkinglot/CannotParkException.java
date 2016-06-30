package com.obc.parkinglot;
// Understands the reason for not being able to park a car
public class CannotParkException extends Exception {

	private CannotParkException(String reason) {
		super(reason);
	}

	public static CannotParkException becauseCarIsPresentInLot(Object car,
			ParkingLot parkingLot) {
		return new CannotParkException("Car " + car + " is already present in "
				+ parkingLot);
	}

	public static CannotParkException becauseLotIsFull(Object car,
			ParkingLot parkingLot) {
		return new CannotParkException("Cannot park the car " + car
				+ " because " + parkingLot + " is full");
	}

}
