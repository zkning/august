package com.august.rbac.service;


import com.august.rbac.consts.CacheableConsts;
import com.august.rbac.domain.Dict;
import com.august.rbac.dto.DictEditDTO;
import com.august.rbac.dto.DictFetchDTO;
import com.august.rbac.dto.DictSearchDTO;
import com.august.rbac.dto.TreeNodeDTO;
import com.august.rbac.mapper.DictMapper;
import com.august.rbac.utils.RecursiveTools;
import com.august.website.utils.Pager;
import com.august.website.utils.Resp;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ningzuokun
 * 数据字段服务
 */
@Service
public class DictService {
    private static final Long TOP_NODE = 0L;

    @Autowired
    DictMapper dictMapper;

    public Resp edit(DictEditDTO editModel) {
        Dict t = new Dict();
        Boolean isEdit = null != editModel.getId();
        if (isEdit) {
            t = dictMapper.selectById(editModel.getId());
        }
        t.setPid(null != editModel.getPid() ? editModel.getPid() : TOP_NODE);
        t.setSort(editModel.getSort());
        t.setText(editModel.getText());
        t.setValue(editModel.getValue());
        t.setVersion(editModel.getVersion());
        t.setId(editModel.getId());
        t.setRemark(editModel.getRemark());
        if (isEdit) {
            dictMapper.updateById(t);
        } else {
            dictMapper.insert(t);
        }
        return Resp.SUCCESS();
    }

    public Resp delete(Long id) {
        dictMapper.deleteById(id);
        return Resp.SUCCESS();
    }

    @Cacheable(value = CacheableConsts.CacheableName, key = "#root.method.name + #id", unless = "#result.code != 0")
    public Resp<DictFetchDTO> fetch(Long id) {
        Dict t = dictMapper.selectById(id);
        if (null == t) {
            return Resp.FAILURE("记录不存在");
        }
        DictFetchDTO d = new DictFetchDTO();
        d.setId(t.getId());
        d.setPid(t.getPid());
        d.setValue(t.getValue());
        d.setText(t.getText());
        d.setVersion(t.getVersion());
        d.setCreateTime(t.getCreateTime());
        d.setRemark(t.getRemark());
        d.setSort(t.getSort());
        return Resp.SUCCESS(d);
    }

    public List<DictFetchDTO> findByPValue(String value) {
        Dict pDict = dictMapper.findByValue(value);
        List<Dict> ds = dictMapper.findByPidOrderBySortDesc(pDict.getId());
        List<DictFetchDTO> items = Lists.newArrayList();
        ds.forEach(dict -> {
            DictFetchDTO dm = new DictFetchDTO();
            dm.setId(dict.getId());
            dm.setPid(dict.getPid());
            dm.setValue(dict.getValue());
            dm.setText(dict.getText());
            dm.setVersion(dict.getVersion());
            dm.setCreateTime(dict.getCreateTime());
            dm.setRemark(dict.getRemark());
            items.add(dm);
        });
        return items;
    }

    public Pager<DictFetchDTO> list(DictSearchDTO dsm) {
        Page p = new Page(dsm.getPageNo(), dsm.getPageSize());
        List<DictFetchDTO> list = dictMapper.list(p, dsm);

        // 分页响应
        Pager<DictFetchDTO> pager = new Pager();
        pager.setPageNo(p.getCurrent());
        pager.setPageSize(p.getSize());
        pager.setTotalElements(p.getTotal());
        pager.setContent(list);
        return pager;
    }

    public List<TreeNodeDTO> treeNodes() {
        List<Dict> entityList = dictMapper.findByPidOrderBySortDesc(TOP_NODE);
        if (CollectionUtils.isEmpty(entityList)) {
            return Lists.newArrayList();
        }
        List<TreeNodeDTO> treeNodeModels = Lists.newArrayList();
        entityList.forEach(group -> {
            treeNodeModels.add(TreeNodeDTO.builder()
                    .key(group.getId() + "")
                    .pid(group.getPid() + "")
                    .selectable(true)
                    .expanded(true)
                    .title(group.getText())
                    .build());
        });
        return RecursiveTools.forEachTreeItems(treeNodeModels, (TreeNodeDTO item) -> {
            List<Dict> entitys = dictMapper.findByPidOrderBySortDesc(Long.valueOf(item.getKey()));
            if (CollectionUtils.isEmpty(entitys)) {
                item.setLeaf(true);
                return null;
            }
            List<TreeNodeDTO> list = Lists.newArrayList();
            entitys.forEach(dict -> {
                list.add(TreeNodeDTO.builder()
                        .key(dict.getId() + "")
                        .selectable(true)
                        .title(dict.getText())
                        .pid(dict.getPid() + "")
                        .build());
            });
            item.setChildren(list);
            return item.getChildren();
        });
    }
}
