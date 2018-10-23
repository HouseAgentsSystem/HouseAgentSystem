package com.houseAgent.calendar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseAgent.calendar.domain.Calendar;
import com.houseAgent.calendar.repository.CalendarRepository;


@Service
public class CalendarService implements ICalendarService{

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Override
	public void save(Calendar calendar) {
		calendarRepository.save(calendar);
	}

	@Override
	public List<Calendar> findById(Long id) {
		Calendar c = calendarRepository.findById(id).get();
		List<Calendar>  clist = new ArrayList<Calendar>();
		clist.add(c);
		return clist;
	}

	public List<Calendar> findAll() {
		List<Calendar>  clist = (List<Calendar>) calendarRepository.findAll();
		return clist;
	}
}
