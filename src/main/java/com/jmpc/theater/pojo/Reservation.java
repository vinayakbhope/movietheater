package com.jmpc.theater.pojo;

import java.util.Objects;

/**
 * Representation of a reservation with fields Customer, number of seats, Theater, and showId
 * @author vinayakbhope
 *
 */
public class Reservation {
	
	private Customer customer;
	private int numSeats;
	private Theater theater;
	private String showId;
	
	public Reservation(Customer customer, int numSeats, Theater theater, String showId) {
		super();
		this.customer = customer;
		this.numSeats = numSeats;
		this.theater = theater;
		this.showId = showId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, numSeats, showId, theater);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(customer, other.customer) && numSeats == other.numSeats
				&& Objects.equals(showId, other.showId) && Objects.equals(theater, other.theater);
	}

}
