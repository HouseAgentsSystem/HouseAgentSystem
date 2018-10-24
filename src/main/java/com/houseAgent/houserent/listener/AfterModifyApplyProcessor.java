package com.houseAgent.houserent.listener;


import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.houserent.domain.HouseRent;
import com.houseAgent.houserent.service.IHouseRentActivitiService;
import com.houseAgent.houserent.util.RentApplyStates;

/**
 * 调整请假内容处理器
 */
@Component
@Transactional
public class AfterModifyApplyProcessor implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IHouseRentActivitiService houseRentActivitiService;

    @Autowired
    private RuntimeService runtimeService;
    
    @Override
    public void notify(DelegateTask delegateTask) {
    	System.out.println("AfterModifyApplyProcessor:notify");
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HouseRent houseRent = houseRentActivitiService.findOne(new Long(processInstance.getBusinessKey()));
        if(delegateTask.getVariable("reApply").toString() =="true") {
        	System.out.println("reApply==true");
        	houseRent.setState(RentApplyStates.APPROVAL);
//        	BeanUtils.copyProperties(delegateTask.getVariables(),houseRent);
//        	leave.setLeaveType((String) delegateTask.getVariable("leaveType"));
//            leave.setStartTime((Date) delegateTask.getVariable("startTime"));
//            leave.setEndTime((Date) delegateTask.getVariable("endTime"));
//            leave.setReason((String) delegateTask.getVariable("reason"));
        }else {
        	System.out.println("reApply==false");
        	houseRent.setState(RentApplyStates.CANCEL);
        }
    }
}
