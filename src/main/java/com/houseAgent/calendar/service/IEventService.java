package com.houseAgent.calendar.service;

import java.util.Date;
import java.util.List;

import com.houseAgent.calendar.domain.Event;


public interface IEventService {
	
	public void save(Event event);
	
	public void delete(long id);
	
	public List<Event> findByCalendarId(Long calendarId,Date startDate, Date endDate);
	public Event findOne(long id);
}
