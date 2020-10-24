package com.capgemini.cabinvoicegenerator;

import com.capgemini.cabinvoicegenerator.model.Ride;
import com.capgemini.cabinvoicegenerator.model.RideRepository;

public class InvoiceService {
	private static final int COST_PER_TIME = 1;
	private static final double COST_PER_KM = 10;
	private static final double MINIMUM_FARE = 5;
	private RideRepository rideRepository;
	
	public InvoiceService() {
		this.rideRepository = new RideRepository();
	}
	/**
	 * @param distance
	 * @param time
	 * @return total fare
	 */
	public double calculateFare(double distance, int time) {
		return Math.max(MINIMUM_FARE,distance * COST_PER_KM + time * COST_PER_TIME);
	}
	
	/**
	 * @param rides
	 * @return invoice summary
	 */
	public InvoiceSummary calculateFare(Ride[] rides) {
		double totalFare=0;
		for(Ride ride : rides) {
			totalFare+=calculateFare(ride.getDistance(),ride.getTime());
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
	
	
	
	public void addRides(String userId,Ride[] ride) {
		rideRepository.addRide(userId, ride);
	}

	public InvoiceSummary getInvoiceSummary(String userId) {
		return this.calculateFare(rideRepository.getRides(userId));
	}

}
