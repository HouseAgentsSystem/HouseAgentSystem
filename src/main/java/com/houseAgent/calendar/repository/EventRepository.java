package com.houseAgent.calendar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.houseAgent.calendar.domain.Event;


@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long>,JpaSpecificationExecutor<Event>{
	
	@Query("from Event e where e.calendarId = ?1 and e.startDate > ?2 and e.endDate < ?3 and e.staffName = ?4")
	public List<Event> findByCalendarId(Long calendarId, Date startDate, Date endDate, String staffName);
	
}
