package com.capgemini.cabinvoicegenerator;

import com.capgemini.cabinvoicegenerator.model.Ride;
import com.capgemini.cabinvoicegenerator.model.RideRepository;

public class InvoiceService {
	private RideRepository rideRepository;
	
	public InvoiceService() {
		this.rideRepository = new RideRepository();
	}
	/**
	 * @param distance
	 * @param time
	 * @return total fare
	 */
	public double calculateFare(Ride ride) {
		return Math.max(ride.rideCategory.minFare,ride.getDistance() * ride.rideCategory.costPerKm + ride.getTime() * ride.rideCategory.costPerTime);
	}
	
	/**
	 * @param rides
	 * @return invoice summary
	 */
	public InvoiceSummary calculateFare(Ride[] rides) {
		double totalFare=0;
		for(Ride ride : rides) {
			totalFare+=calculateFare(ride);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
	
	
	
	/**
	 * @param userId
	 * @param ride
	 */
	public void addRides(String userId,Ride[] ride) {
		rideRepository.addRide(userId, ride);
	}

	/**
	 * @param userId
	 * @return InvoiceSummary
	 */
	public InvoiceSummary getInvoiceSummary(String userId) {
		return this.calculateFare(rideRepository.getRides(userId));
	}

}
