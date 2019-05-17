package com.august.rbac.web;

import com.august.rbac.domain.Group;
import com.august.rbac.dto.RoleEditDTO;
import com.august.rbac.dto.RoleFetchDTO;
import com.august.rbac.dto.RoleSearchDTO;
import com.august.rbac.dto.TreeNodeDTO;
import com.august.rbac.service.RoleService;
import com.august.website.utils.Resp;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('role_edit')")
    @ApiModelProperty(value = "编辑角色信息")
    @PostMapping(value = "/edit")
    public Resp<Group> edit(@RequestBody @Valid RoleEditDTO roleEditModel) {
        return roleService.edit(roleEditModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('role_delete')")
    @ApiModelProperty(value = "删除")
    @GetMapping(value = "/delete")
    public Resp delete(Long id) {
        return roleService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('role_getRoleTree')")
    @ApiOperation(value = "获取角色树", notes = "角色树")
    @GetMapping(value = "/getRoleTree")
    public Resp<List<TreeNodeDTO>> roleTree() {
        return Resp.SUCCESS(roleService.TreeNodeModel());
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('role_fetch')")
    @ApiOperation(value = "获取角色根据id", notes = "获取角色根据id")
    @GetMapping(value = "/fetch")
    public Resp<RoleFetchDTO> fetch(Long id) {
        return roleService.fetch(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('role_list')")
    @ApiOperation(value = "查询列表")
    @GetMapping(value = "/list")
    public Resp<List<RoleFetchDTO>> list(RoleSearchDTO roleSearchModel) {
        return Resp.SUCCESS(roleService.list(roleSearchModel));
    }
}
