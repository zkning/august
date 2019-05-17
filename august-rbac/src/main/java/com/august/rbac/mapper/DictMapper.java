package com.august.rbac.mapper;

import com.august.rbac.domain.Dict;
import com.august.rbac.dto.DictFetchDTO;
import com.august.rbac.dto.DictSearchDTO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    List<DictFetchDTO> list(Page page, @Param("params") DictSearchDTO dictSearchModel);

    List<Dict> findByPidOrderBySortDesc(@Param("pid") Long pid);

    Dict findByValue(@Param("value") String value);
}
