package com.gs.service.intf;

import java.util.Map;

import com.gs.model.dto.UserDTO;

import org.springframework.data.domain.Pageable;

public interface UserService {

    /**
     * 分页查询
     * @param UserDTO dto 检索条件dto
     * @param Pageable pageable 分页对象
     * @return Object
     */
    Map<String, Object> page(UserDTO dto, Pageable pageable);

    /**
     * 根据id查找用户
     * @param Long id 主键id
     * @return UserDTO
     */
    UserDTO findById(Long id);

    /**
     * 创建用户
     * @param UserDTO dto 用户dto
     * @return UserDTO 创建成功后的dto
     */
    UserDTO create(UserDTO dto);

    /**
     * 更新用户
     * @param UserDTO dto 用户dto
     */
    void update(UserDTO dto);

    /**
     * 删除用户
     * @param Long id 用户id
     */
    void delete(Long id);
}
