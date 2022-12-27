package com.jmpc.theater.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.jmpc.theater.pojo.Customer;
import com.jmpc.theater.pojo.Movie;
import com.jmpc.theater.pojo.Reservation;
import com.jmpc.theater.pojo.Showing;
import com.jmpc.theater.pojo.Theater;

/**
 * Reservation service implementation.
 * @author vinayakbhope
 *
 */
public class ReservationServiceImpl implements ReservationService {
	
	private static DiscountService discountService = new DiscountServiceImple();
	
	private static final DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Method to create a reservation
	 */
	@Override
	public Reservation createReservation(Customer customer, int numSeats, Theater theater, String showId) {
		return new Reservation(customer, numSeats, theater, showId);
	}

	/**
	 * Method to calculate ticket fees after applying discounts
	 */
	@Override
	public double calculateTicketFee(Reservation reservation) {
		
		Theater theater = reservation.getTheater();
		int numSeats = reservation.getNumSeats();
		String showId = reservation.getShowId();
		
		if(!theater.getShowings().containsKey(showId)) {
			throw new IllegalStateException("not able to find any showing for given show Id:" + showId);
		}
		
		Showing showing = theater.getShowings().get(showId);
		Movie movie = showing.getMovie();
		
		double oneTicketFee =  discountService.getTicketPriceAfterDiscounts(showing);
		
		return new BigDecimal(oneTicketFee * numSeats).setScale(2,RoundingMode.DOWN).doubleValue();
	}

}
