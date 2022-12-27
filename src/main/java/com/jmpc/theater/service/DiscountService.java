package com.jmpc.theater.service;

import com.jmpc.theater.pojo.Showing;

/**
 * Discount service interface - contains methods related to calculation of discount 
 * @author vinayakbhope
 *
 */
public interface DiscountService {
	
	public double getTicketPriceAfterDiscounts(Showing showing);

}
