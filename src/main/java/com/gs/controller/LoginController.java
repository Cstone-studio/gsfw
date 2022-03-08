package com.gs.controller;

import java.util.HashMap;
import java.util.Map;

import com.gs.constant.enums.CodeEnum;
import com.gs.model.dto.UserDTO;
import com.gs.model.dto.UserLoginDTO;
import com.gs.model.entity.mybatis.db1.User;
import com.gs.service.intf.UserMybatisService;
import com.gs.third.jwt.JwtUser;
import com.gs.utils.JwtTokenUtil;
import com.gs.utils.R;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "用户登录相关")
@RestController
@RequestMapping("api")
@Validated
@AllArgsConstructor
public class LoginController {

    private final UserMybatisService userMybatisService;

    private final JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public R login(@Validated @RequestBody UserLoginDTO userLoginDTO) {

        User user = userMybatisService.login(userLoginDTO);

        if (null == user || user.getDeleted()) {
            return R.error(CodeEnum.IS_FAIL.getCode(), "账号不存在");
        }

        // 创建token
        String token = jwtTokenUtil.generateToken(new JwtUser(user));

        user.setToken(token);
        userMybatisService.loginSuccess(user);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", String.valueOf(user.getId()));

        return R.success(result);
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public R getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userMybatisService.findByUseName(userDetails.getUsername());

        if (null == userDTO) {
            return R.error(CodeEnum.IS_FAIL.getCode(), "账号不存在");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("user", userDTO);
        return R.success(result);
    }
}
