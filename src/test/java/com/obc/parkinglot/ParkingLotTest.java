package com.obc.parkinglot;

import junit.framework.TestCase;

// Ensures correctness of a ParkingLot
public class ParkingLotTest extends TestCase {

	private ParkingLot lot1;
	private ParkingLot lot2;
	private Object carA;
	private Object carB;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		lot1 = new ParkingLot(1);
		lot2 = new ParkingLot(2);

		carA = new Object();
		carB = new Object();
	}

	public void testCanParkACarIntoALotWithSpace() throws Exception {
		try {
			lot1.park(carA);
		} catch (CannotParkException unexpected) {
			fail("Should be able to park a car into a parking lot with space");
		}
	}

	public void testCannotParkACarIntoAFullLot() throws Exception {
		lot1.park(carA);
		try {
			lot1.park(carB);
			fail("Should not be able to park a car into a full lot");
		} catch (CannotParkException expected) {
		}
	}

	public void testCannotParkTheSameCarTwice() throws Exception {
		lot2.park(carA);
		try {
			lot2.park(carA);
			fail("Shouldn't be allowed to park the same car twice");
		} catch (CannotParkException expected) {
		}
	}

	public void testCanUnparkAParkedCar() throws Exception {
		Object token = lot1.park(carA);
		assertSame(carA, lot1.unpark(token));
	}

	public void testUnparksCarCorrespondingToToken() throws Exception {
		lot2.park(carA);
		Object token = lot2.park(carB);
		assertSame(carB, lot2.unpark(token));
	}

	public void testCannotUnparkAnAlreadyUnparkedCar() throws Exception {
		Object token = lot2.park(carA);
		lot2.unpark(token);
		try {
			lot2.unpark(token);
			fail("Shouldn't be able to unpark a car that is already unparked");
		} catch (CannotUnparkException expected) {
		}
	}

	public void testUnparkingACarFreesUpSpaceInAFullLot() throws Exception {
		Object token = lot2.park(carA);
		lot2.park(carB);
		lot2.unpark(token);
		try {
			Object car = new Object();
			lot2.park(car);
		} catch (CannotParkException unexpected) {
			fail("Should be able to park a car now, since it has space after unparking");
		}
	}

	
	
	public void testIsFull() throws Exception {
		lot2.park(carA);
		lot2.park(carB);
		assertTrue(lot2.isFull());
	}
	
	public void testOwnerIsNotifiedWhenLotIsFull() throws Exception {
		TestOwner owner = new TestOwner();
		ParkingLot lot2 = new ParkingLot(2, owner);
		lot2.park(carA);
		lot2.park(carB);
		assertTrue(owner.wasNotified);
	}

	public void testOwnerIsNotNotifiedOnEveryPark() throws Exception {
		TestOwner owner = new TestOwner();
		ParkingLot lot2 = new ParkingLot(2, owner);
		lot2.park(carA);
		assertFalse(owner.wasNotified);
	}
}

class TestOwner implements ParkingLotOwner {

	public boolean wasNotified = false;
	
	public void notifyFull() {
		wasNotified = true;
	}

}
