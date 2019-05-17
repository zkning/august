package com.august.rbac.web;

import com.august.rbac.domain.UserInfo;
import com.august.rbac.dto.*;
import com.august.rbac.service.ResService;
import com.august.rbac.service.UserService;
import com.august.rbac.utils.SessionContextHolder;
import com.august.website.utils.Pager;
import com.august.website.utils.Resp;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@RestController
@RequestMapping("/rbacuser")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    ResService resService;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_edit')")
    @ApiModelProperty(value = "编辑用户")
    @PostMapping(value = "/edit")
    public Resp<UserInfo> edit(@RequestBody @Valid UserInfoEditDTO rbacUserInfoReqModel) {
        return userService.edit(rbacUserInfoReqModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_updateInfo')")
    @ApiModelProperty(value = "客户信息更新")
    @PostMapping(value = "/updateInfo")
    public Resp updateInfo(@RequestBody @Valid UserInfoEditDTO rbacUserInfoReqModel) {
        return userService.editInfo(rbacUserInfoReqModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_updatePwd')")
    @ApiModelProperty(value = "修改密码")
    @PostMapping(value = "/updatePwd")
    public Resp updatePwd(@RequestBody @Valid UpdatePwdDTO updatePwdModel) {
        return userService.updatePwd(updatePwdModel);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_delete')")
    @ApiModelProperty(value = "删除用户")
    @GetMapping(value = "/delete")
    public Resp delete(Long id) {
        return userService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_list')")
    @ApiModelProperty(value = "查询所有的用户(包括子分组)列表")
    @GetMapping(value = "/list")
    public Resp<Pager<UserInfoFetchDTO>> list(@Valid UserInfoSearchDTO rbacUserInfoQueryModel) {
        Pager<UserInfoFetchDTO> pager = userService.list(rbacUserInfoQueryModel);
        return Resp.SUCCESS(pager);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_fetch')")
    @ApiOperation(value = "获取用户信息根据id", notes = "获取用户信息")
    @GetMapping(value = "/fetch")
    public Resp<UserInfoFetchDTO> fetch(Long id) {
        return userService.fetch(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasAuthority('rbacuser_getCustomInfo')")
    @ApiOperation(value = "当前客户资料", notes = "当前客户资料")
    @GetMapping(value = "/getCustomInfo")
    public Resp<UserInfoFetchDTO> getCustomInfo() {
        return userService.getCustomInfo();
    }


    @ApiOperation(value = "用户解锁")
    @PostMapping(value = "/unlock")
    public Resp unlock(@RequestBody @Valid UnLockDTO unLockModel) {
        return userService
                .findByUserNameAndPassword(SessionContextHolder.getPrincipal().getUsername(),
                        unLockModel.getPassword());
    }
}