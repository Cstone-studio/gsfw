package com.gs.service.intf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.model.entity.mybatis.db1.User;
import com.gs.repository.db1.UserMybatisRepository;
import com.gs.third.jwt.JwtUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/**
 * Jwt业务类
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    private final UserMybatisRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName, username);
        User user = userRepository.selectOne(queryWrapper);

        if (null == user) {
            // throw new EntityNotFoundException(SysUser.class, "name", username);
            return null;
        } else {
            return new JwtUser(user);
        }
    }
}
