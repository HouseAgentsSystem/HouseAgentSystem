package com.houseAgent.houserent.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.houseAgent.activiti.domain.WorkflowDTO;
import com.houseAgent.activiti.service.IWorkflowService;
import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.domain.HouseRentActivitiDTO;
import com.houseAgent.houserent.domain.HouseRentDTO;
import com.houseAgent.houserent.repository.HouseRentRepository;
import com.houseAgent.houserent.util.RentApplyStates;

@Service
public class HouseRentActivitiService implements IHouseRentActivitiService {

	@Autowired 
	private HouseRentRepository houseRentRepository;
	
	@Autowired 
	private IWorkflowService workflowService;
	
	@Autowired 
	private TaskService taskService;
	
	@Override
	public HouseRent findOne(Long id) {
		return houseRentRepository.findById(id).get();
	}
	
	@Override
	public void startWorkflow(String userId, Long houseRentId, Map<String, Object> variables) {
		//1.声明流程实例
		ProcessInstance processInstance = null;
		//2.获取创建好的租房申请实例
		HouseRent houseRent = houseRentRepository.findById(houseRentId).get();
		if(houseRent!=null){
			try {
				processInstance = workflowService.startWorkflow(userId, "rentapply", houseRent.getId().toString(), variables);
				houseRent.setState(RentApplyStates.APPROVAL);
				houseRent.setProcessInstanceId(processInstance.getId());
				houseRentRepository.save(houseRent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Page<HouseRentActivitiDTO> findTodoTasks(String userId, Pageable pageable) {
		List<HouseRentActivitiDTO> results = null;
		List<WorkflowDTO> workflowLists = workflowService.findTodoTasks(userId);
        // 根据流程的业务ID查询实体并关联
		if(null!=workflowLists) {
			results = new ArrayList<HouseRentActivitiDTO>();
			for (WorkflowDTO workflow : workflowLists) {
	        	Long businessKey = new Long(workflow.getBusinessKey());
	            if (workflow.getBusinessKey() == null) {
	                continue;
	            }
	            HouseRent houseRent = houseRentRepository.findById(businessKey).get();
	            if(houseRent!=null){
	            	HouseRentActivitiDTO houseRentActivitiDTO = new HouseRentActivitiDTO();
	            	HouseRentActivitiDTO.entityToDto(houseRent, workflow, houseRentActivitiDTO);
	            	results.add(houseRentActivitiDTO);
	            }
	        }
		}
		return new PageImpl<HouseRentActivitiDTO> (results, pageable, null!=results?results.size():0);
	}

	@Override
	public void claim(String taskId, String userId) {
		workflowService.claim(taskId, userId);
	}

	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		workflowService.complete(taskId, variables);
	}

	/**
	 * 在更新完之后将更新reApply为true写入工作流variables
	 */
	@Override
	public void setReApply(Long id, Boolean reApply) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reApply", reApply);
		HouseRent houseRent = this.findOne(id);
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(id.toString()).active().singleResult();
		this.claim(task.getId(), houseRent.getUser().getId().toString());
		this.complete(task.getId(), map);
	}
	
	@Override
	public void changeStates(Long id, Long userId, String newStates) {
		System.out.println("changeStates");
		HouseRent houseRent = houseRentRepository.findById(id).get();
		
		if(houseRent!=null && houseRent.getUser().getId()==userId && newStates!=null) {
			System.out.println("gogogo");
			RentApplyStates state = houseRent.getState();
			if(newStates.equals("rented") && state.equals(RentApplyStates.RENTING)) {
				System.out.println("rented");
				houseRent.setState(RentApplyStates.RENTED);
			}else if(newStates.equals("down") && (state.equals(RentApplyStates.RENTED) || state.equals(RentApplyStates.RENTING))) {
				System.out.println("down");
				houseRent.setState(RentApplyStates.DOWN);
			}else if(newStates.equals("cancel") && state.equals(RentApplyStates.REFUSE)) {
				System.out.println("cancel");
				houseRent.setState(RentApplyStates.CANCEL);
				this.setReApply(id, false);
			}
			
			houseRentRepository.save(houseRent);
		}
	}

	@Override
	public void reApply(Long id, HouseRentDTO houseRentDTO) {
		//dto to entity
		HouseRent houseRent = houseRentRepository.findById(id).get();
		HouseRentDTO.dtoToEntity(houseRentDTO, houseRent);
		houseRentRepository.save(houseRent);
		
		this.setReApply(id, true);
	}
	
}
