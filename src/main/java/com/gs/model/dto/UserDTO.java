package com.gs.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.gs.annotation.IsEmailExist;
import com.gs.annotation.IsPhone;
import com.gs.annotation.IsPhoneExist;
import com.gs.annotation.PasswordEqual;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@PasswordEqual(message = "两次输入的密码不一致")
public class UserDTO implements Serializable {

    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "姓名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotNull(message = "确认密码不能为空")
    private String confirmPassword;

    /**
     * 邮箱
     */
    @NotNull(message = "账号不能为空")
    @IsEmailExist
    private String email;

    /**
     * 手机号
     */
    @NotNull
    @IsPhone
    @IsPhoneExist
    private String mobile;
}
