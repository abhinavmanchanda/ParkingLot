package com.obc.parkinglot;



import java.util.HashMap;
import java.util.Map;


// Understands a space to keep cars temporarily
public class ParkingLot {

	private int capacity;
	private Map<Object, Object> cars = new HashMap<Object, Object>();
	private ParkingLotOwner owner = null;

	public ParkingLot(int capacity) {
		this.capacity = capacity;
	}

	public ParkingLot(int capacity, ParkingLotOwner owner) {
		this.capacity = capacity;
		this.owner = owner;
		
	}

	public Object park(Object car) throws CannotParkException {
		if (isFull())
			throw CannotParkException.becauseLotIsFull(car, this);
		if (hasCar(car))
			throw CannotParkException.becauseCarIsPresentInLot(car, this);
		Object token = new Object();
		cars.put(token, car);
		if (owner != null && isFull()) owner.notifyFull();
		return token;
	}

	private boolean hasCar(Object car) {
		return cars.containsValue(car);
	}

	public boolean isFull() {
		return capacity == cars.size();
	}

	public Object unpark(Object token) throws CannotUnparkException {
		if (!hasCarFor(token)) 
			throw new CannotUnparkException(token, this);
		Object car = cars.get(token);
		cars.remove(token);
		return car;
	}

	private boolean hasCarFor(Object token) {
		return cars.containsKey(token);
	}
}
