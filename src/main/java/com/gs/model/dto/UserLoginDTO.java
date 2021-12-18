package com.gs.model.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserLoginDTO {

    @NotNull(message = "账号不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String password;
}