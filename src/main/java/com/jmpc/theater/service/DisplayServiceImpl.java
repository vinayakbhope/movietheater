package com.jmpc.theater.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jmpc.theater.pojo.Showing;
import com.jmpc.theater.pojo.Theater;
import com.jmpc.theater.serializer.ShowingSerializer;
import com.jmpc.theater.util.CustomStringUtils;

public class DisplayServiceImpl implements DisplayService {
	
	private CustomStringUtils utils = new CustomStringUtils();

	@Override
	public Set<String> displayScheduleInPlainText(Theater theater) {
		Map<String, Showing> showings = theater.getShowings();
		Showing s;
		Set<String> schedules = new HashSet<String>();
		
		for(String showId: showings.keySet()) {
			s = showings.get(showId);
			String printStr = s.getSequenceOfTheDay() + ": " +
								s.getShowStartTime() + " " + 
								s.getMovie().getTitle() + " " +
								utils.humanReadableFormat(s.getMovie().getRunningTime()) + " $" + 
								s.getTicketPrice();
			schedules.add(printStr);
			System.out.println(printStr);
		}
        System.out.println("===================================================");
        return schedules;
    }
	
	@Override
	public String displayScheduleInJsonFormat(Theater theater) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Showing.class, new ShowingSerializer());
		mapper.registerModule(module);
		
		ArrayList<Showing> listOfValues = new ArrayList<Showing>(theater.getShowings().values());
		String serialized = mapper.writeValueAsString(listOfValues);
		System.out.println(serialized);
		return serialized;
	}

}
