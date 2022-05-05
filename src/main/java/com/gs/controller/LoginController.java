package com.gs.controller;

import java.util.HashMap;
import java.util.Map;

import com.gs.constant.enums.CodeEnum;
import com.gs.convert.UserConvert;
import com.gs.model.dto.UserDTO;
import com.gs.model.dto.UserLoginDTO;
import com.gs.model.entity.jpa.db1.User;
import com.gs.repository.jpa.UserRepository;
import com.gs.service.intf.UserService;
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
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final UserConvert userConvert;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public R login(@Validated @RequestBody UserLoginDTO userLoginDTO) {

        User user = userService.login(userLoginDTO);

        if (null == user || user.getDeleted()) {
            return R.error(CodeEnum.IS_FAIL.getCode(), "用户名或密码错误");
        }

        // 创建token
        String token = jwtTokenUtil.generateToken(new JwtUser(user));

        user.setToken(token);
        userService.loginSuccess(user);

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", String.valueOf(user.getId()));

        return R.success(result);
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping(value = "/getUserInfo")
    public R getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userConvert.toDto(userRepository.findByUserName(userDetails.getUsername()));

        if (null == userDTO) {
            return R.error(CodeEnum.IS_FAIL.getCode(), "账号不存在");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("user", userDTO);
        return R.success(result);
    }
}
