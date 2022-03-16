package com.gs.service.intf;

import com.gs.model.entity.jpa.db1.User;
import com.gs.repository.jpa.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUserName(username);

        if (null == user) {
            // throw new EntityNotFoundException(SysUser.class, "name", username);
            return null;
        } else {
            return new JwtUser(user);
        }
    }
}
