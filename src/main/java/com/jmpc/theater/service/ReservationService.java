package com.jmpc.theater.service;

import com.jmpc.theater.pojo.Customer;
import com.jmpc.theater.pojo.Reservation;
import com.jmpc.theater.pojo.Theater;

/**
 * Reservation service interface. Contains methods related to reservation functionality
 * @author vinayakbhope
 *
 */
public interface ReservationService {

	public Reservation createReservation(Customer customer, int numSeats, Theater theater, String showId);
	
	public double calculateTicketFee(Reservation reservation);
}
