package com.capgemini.cabinvoicegenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.cabinvoicegenerator.model.Ride;

public class InvoiceGeneratorTest 
{
	InvoiceService invoiceService;
	
	@Before
	public void init() {
		invoiceService = new InvoiceService();
	}
	
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {	
		double distance = 1.0;
		int time = 10;
		double fare = invoiceService.calculateFare(distance,time);
		assertEquals(20,fare,0.0);
	}
	
	@Test
	public void givenDistanceAndTime_WhenTotalFareLessThan10_ShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 1;
		double fare = invoiceService.calculateFare(distance,time);
		assertEquals(5,fare,0.0);
	}
	
	@Test
	public void givenMultipleRidesShouldReturnRideSummary() {
		Ride[] rides = {
				new Ride(2.0,5),
				new Ride(0.1,1)
		};
		InvoiceSummary summary = invoiceService.calculateFare(rides);
		InvoiceSummary expectedSummary = new InvoiceSummary(2,30.0);
		assertEquals(expectedSummary,summary);
	}
	
	@Test
	public void givenUserIdShouldReturnTheInvoice() {
		String userId = "abc@123";
		Ride[] rides = {
				new Ride(2.0,5),
				new Ride(0.1,1)
		};
		invoiceService.addRides(userId, rides);
		InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
		InvoiceSummary checkSummary = new InvoiceSummary(2,30.0);
		assertEquals(summary,checkSummary);
	}
}
