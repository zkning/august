package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.ActivitiDO;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ActTaskService {
    List<ActivitiDO> listTodo(ActivitiDO act);

    void beforeComplete(String taskKey);

    void complete(String taskId, Map<String, Object> vars);

    /**
     * 完成任务时的操作，开发者自己实现接口
     * @param taskKey
     */
    void afterComplete(String taskKey);

    ProcessInstance startProcess(String procDefKey, String businessId, String title, Map<String, Object> vars);

    String getFormKey(String procDefId, String taskDefKey);

    InputStream tracePhoto(String processDefinitionId, String executionId);

    void setAssignee(String procesKey);

    void setAssigneeByTaskId(String taskId);

    /**
     * 根据任务id查询流转信息
     *
     * @param taskId
     * @return
     */
    List listHisTaskByTaskId(String taskId);

    /**
     * 获取业务ID
     * @param taskId
     * @return
     */
    String getBusinessKeyByTaskId(String taskId);

    /**
     * 获取task
     * @param taskId
     * @return
     */
    Task getTaskByTaskId(String taskId);
}
