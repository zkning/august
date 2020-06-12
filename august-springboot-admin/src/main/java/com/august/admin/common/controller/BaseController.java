package com.august.admin.common.controller;

import com.august.admin.common.utils.ShiroUtils;
import com.august.admin.system.domain.UserDO;
import com.august.admin.common.utils.ShiroUtils;
import com.august.admin.system.domain.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}