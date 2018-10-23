package com.houseAgent.calendar.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventDTO {
	
	private Long id;
	private String title;
	private Boolean allDay=false;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date startDate; 
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date endDate;  
	private Long calendarId;
	private String  description;
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getAllDay() {
		return allDay;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public Date getStartDate() {
		return startDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public Date getEndDate() {
		return endDate;
	}

	public Long getCalendarId() {
		return calendarId;
	}

	public String getDescription() {
		return description;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@SuppressWarnings({ "serial"})
	public static Specification<Event> getWhereClause(final EventDTO eventDTO) {
		return new Specification<Event>() {
			@Override
			public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (null!=eventDTO.getStartDate()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate").as(Date.class),
							eventDTO.getStartDate()));
				}
				if (null!=eventDTO.getEndDate()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate").as(Date.class),
							eventDTO.getEndDate()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
