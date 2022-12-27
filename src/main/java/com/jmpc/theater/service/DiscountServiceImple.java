package com.jmpc.theater.service;

import com.jmpc.theater.pojo.Showing;

/**
 * Discount service implementation -  contains methods to apply various discounts and choose one of them according to the rules
 * @author vinayakbhope
 *
 */
public class DiscountServiceImple implements DiscountService {

	/**
	 * Method to apply special discount
	 * @param showing
	 * @return
	 */
	private double getTickterPriceAfterSpecialMovieDiscount(Showing showing) {
		if(showing.getMovie().isSpecial()) {
			return showing.getTicketPrice() - (showing.getTicketPrice() * 0.2);
		}
		return showing.getTicketPrice();
	}

	/**
	 * Method to apply first show discount
	 * @param showing
	 * @return
	 */
	private double getTicketPriceAfterFirstShowDiscount(Showing showing) {
		if(showing.getSequenceOfTheDay() == 1) {
			return showing.getTicketPrice() - 3;
		}
		return showing.getTicketPrice();
	}

	/**
	 * Method to apply second show discount
	 * @param showing
	 * @return
	 */
	private double getTicketPriceAfterSecondShowDiscount(Showing showing) {
		if(showing.getSequenceOfTheDay() == 2) {
			return showing.getTicketPrice() - 2;
		}
		return showing.getTicketPrice();
	}
	
	/**
	 * Method to apply discount for movies between 11AM and 4PM
	 * @param showing
	 * @return
	 */
	private double getTicketPriceAfterShowTimingDiscount(Showing showing) {
		if(showing.getShowStartTime().getHour() >= 11 && showing.getShowStartTime().getHour() <= 16) {
			return showing.getTicketPrice() - (showing.getTicketPrice() * 0.25);
		}
		return showing.getTicketPrice();
	}
	
	/**
	 * Method to apply discount for movies on 7th day of any month
	 * @param showing
	 * @return
	 */
	private double getTicketPriceAfter7thDayDiscount(Showing showing) {
		if(showing.getShowStartTime().getDayOfMonth() == 7) {
			return showing.getTicketPrice() - 1;
		}
		return showing.getTicketPrice();
	}

	/**
	 * Method to apply all discount and choose the maximum one
	 */
	@Override
	public double getTicketPriceAfterDiscounts(Showing showing) {
		double ticketPrice = showing.getTicketPrice();
		
		// Apply discounts
		ticketPrice = Math.min(ticketPrice, getTickterPriceAfterSpecialMovieDiscount(showing));
		ticketPrice = Math.min(ticketPrice, getTicketPriceAfterFirstShowDiscount(showing));
		ticketPrice = Math.min(ticketPrice, getTicketPriceAfterSecondShowDiscount(showing));
		ticketPrice = Math.min(ticketPrice, getTicketPriceAfterShowTimingDiscount(showing));
		ticketPrice = Math.min(ticketPrice, getTicketPriceAfter7thDayDiscount(showing));
		
		return ticketPrice;
	}

}
