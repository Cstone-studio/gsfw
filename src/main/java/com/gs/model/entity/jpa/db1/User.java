package com.gs.model.entity.jpa.db1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gs.model.entity.jpa.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guozy
 * @date 2019-03-02
 */
@Entity
@Getter
@Setter
@ToString
@Table(name="user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 登陆token
     */
    @Column(name = "token")
    private String token;
}