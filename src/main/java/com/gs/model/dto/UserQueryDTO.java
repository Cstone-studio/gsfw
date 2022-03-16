package com.gs.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserQueryDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;
}
