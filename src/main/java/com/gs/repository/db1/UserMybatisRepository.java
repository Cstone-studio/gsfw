package com.gs.repository.db1;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.model.entity.mybatis.db1.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMybatisRepository extends BaseMapper<User> {

    /**
     * 以sql的方式检索事例(sql位于/resources/mapper/db1/UserMapper.xml)
     */
    List<User> test();
}
