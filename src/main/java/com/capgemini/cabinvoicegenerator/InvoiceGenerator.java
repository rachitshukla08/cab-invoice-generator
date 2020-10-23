package com.capgemini.cabinvoicegenerator;

import com.capgemini.cabinvoicegenerator.model.Ride;

public class InvoiceGenerator {
	private static final int COST_PER_TIME = 1;
	private static final double COST_PER_KM = 10;
	private static final double MINIMUM_FARE = 5;
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
	 * @return total fare
	 */
	public double calculateFare(Ride[] rides) {
		double totalFare=0;
		for(Ride ride : rides) {
			totalFare+=calculateFare(ride.getDistance(),ride.getTime());
		}
		return totalFare;
	}
}
