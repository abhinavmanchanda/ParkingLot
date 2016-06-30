package com.obc.parkinglot;


import org.junit.Test;

// Ensures correctness of a ParkingLot
public class ParkingLotTest  {

	private ParkingLot lot1;
	private ParkingLot lot2;
	private Object carA;
	private Object carB;

	@Test
	public void testCanParkACarIntoALotWithSpace() throws Exception {
		
	}

}

class TestOwner implements ParkingLotOwner {

	public boolean wasNotified = false;
	
	public void notifyFull() {
		wasNotified = true;
	}

}
