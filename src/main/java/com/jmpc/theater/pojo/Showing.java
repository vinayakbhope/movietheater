package com.jmpc.theater.pojo;

import java.time.LocalDateTime;

/**
 * Representation of showing of a movie with fields as Movie, sequence of the day, show start time, and ticket price
 * @author vinayakbhope
 *
 */
public class Showing {
	
	private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;
    private double ticketPrice;
    
    
	public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime, double ticketPrice) {
		super();
		this.movie = movie;
		this.sequenceOfTheDay = sequenceOfTheDay;
		this.showStartTime = showStartTime;
		this.ticketPrice = ticketPrice;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getSequenceOfTheDay() {
		return sequenceOfTheDay;
	}

	public void setSequenceOfTheDay(int sequenceOfTheDay) {
		this.sequenceOfTheDay = sequenceOfTheDay;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
