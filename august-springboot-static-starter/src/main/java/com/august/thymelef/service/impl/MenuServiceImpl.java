package com.august.thymelef.service.impl;

import com.august.thymelef.domain.Menu;
import com.august.thymelef.mapper.MenuMapper;
import com.august.thymelef.service.IMenuService;
import com.august.website.utils.Resp;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/11/11.
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
    public Resp saveOrUpdate(Menu menu) {
        if (null != menu.getId()) {
            baseMapper.updateById(menu);
        } else {
            baseMapper.insert(menu);
        }
        return Resp.SUCCESS();
    }

    @Override
    public Resp getMenus(Long userId) {
        return null;
    }
}
