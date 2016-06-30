package com.obc.parkinglot;
// Understands the reason for not being able to unpark a car
public class CannotUnparkException extends Exception {

	public CannotUnparkException(Object token, ParkingLot parkingLot) {
		super("The car corresponding to the token " + token + " was not found in the lot " + parkingLot);
	}

}
