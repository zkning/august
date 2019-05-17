package com.august.rbac.web;


import com.august.rbac.domain.Group;
import com.august.rbac.dto.DictEditDTO;
import com.august.rbac.dto.DictFetchDTO;
import com.august.rbac.dto.DictSearchDTO;
import com.august.rbac.dto.TreeNodeDTO;
import com.august.rbac.service.DictService;
import com.august.website.utils.Pager;
import com.august.website.utils.Resp;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_edit')")
    @ApiModelProperty(value = "编辑")
    @PostMapping(value = "/edit")
    public Resp<Group> edit(@RequestBody @Valid DictEditDTO dictEditModel) {
        return dictService.edit(dictEditModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_delete')")
    @ApiModelProperty(value = "删除")
    @GetMapping(value = "/delete")
    public Resp delete(Long id) {
        return dictService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_fetch')")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/fetch")
    public Resp<DictFetchDTO> fetch(Long id) {
        return dictService.fetch(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_list')")
    @ApiOperation(value = "查询列表")
    @GetMapping(value = "/list")
    public Resp<Pager<DictFetchDTO>> list(DictSearchDTO dictSearchModel) {
        return Resp.SUCCESS(dictService.list(dictSearchModel));
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_tree')")
    @ApiOperation(value = "字典树")
    @GetMapping(value = "/tree")
    public Resp<List<TreeNodeDTO>> tree() {
        return Resp.SUCCESS(dictService.treeNodes());
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('dict_findByPValue')")
    @ApiOperation(value = "根据上级值查询所有子项")
    @GetMapping(value = "/findByPValue")
    public Resp<List<DictFetchDTO>> findByPValue(String value) {
        return Resp.SUCCESS(dictService.findByPValue(value));
    }
}
