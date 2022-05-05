package com.gs.model.dto;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginDTO {

    @NotBlank(message = "账号不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
}