package com.houseAgent.calendar.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.houseAgent.calendar.domain.Calendar;


public interface CalendarRepository extends PagingAndSortingRepository<Calendar, Long>,JpaSpecificationExecutor<Calendar> {

}
