package com.houseAgent.calendar.service;

import java.util.List;

import com.houseAgent.calendar.domain.Calendar;


public interface ICalendarService {
	
	public void save(Calendar calendar);
	
	public List<Calendar> findById(Long id);
	
	public List<Calendar> findAll();
}
