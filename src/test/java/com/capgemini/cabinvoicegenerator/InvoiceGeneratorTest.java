package com.capgemini.cabinvoicegenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.cabinvoicegenerator.model.Ride;
import com.capgemini.cabinvoicegenerator.model.RideCategories;

public class InvoiceGeneratorTest 
{
	InvoiceService invoiceService;
	
	@Before
	public void init() {
		invoiceService = new InvoiceService();
	}
	
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {	
		Ride ride = new Ride(RideCategories.NORMAL_RIDE, 1.0, 10);
		double fare = invoiceService.calculateFare(ride);
		assertEquals(20,fare,0.0);
	}
	
	@Test
	public void givenDistanceAndTime_WhenTotalFareLessThan10_ShouldReturnMinimumFare() {
		Ride ride = new Ride(RideCategories.NORMAL_RIDE, 0.1, 1);
		double fare = invoiceService.calculateFare(ride);
		assertEquals(5,fare,0.0);
	}
	
	@Test
	public void givenMultipleRidesShouldReturnRideSummary() {
		Ride[] rides = {
				new Ride(RideCategories.NORMAL_RIDE,2.0,5),
				new Ride(RideCategories.NORMAL_RIDE,0.1,1)
		};
		InvoiceSummary summary = invoiceService.calculateFare(rides);
		InvoiceSummary expectedSummary = new InvoiceSummary(2,30.0);
		assertEquals(expectedSummary,summary);
	}
	
	@Test
	public void givenUserIdShouldReturnTheInvoice() {
		String userId = "abc@123";
		Ride[] rides = {
				new Ride(RideCategories.NORMAL_RIDE,2.0,5),
				new Ride(RideCategories.NORMAL_RIDE,0.1,1)
		};
		invoiceService.addRides(userId, rides);
		InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
		InvoiceSummary checkSummary = new InvoiceSummary(2,30.0);
		assertEquals(summary,checkSummary);
	}
	
	@Test
	public void givenUserIdWithPremiumRideShouldReturnTheInvoice() {
		String userId = "pqr@123";
		Ride[] rides = {
				new Ride(RideCategories.NORMAL_RIDE,2.0,5),
				new Ride(RideCategories.NORMAL_RIDE,0.1,1),
				new Ride(RideCategories.PREMIUM_RIDE,0.1,1)
		};
		invoiceService.addRides(userId, rides);
		InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
		InvoiceSummary checkSummary = new InvoiceSummary(3,50.0);
		assertEquals(summary,checkSummary);
	}
}
