package com.jmpc.theater.service;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmpc.theater.pojo.Theater;

public interface DisplayService {
	
	public Set<String> displayScheduleInPlainText(Theater theater);
	
	public String displayScheduleInJsonFormat(Theater theater) throws JsonProcessingException;

}
