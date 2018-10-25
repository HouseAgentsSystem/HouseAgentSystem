package com.houseAgent.calendar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.calendar.domain.Event;
import com.houseAgent.calendar.repository.EventRepository;


@Service
public class EventService implements IEventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public void save(Event event) {
		eventRepository.save(event);
	}

	@Override
	public void delete(long id) {
		eventRepository.deleteById(id);
	}
	
	@Override
	public List<Event> findByCalendarId(Long calendarId , Date startDate, Date endDate, String staffName) {
		System.out.println(startDate);
		System.out.println(endDate);
		return eventRepository.findByCalendarId(calendarId,startDate,endDate,staffName);
		
	}

	@Override
	public Event findOne(long id) {
		return eventRepository.findById(id).get();
	}

	
	
}
