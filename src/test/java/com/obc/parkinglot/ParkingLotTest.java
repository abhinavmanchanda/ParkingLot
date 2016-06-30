package com.obc.parkinglot;


import org.junit.Test;

import static org.junit.Assert.fail;

// Ensures correctness of a ParkingLot
public class ParkingLotTest  {

	private ParkingLot lot1;
	private ParkingLot lot2;
	private Object carA;
	private Object carB;

	@Test
	public void testCanParkACarIntoALotWithSpace() throws Exception {
		try {
			lot1.park(carA);
		} catch (CannotParkException unexpected) {
			fail("Should be able to park a car into a parking lot with space");
		}
	}

}

class TestOwner implements ParkingLotOwner {

	public boolean wasNotified = false;
	
	public void notifyFull() {
		wasNotified = true;
	}

}
