package com.august.admin.common.service;

import java.util.List;

import com.august.admin.common.domain.PageDO;
import org.springframework.stereotype.Service;

import com.august.admin.common.domain.LogDO;
import com.august.admin.common.domain.PageDO;
import com.august.admin.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
