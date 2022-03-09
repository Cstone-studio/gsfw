package com.gs.controller;

import com.gs.model.dto.UserDTO;
import com.gs.model.dto.UserQueryDTO;
import com.gs.repository.jpa.HospitalRepository;
import com.gs.utils.R;
import com.gs.service.intf.UserService;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "用户CRUD(Jpa版)")
@RestController
@RequestMapping("api/user")
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final HospitalRepository hospitalRepository;

    @ApiOperation(value = "新增用户")
    @PostMapping
    public R add(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return R.success();
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public R update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return R.success();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping
    public R del(@RequestParam("id") Long id) {
        userService.delete(id);
        return R.success();
    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public R page(UserQueryDTO userQueryDTO , Pageable pageable) {
        return R.success(userService.page(userQueryDTO, pageable));
    }

    @ApiOperation(value = "多表关联查询demo")
    @GetMapping("/hospital")
    public R hospital() {
        return R.success(hospitalRepository.findAll());
    }
}
