package com.gs.controller;

import java.util.List;

import com.gs.constant.consist.WebAuthConst;
import com.gs.constant.enums.CodeEnum;
import com.gs.convert.UserConvert;
import com.gs.model.dto.UserDTO;
import com.gs.utils.R;
import com.gs.service.intf.UserService;

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

@Api(tags = "用户CRUD")
@RestController
@RequestMapping("api/user")
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserConvert userConvert;

    @ApiOperation(value = "新增用户")
    @PostMapping
    public R add(@RequestBody UserDTO userDTO) {

        if (userService.save(userConvert.toEntity(userDTO))) {
            return R.success();
        }
        return R.error(CodeEnum.IS_FAIL.getCode(), CodeEnum.IS_FAIL.getValue());
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public R update(@RequestBody UserDTO userDTO) {

        if (userService.saveOrUpdate(userConvert.toEntity(userDTO))) {
            return R.success();
        }
        return R.error(CodeEnum.IS_FAIL.getCode(), CodeEnum.IS_FAIL.getValue());
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping
    public R del(@RequestParam("ids") List<String> ids) {
        if (userService.removeByIds(ids)) {
            return R.success();
        }
        return R.error(CodeEnum.IS_FAIL.getCode(), CodeEnum.IS_FAIL.getValue());
    }

    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public R page(@RequestParam Boolean deleted,
                  @RequestParam(defaultValue = WebAuthConst.PAGE_NO) Integer pageNo,
                  @RequestParam(defaultValue = WebAuthConst.PAGE_SIZE) Integer pageSize) {
        return R.success(userService.page(deleted, pageNo, pageSize));
    }
}
