package com.houseAgent.houserent.listener;

import java.util.Date;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.service.IHouseRentService;
import com.houseAgent.houserent.util.RentApplyStates;

/**
 * 管理员审核处理器
 */
@Component
@Transactional
public class AfterAdminAuditProcessor implements TaskListener  {

	private static final long serialVersionUID = 1L;

	@Autowired
    private IHouseRentService houseRentService;

    @Autowired
    private RuntimeService runtimeService;
    
	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("AfterAdminAuditProcessor:notify");
		String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HouseRent houseRent = houseRentService.findOne(new Long(processInstance.getBusinessKey()));
        if(delegateTask.getVariable("adminPass").toString() =="true") {
        	// 租房审核通过
        	System.out.println("adminPass==true");
        	houseRent.setState(RentApplyStates.RENTING);
        	houseRent.setPublishTime(new Date());
//            leave.setLeaveType((String) delegateTask.getVariable("leaveType"));
//            leave.setStartTime((Date) delegateTask.getVariable("startTime"));
//            leave.setEndTime((Date) delegateTask.getVariable("endTime"));
//            leave.setReason((String) delegateTask.getVariable("reason"));
        }else {
        	System.out.println("adminPass==false");
        	houseRent.setBackReason(delegateTask.getVariable("adminBackReason").toString());
        	houseRent.setState(RentApplyStates.REFUSE);
        }
	}

}
