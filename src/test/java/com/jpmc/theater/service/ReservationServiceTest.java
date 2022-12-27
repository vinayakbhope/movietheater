package com.jpmc.theater.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jmpc.theater.pojo.Reservation;
import com.jmpc.theater.service.ReservationService;
import com.jmpc.theater.service.ReservationServiceImpl;

public class ReservationServiceTest extends BaseTest {
	
	ReservationService reservationService;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.reservationService = new ReservationServiceImpl();
	}
	
	@Test
	public void testCreateReservation() {
		Reservation validationReservation = new Reservation(getCustomer(), 1, getTheater(), "2");
		Reservation createdReservation = reservationService.createReservation(getCustomer(), 1, getTheater(), "2");
		assertEquals(validationReservation, createdReservation);
		assertEquals(createdReservation.getCustomer().getEmail(), "john.doe@abc.com");
		assertEquals(createdReservation.getCustomer().getLastName(), "Doe");
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCalculateTicketFeeException() {
		Reservation invalidReservation = new Reservation(getCustomer(), 1, getTheater(), "12");
		reservationService.calculateTicketFee(invalidReservation);
		exceptionRule.expectMessage("not able to find any showing for given show Id:12");
	}
	
	/**
	 * Test Details: number of seats = 1, 20% discount, second show rule, and movie between 11 AM and 4 PM rule apply. 
	 * movie between 11 AM and 4 PM rule apply wins - 25%
	 */
	@Test
	public void testCalculateTicketFee20PercentDiscount_1() {
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "2");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 9.37d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 3, 20% discount, second show rule, and movie between 11 AM and 4 PM rule apply. 
	 * movie between 11 AM and 4 PM rule apply wins - 25%
	 */
	@Test
	public void testCalculateTicketFee20PercentDiscount_2() {
		Reservation reservation = new Reservation(getCustomer(), 3, getTheater(), "2");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 28.12d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, 20% discount, second show rule, and movie between 11 AM and 4 PM rule apply. 
	 * movie between 11 AM and 4 PM rule apply and second show rule both give same discount - 25%
	 */
	@Test
	public void testCalculateTicketFee20PercentDiscount_3() {
		getTheater().getShowings().get("2").setTicketPrice(8);
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "2");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 6.0d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, 20% discount applies.
	 */
	@Test
	public void testCalculateTicketFee20PercentDiscount_4() {
		getTheater().getShowings().get("6").getMovie().setSpecial(true);
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "6");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 7.2d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, First show rule applies
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_1() {
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 8.0d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 4, First show rule applies
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_2() {
		Reservation reservation = new Reservation(getCustomer(), 4, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 32.0d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, First show rule and 20% discount applies. First show rule wins
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_3() {
		getTheater().getShowings().get("1").getMovie().setSpecial(true);
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 8.0d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, First show rule and 20% discount applies. 20% discount wins
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_4() {
		getTheater().getShowings().get("1").setTicketPrice(16);
		getTheater().getShowings().get("1").getMovie().setSpecial(true);
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 12.8d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 2, First show rule and 20% discount applies. First show rule wins
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_5() {
		getTheater().getShowings().get("1").getMovie().setSpecial(true);
		Reservation reservation = new Reservation(getCustomer(), 2, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 16.0d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 3, First show rule and 20% discount applies. 20% discount wins
	 */
	@Test
	public void testCalculateTicketFeeFirstShowRule_6() {
		getTheater().getShowings().get("1").setTicketPrice(16);
		getTheater().getShowings().get("1").getMovie().setSpecial(true);
		Reservation reservation = new Reservation(getCustomer(), 3, getTheater(), "1");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 38.4d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, no discount applies
	 */
	@Test
	public void testCalculateTicketFeeNoDiscount() {
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "6");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 9.0d, 0d);
	}
	
	
	/**
	 * Test Details: number of seats = 1, 25% discount applies
	 */
	@Test
	public void testCalculateTicketFee25PercentDiscount() {
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "4");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 8.25d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 1, $1 discount applies
	 */
	@Test
	public void testCalculateTicketFee1DollarDiscount_1() {
		getTheater().getShowings().get("9").setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(23, 0)));
		Reservation reservation = new Reservation(getCustomer(), 1, getTheater(), "9");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 8d, 0d);
	}
	
	/**
	 * Test Details: number of seats = 5, $1 discount applies
	 */
	@Test
	public void testCalculateTicketFee1DollarDiscount_2() {
		getTheater().getShowings().get("9").setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 12, 7), LocalTime.of(23, 0)));
		Reservation reservation = new Reservation(getCustomer(), 5, getTheater(), "9");
		double ticketFee = reservationService.calculateTicketFee(reservation);
		assertEquals(ticketFee, 40d, 0d);
	}

}
