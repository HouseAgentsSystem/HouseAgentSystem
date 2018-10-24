package com.houseAgent.activiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houseAgent.activiti.domain.WorkflowDTO;



@Service
@Transactional
public class WorkflowService implements IWorkflowService {

	/**
	 * 流程服务
	 */
	@Autowired
	private IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	
	/*----------------------------------------------流程业务--------------------------------------------*/
	@Override
	public ProcessInstance startWorkflow(String authenticatedUserId, String processDefinitionKey, String businessKey,
			Map<String, Object> variables) {
		ProcessInstance processInstance = null;	//1.声明流程实例
		try {
			identityService.setAuthenticatedUserId(authenticatedUserId);//2.授权
			//3.启动流程实例:processDefinitionKey, businessKey, variables
			processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			identityService.setAuthenticatedUserId(null);	//4.取消授权
		}
		return processInstance;
	}

	@Override
	public List<WorkflowDTO> findTodoTasks(String userId) {
		List<WorkflowDTO> results=null;
        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        List<Task> tasks = taskQuery.list();
        if(null!=tasks) {
        	results= new ArrayList<WorkflowDTO>();
        	 // 根据流程的业务ID查询实体并关联
            for (Task task : tasks) {
                String processInstanceId = task.getProcessInstanceId();
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
                String businessKey = processInstance.getBusinessKey();
                if (businessKey == null) {
                    continue;
                }
            	WorkflowDTO dto = new WorkflowDTO();
            	dto.setBusinessKey(processInstance.getBusinessKey());
            	
            	dto.setTaskId(task.getId());
            	dto.setTaskName(task.getName());
            	dto.setTaskCreateTime(task.getCreateTime());
            	dto.setAssignee(task.getAssignee());
            	dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
            	dto.setSuspended(processInstance.isSuspended());

            	ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
            	dto.setProcessDefinitionId(processDefinition.getId());
            	dto.setVersion(processDefinition.getVersion());
            	
                results.add(dto);
            }
        }
		return results;
	}
	 /**
	  * 签收流程任务
     *
     * @param taskId 任务ID
     * @param userId 签收人用户ID
     * @return
     */
	@Override
	public void claim(String taskId, String userId) {
		taskService.claim(taskId, userId);
	}

	 /**
	  * 完成流程任务
     *
     * @param taskId 任务ID
     * @param variables 流程变量
     * @return
     */
	@Override
	public void complete(String taskId, Map<String, Object> variables) {
		taskService.complete(taskId, variables);
	}
	/**
	 * 查询流程定义对象
    *
    * @param processDefinitionId 流程定义ID
    * @return
    */
	protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}
}
