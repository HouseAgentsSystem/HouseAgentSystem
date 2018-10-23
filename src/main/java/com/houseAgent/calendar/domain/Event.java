package com.houseAgent.calendar.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_event")
public class Event {
	private Long id;
	private String title;
	private Boolean allDay=false;
//	@DateTimeFormat(iso=ISO.DATE_TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date startDate; //2018-01-05T00:00:00.000Z,//UTC
//	@DateTimeFormat(iso=ISO.DATE_TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date endDate;  //2018-01-06T00:00:00.000Z,
	private Long calendarId;
	private String  description;
	
	private String staffName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	
	public Boolean getAllDay() {
		return allDay;
	}
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale = "zh", timezone = "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public Date getStartDate() {
		return startDate;
	}
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale = "zh", timezone = "GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public Date getEndDate() {
		return endDate;
	}
	public Long getCalendarId() {
		return calendarId;
	}
	public String getStaffName() {
		return staffName;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", allDay=" + allDay + ", startDate=" + startDate + ", endDate="
				+ endDate + ", calendarId=" + calendarId + ", description=" + description + "]";
	}
     
}