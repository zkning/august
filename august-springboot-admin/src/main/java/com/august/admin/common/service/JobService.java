package com.august.admin.common.service;

import com.august.admin.common.domain.TaskScheduleDO;
import com.august.admin.common.domain.TaskScheduleDO;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
public interface JobService {
	
	TaskScheduleDO get(Long id);
	
	List<TaskScheduleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskScheduleDO taskScheduleJob);
	
	int update(TaskScheduleDO taskScheduleJob);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
