package com.houseAgent.activiti.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WorkflowDTO {
	/*任务*/
    private String taskId;
    private String taskName;
    private Date   taskCreateTime;
    private String assignee;
    private String taskDefinitionKey;
    /*流程实例*/
    private String processInstanceId;
    /*流程图定义*/
    private String processDefinitionId;
    private boolean suspended;
    private int version;
    
    private String businessKey;

	public String getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public int getVersion() {
		return version;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
    
}
