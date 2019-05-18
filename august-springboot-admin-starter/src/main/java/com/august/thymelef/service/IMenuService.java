package com.august.thymelef.service;

import com.august.thymelef.domain.Menu;
import com.august.website.utils.Resp;
import com.baomidou.mybatisplus.service.IService;

public interface IMenuService extends IService<Menu> {
    public static final Long TOP_NODE = 0L;

    Resp saveOrUpdate(Menu menu);

    Resp getMenus(Long userId);
}
