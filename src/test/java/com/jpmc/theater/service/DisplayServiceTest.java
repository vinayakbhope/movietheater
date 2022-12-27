package com.jpmc.theater.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmpc.theater.service.DisplayService;
import com.jmpc.theater.service.DisplayServiceImpl;

public class DisplayServiceTest extends BaseTest {
	
	private DisplayService displayService;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.displayService = new DisplayServiceImpl();
	}

	@Test
	public void testDisplayScheduleInPlainText() {
		Set<String> schedules = displayService.displayScheduleInPlainText(getTheater());
		assertEquals(schedules.size(), 9);
		assertTrue(schedules.contains("1: 2022-12-27T09:00 Turning Red (1 hour 25 minutes) $11.0") && 
				schedules.contains("5: 2022-12-27T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5") && 
				schedules.contains("6: 2022-12-27T17:50 The Batman (1 hour 35 minutes) $9.0"));
	}
	
	@Test
	public void testDisplayScheduleInJson() throws JsonProcessingException {
		String jsonStr = displayService.displayScheduleInJsonFormat(getTheater());
		assertTrue( jsonStr.contains("{\"sequenceOfTheDay\":3,\"showStartTime\":\"2022-12-27T12:50\",\"title\":\"The Batman\",\"runningTime\":\"(1 hour 35 minutes)\",\"ticketPrice\":9.0}") );
	}

}
