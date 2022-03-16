package com.gs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author 
 * @version 1.0
 * @description
 * @createTime 2019/3/9
 **/
@Configuration
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Object principalOrAnonymous = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principalOrAnonymous.getClass().equals(String.class))
        {
            return null;
        }else {
            return Optional.ofNullable(((UserDetails)principalOrAnonymous).getUsername());
        }
    }
}
