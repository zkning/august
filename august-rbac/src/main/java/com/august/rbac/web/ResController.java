package com.august.rbac.web;

import com.august.rbac.domain.Res;
import com.august.rbac.dto.*;
import com.august.rbac.service.ResService;
import com.august.website.utils.Resp;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ningzuokun on 2018/3/22.
 */
@RestController
@RequestMapping("/resource")
public class ResController {

    @Autowired
    private ResService resourceService;


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_edit')")
    @ApiModelProperty(value = "编辑")
    @PostMapping(value = "/edit")
    public Resp<Res> edit(@RequestBody @Valid ResEditDTO resourceEditModel) {
        return resourceService.edit(resourceEditModel);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_getRoleResTree')")
    @ApiOperation(value = "获取分组树", notes = "机构树")
    @GetMapping(value = "/getRoleResTree")
    public Resp<List<TreeNodeDTO>> getRoleResTree(Long roleId) {
        return Resp.SUCCESS(resourceService.getRoleResTree(roleId));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_delete')")
    @ApiModelProperty(value = "删除")
    @GetMapping(value = "/delete")
    public Resp delete(Long id) {
        return resourceService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_fetch')")
    @ApiOperation(value = "获取角色根据id", notes = "获取角色根据id")
    @GetMapping(value = "/fetch")
    public Resp<ResFetchDTO> fetch(Long id) {
        return resourceService.fetch(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_saveRoleRes')")
    @ApiModelProperty(value = "关联角色资源")
    @PostMapping(value = "/saveRoleRes")
    public Resp saveRoleRes(@RequestBody @Valid SaveRoleResDTO saveRoleResModel) {
        return resourceService.saveRoleRes(saveRoleResModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_saveRoleUser')")
    @ApiModelProperty(value = "关联角色用户")
    @PostMapping(value = "/saveRoleUser")
    public Resp saveRoleUser(@RequestBody @Valid EditRoleUserDTO editRoleUserModel) {
        return resourceService.saveRoleUser(editRoleUserModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('resource_removeRoleUser')")
    @ApiModelProperty(value = "移除关联角色用户")
    @PostMapping(value = "/removeRoleUser")
    public Resp removeRoleUser(@RequestBody @Valid EditRoleUserDTO editRoleUserModel) {
        return resourceService.removeRoleUser(editRoleUserModel);
    }

}
