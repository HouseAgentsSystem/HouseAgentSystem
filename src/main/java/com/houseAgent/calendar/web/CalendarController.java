package com.houseAgent.calendar.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.calendar.domain.Calendar;
import com.houseAgent.calendar.domain.Event;
import com.houseAgent.calendar.service.ICalendarService;
import com.houseAgent.calendar.service.IEventService;
import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtResultJson;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.staff.domain.Staff;

@RestController
@RequestMapping("/calendar")
public class CalendarController 
{
	@Autowired
	private ICalendarService calendarService;
	@Autowired
	private IEventService eventService;
	
	@PostMapping("/addEvents")
    public ExtAjaxResponse saveEvents(HttpSession session, Event event) {
    	try {
//    		String userName = SessionUtil.getUserName(session);
    		String staffName = SessionUtil.getStaffId(session);
    		event.setStaffName(staffName);
    		System.out.println("addevents!");
    		System.out.println(event);
    		eventService.save(event);
    		return new ExtAjaxResponse(true,"添加日程成功！");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"添加日程失败！！！");
	    }
    }
	
	//查找日历类型
	@RequestMapping("/findCalendars")
	public @ResponseBody ExtResultJson<Calendar> findCalendar()
	{
		List<Calendar>  clist = new ArrayList<Calendar>();
		
		Calendar c1 = new Calendar();
		c1.setId(1L);
		c1.setTitle("Work");
		c1.setDescription("");
		c1.setColor("");
		c1.setAssignedColor("#F44336");
		c1.setHidden(false);
		c1.setEditable(true);
	
		Calendar c2 = new Calendar();
		c2.setId(2L);
		c2.setTitle("Personal");
		c2.setDescription("");
		c2.setColor("");
		c2.setAssignedColor("#40E0D0");
		c2.setHidden(false);
		c2.setEditable(true);
		
		clist.add(c1);
		clist.add(c2);
		
		//clist = calendarService.findAll();
		return new ExtResultJson<Calendar>(clist);
		
	}
	
	//按日历类型的id和时间区间查找对应的活动集合
	/**
	 *  calendar: Calendar类的Id
		startDate:开始时间,为UTC格式:2017-11-09T00:00:00.000Z
		endDate:结束时间:2018-03-11T00:00:00.000Z
	 *  
	 *  calendar:2
		startDate:2017-11-09T00:00:00.000Z
		endDate:2018-03-11T00:00:00.000Z
	 * @return
	 */
	
	@RequestMapping("/findEvents")
	public @ResponseBody ExtResultJson<Event> findEvents(
			Long calendar, HttpSession session, 
			@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date startDate,
			@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date endDate
			)
	{
		String staffName = SessionUtil.getStaffId(session);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(staffName);
		ExtResultJson<Event> json = new ExtResultJson<Event>(new ArrayList<Event>());
		List<Event> events = eventService.findByCalendarId(calendar, startDate, endDate,staffName);
		System.out.println(events);
		json.setLists(events);
		
//		System.out.println(calendar);
//		System.out.println(startDate);
//		System.out.println(endDate);
		return json;
	}
	
	@PostMapping("/deleteEvents")
	public ExtAjaxResponse deleteEvents(@RequestParam("id") long id) {
		try {
			System.out.println(id);
			eventService.delete(id);
			return new ExtAjaxResponse (true,"成功删除！");
		} catch (Exception e) {
			return new ExtAjaxResponse (true,"删除失败！");
		}
	}
	
	@PostMapping("/editEvents")
	public ExtAjaxResponse UpdateEvent(@RequestParam("id") Long id , Event dto) {
		try {
			Event event = eventService.findOne(id);
			BeanUtils.copyProperties(dto, event);//使用自定义的BeanUtils
			System.out.println(event);
			eventService.save(event);
			return new ExtAjaxResponse(true,"修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"修改失败！！！");
		}
	}
}