package com.bootdo.common.dao;

import com.bootdo.common.domain.TaskScheduleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface TaskScheduleDao {

	TaskScheduleDO get(Long id);
	
	List<TaskScheduleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TaskScheduleDO task);
	
	int update(TaskScheduleDO task);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
