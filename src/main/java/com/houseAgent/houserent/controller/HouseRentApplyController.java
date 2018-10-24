package com.houseAgent.houserent.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.activiti.util.WorkflowVariable;
import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.common.web.SessionUtil;
import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentDTO;
import com.houseAgent.houserent.domain.ShowHouseRentDTO;
import com.houseAgent.houserent.service.IHouseRentActivitiService;
import com.houseAgent.houserent.service.IHouseRentService;


@RestController
@RequestMapping(value="/rentapply")
public class HouseRentApplyController {

	@Autowired
	private IHouseRentActivitiService houseRentActivitiService;
	
	@Autowired
	private IHouseRentService houseRentService;
	
	
	//改--重新申请
	@PutMapping(value="{id}")
	public @ResponseBody ExtAjaxResponse updateHouse(@PathVariable("id") Long id, @RequestBody HouseRentDTO houseRentDTO) {
		System.out.println("updateHouse");
		System.out.println(houseRentDTO);
		try {
			houseRentActivitiService.reApply(id, houseRentDTO);
			return new ExtAjaxResponse(true,"操作成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"操作失败!");
		}
	}
	//查看我的房源申请
	@GetMapping
	public Page<HouseRentActivitiDTO> findHouse(HttpSession session, ExtjsPageRequest pageRequest) {
		//分页返回
		System.out.println("findHouse");
		try {
			Long userId = SessionUtil.getUserId(session);
			return houseRentService.findByUserId(HouseRentActivitiDTO.getWhereClause(userId), pageRequest.getPageable());
		} catch (Exception e) {
			return null;
		}
	}
	//按钮：
	//下架
	//已售
	//取消申请
	
//	//改 == 重新申请
//	
//	public void update(@PathVariable("id") Long id,@RequestBody Leave leave) {
//		//....................
//		
//		houseRentActivitiService.reApply(id);
//	}
//	
//	public void save(@RequestParam(name="id") Long leaveId, HttpSession session) {
//		
//		//....................
//		String userId = SessionUtil.getUserName(session);
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("applyUserId", userId);
//		houseRentActivitiService.startWorkflow(userId,leaveId, variables);
//	}
	
	@RequestMapping(value = "/changeStates/{states}/{id}")
    public @ResponseBody ExtAjaxResponse changeStates(@PathVariable("states")String states, 
    		@PathVariable("id") Long id, HttpSession session) {
    	try {
    		Long userId = SessionUtil.getUserId(session);
    		houseRentActivitiService.changeStates(id, userId, states);
    		return new ExtAjaxResponse(true,"操作成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"操作失败!");
	    }
    }
	
	
	/**
	 * 启动流程
	 * @param leaveId	请假信息Id
	 * @param session	通过会话获取登录用户(请假人)
	 * @return
	 */
	@RequestMapping(value = "/start")
    public @ResponseBody ExtAjaxResponse start(@RequestParam(name="id") Long houseRentId,HttpSession session) {
    	try {
    		String userId = SessionUtil.getStaffId(session);
    		Map<String, Object> variables = new HashMap<String, Object>();
    		variables.put("applyUserId", userId);
    		houseRentActivitiService.startWorkflow(userId,houseRentId, variables);
    		return new ExtAjaxResponse(true,"操作成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"操作失败!");
	    }
    }
	
	/**
	 * 查询待处理流程任务
	 * @param pageable	分页对象
	 * @param session	通过会话获取登录用户(请假人)
	 * @return
	 */
	@RequestMapping(value = "/tasks")
    public @ResponseBody Page<HouseRentActivitiDTO> findTodoTasks(HttpSession session,ExtjsPageRequest pageable) {
		Page<HouseRentActivitiDTO> page = new PageImpl<HouseRentActivitiDTO>(new ArrayList<HouseRentActivitiDTO>(), pageable.getPageable(), 0);
    	try {
    		page = houseRentActivitiService.findTodoTasks(SessionUtil.getStaffId(session), pageable.getPageable());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    	return page;
    }
	
	/**
     * 签收任务
     */
    @RequestMapping(value = "claim/{id}")
    public @ResponseBody ExtAjaxResponse claim(@PathVariable("id") String taskId, HttpSession session) {
    	try{
    		houseRentActivitiService.claim(taskId, SessionUtil.getStaffId(session));
	    	return new ExtAjaxResponse(true,"任务签收成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"任务签收失败!");
	    }
    }
    
    /**
     * 完成任务
     * @param id
     * @return
     */
    @RequestMapping(value = "complete/{id}")
    public @ResponseBody ExtAjaxResponse complete(@PathVariable("id") String taskId, WorkflowVariable var) {
    	try{
    		Map<String, Object> variables = var.getVariableMap();
    		houseRentActivitiService.complete(taskId, variables);
	    	return new ExtAjaxResponse(true,"审批成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"审批失败!");
	    }
    }
}
