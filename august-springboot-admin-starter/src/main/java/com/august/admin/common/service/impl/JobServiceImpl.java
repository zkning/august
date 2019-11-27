package com.august.admin.common.service.impl;

import com.august.admin.common.dao.TaskScheduleDao;
import com.august.admin.common.config.Constant;
import com.august.admin.common.domain.JobDO;
import com.august.admin.common.domain.TaskScheduleDO;
import com.august.admin.common.quartz.utils.QuartzManager;
import com.august.admin.common.service.JobService;
import com.august.admin.common.utils.ScheduleJobUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private TaskScheduleDao taskScheduleJobMapper;

	@Autowired
	QuartzManager quartzManager;

	@Override
	public TaskScheduleDO get(Long id) {
		return taskScheduleJobMapper.get(id);
	}

	@Override
	public List<TaskScheduleDO> list(Map<String, Object> map) {
		return taskScheduleJobMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return taskScheduleJobMapper.count(map);
	}

	@Override
	public int save(TaskScheduleDO taskScheduleJob) {
		return taskScheduleJobMapper.save(taskScheduleJob);
	}

	@Override
	public int update(TaskScheduleDO taskScheduleJob) {
		return taskScheduleJobMapper.update(taskScheduleJob);
	}

	@Override
	public int remove(Long id) {
		try {
			TaskScheduleDO scheduleJob = get(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			return taskScheduleJobMapper.remove(id);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int batchRemove(Long[] ids) {
		for (Long id : ids) {
			try {
				TaskScheduleDO scheduleJob = get(id);
				quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			} catch (SchedulerException e) {
				e.printStackTrace();
				return 0;
			}
		}
		return taskScheduleJobMapper.batchRemove(ids);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		List<TaskScheduleDO> jobList = taskScheduleJobMapper.list(new HashMap<String, Object>(16));
		for (TaskScheduleDO scheduleJob : jobList) {
			if ("1".equals(scheduleJob.getJobStatus())) {
				JobDO job = ScheduleJobUtils.entityToData(scheduleJob);
				quartzManager.addJob(job);
			}

		}
	}

	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		TaskScheduleDO scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(JobDO.STATUS_NOT_RUNNING);
		} else {
			if (!Constant.STATUS_RUNNING_START.equals(cmd)) {
			} else {
                scheduleJob.setJobStatus(JobDO.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
		}
		update(scheduleJob);
	}

	@Override
	public void updateCron(Long jobId) throws SchedulerException {
		TaskScheduleDO scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (JobDO.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		update(scheduleJob);
	}

}
