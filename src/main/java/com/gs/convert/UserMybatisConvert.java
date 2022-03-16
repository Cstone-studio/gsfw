package com.gs.convert;

import com.gs.model.dto.UserDTO;
import com.gs.model.entity.mybatis.db1.User;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMybatisConvert extends EntityConvert<UserDTO, User> {
    
}
