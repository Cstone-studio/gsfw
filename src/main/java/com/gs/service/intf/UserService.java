package com.gs.service.intf;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.model.dto.UserDTO;
import com.gs.model.dto.UserLoginDTO;
import com.gs.model.entity.db1.User;

public interface UserService extends IService<User> {

    User login(UserLoginDTO userLoginDTO);

    /**
     * 登录成功后保存token,用来检验重复登录
     * @param User 用户新信息
     */
    void loginSuccess(User user);

    UserDTO findByUseName(String userName);

    /**
     * 分页查询用户
     * @param Boolean deleted 检索条件1
     * @param Integer pageNo 当前页码
     * @param Integer pageSize 每页数据量
     */
    IPage<User> page(Boolean deleted, Integer pageNo, Integer pageSize);

    /**
     * 以sql的方式检索事例
     */
    List<User> sqlSelect();
}
