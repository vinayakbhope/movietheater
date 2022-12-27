package com.jpmc.theater.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import com.jmpc.theater.pojo.Customer;
import com.jmpc.theater.pojo.Movie;
import com.jmpc.theater.pojo.Showing;
import com.jmpc.theater.pojo.Theater;

public class BaseTest {
	
	private Theater theater;
	
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		this.theater = new Theater("Theater 1", "1 Good Ave, Princeton, NJ - 08540");
		
		Movie spiderMan = new Movie("Spider-Man: No Way Home", "test description1", Duration.ofMinutes(90), true);
		Movie turningRed = new Movie("Turning Red", "test description2", Duration.ofMinutes(85), false);
		Movie theBatMan = new Movie("The Batman", "test description3", Duration.ofMinutes(95), false);
		
		Map<String, Showing> showings = new HashMap<String, Showing>();
		Showing showing1 = new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), 11);
		showings.put("1", showing1);
		Showing showing2 = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)), 12.5);
		showings.put("2", showing2);
		Showing showing3 = new Showing(theBatMan, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50)), 9);
		showings.put("3", showing3);
		
		Showing showing4 = new Showing(turningRed, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)), 11);
		showings.put("4", showing4);
		Showing showing5 = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)), 12.5);
		showings.put("5", showing5);
		Showing showing6 = new Showing(theBatMan, 6, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)), 9);
		showings.put("6", showing6);
		
		Showing showing7 = new Showing(turningRed, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)), 11);
		showings.put("7", showing7);
		Showing showing8 = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10)), 12.5);
		showings.put("8", showing8);
		Showing showing9 = new Showing(theBatMan, 9, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)), 9);
		showings.put("9", showing9);
		
		this.theater.setShowings(showings);
		
		this.customer = new Customer("John", "Doe", "john.doe@abc.com");
		
	}
	
	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
