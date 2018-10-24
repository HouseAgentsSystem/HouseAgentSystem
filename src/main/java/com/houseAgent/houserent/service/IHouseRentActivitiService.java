package com.houseAgent.houserent.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentDTO;


public interface IHouseRentActivitiService {
	
	//流程业务
	//1.启动流程
	public void startWorkflow(String userId,Long houseRentId, Map<String, Object> variables);//findOne(Long id);
	//2.查询流程任务
	public Page<HouseRentActivitiDTO> findTodoTasks(String userId, Pageable pageable);
	//3.签收流程任务
	public void claim(String taskId,String userId);
	//4.完成流程任务
	public void complete(String taskId, Map<String, Object> variables);
	public HouseRent findOne(Long id);
	public void changeStates(Long id, Long userId, String states);
	public void setReApply(Long id, Boolean reApply);
	public void reApply(Long id, HouseRentDTO houseRentDTO);
}
