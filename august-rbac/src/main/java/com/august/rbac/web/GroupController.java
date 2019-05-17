package com.august.rbac.web;


import com.august.rbac.domain.Group;
import com.august.rbac.dto.GroupEditDTO;
import com.august.rbac.dto.GroupSearchDTO;
import com.august.rbac.dto.TreeNodeDTO;
import com.august.rbac.service.GroupService;
import com.august.website.utils.Resp;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('group_edit')")
    @ApiModelProperty(value = "编辑分组信息")
    @PostMapping(value = "/edit")
    public Resp<Group> saveOrUpdate(@RequestBody @Valid GroupEditDTO groupModel) {
        return groupService.saveOrUpdate(groupModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('group_delete')")
    @ApiModelProperty(value = "删除分组")
    @GetMapping(value = "/delete")
    public Resp delete(Long id) {
        return groupService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('group_getGroupTree')")
    @ApiOperation(value = "获取分组树", notes = "机构树")
    @GetMapping(value = "/getGroupTree")
    public Resp<List<TreeNodeDTO>> getGroupTreeModel() {
        return Resp.SUCCESS(groupService.getGroupTreeModel(GroupService.TOP_NODE));
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('group_fetch')")
    @ApiOperation(value = "获取分组树根据id", notes = "获取分组树根据id")
    @GetMapping(value = "/fetch")
    public Resp<GroupSearchDTO> fetch(Long id) {
        return groupService.findById(id);
    }
}
