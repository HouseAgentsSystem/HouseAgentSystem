package com.houseAgent.houserent.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.houseAgent.activiti.domain.WorkflowDTO;
import com.houseAgent.common.beans.BeanUtils;
import com.houseAgent.staff.domain.Staff;
import com.houseAgent.store.domain.Store;
import com.houseAgent.store.domain.StoreQueryDTO;

/**
 * 
 * @author Administrator
 * 针对HouseRent在审核流程下的Dto
 */
public class HouseRentActivitiDTO extends HouseRentBaseDTO {
	private Long userId;//启动流程的用户ID, 客户外键
	/**------------流程数据--------------**/
//  流程实例Id：用于关联流程引擎相关数据,没有启动流程之前为""
    private String processInstanceId;
    /*任务*/
    private String assignee;
    private String taskId;
    private Date   taskCreateTime;
    private String taskDefinitionKey;
    private String backReason;	// 不通过理由
    
	public static void entityToDto(HouseRent entity, WorkflowDTO workflow, HouseRentActivitiDTO dto) {
		HouseRentBaseDTO.entityToDto(entity, dto);
		dto.userId = entity.getUser().getId();
		BeanUtils.copyProperties(workflow, dto);
	}
	public static void dtoToEntity(HouseRentActivitiDTO dto, HouseRent entity) {
		HouseRentBaseDTO.dtoToEntity(dto, entity);
	}
	public static Specification<HouseRent> getWhereClause(Long userId)
    {
		return new Specification<HouseRent>() {
			public Predicate toPredicate(Root<HouseRent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 if(userId!=null) {
					 predicate.add(cb.equal(root.get("user").get("id").as(Long.class), userId));
//					 predicate.add(cb.equal(root.get("state").as(String.class), ""));
				 }
				 //2.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
      
    }
	public Long getUserId() {
		return userId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public String getAssignee() {
		return assignee;
	}
	public String getTaskId() {
		return taskId;
	}
	public Date getTaskCreateTime() {
		return taskCreateTime;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public void setTaskCreateTime(Date taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	@Override
	public String toString() {
		return "HouseRentActivitiDTO [userId=" + userId + ", processInstanceId=" + processInstanceId + ", assignee="
				+ assignee + ", taskId=" + taskId + ", taskCreateTime=" + taskCreateTime + ", taskDefinitionKey="
				+ taskDefinitionKey + ", backReason=" + backReason + "]";
	}
	
}
