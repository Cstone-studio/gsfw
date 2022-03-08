package com.gs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.model.entity.mybatis.db1.User;
import com.gs.repository.db1.UserMybatisRepository;
import com.gs.service.intf.JwtUserDetailsService;
import com.gs.utils.JwtTokenUtil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtUserDetailsService jwtUserDetailsService;

  private final JwtTokenUtil jwtTokenUtil;

  private final UserMybatisRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
  throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");
    String username = null;
    String jwtToken = null;
    // JWT Token is in the form "Bearer token". Remove Bearer word and get
    // only the Token
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        System.out.println("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        System.out.println("JWT Token has expired");
      } catch (MalformedJwtException e) {
        System.out.println("Unable to get JWT Token");
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }
    // Once we get the token validate it.
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
      // if token is valid configure Spring Security to manually set
      // authentication
      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    //查看是否当前账号是否已经在其他地方进行了登陆
    if(username != null && jwtToken != null) {
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.lambda().eq(User::getUserName, username);
      queryWrapper.lambda().eq(User::getToken, jwtToken);
      User user = userRepository.selectOne(queryWrapper);
      if(user == null){
        //有其他人在其他地方登陆当前账号，抛出异常
        try {
          int i = 0 / 0;
        } catch (Exception e) {
          HttpServletRequest req = (HttpServletRequest) request;
          req.setAttribute("errorMessage", e);
          req.getRequestDispatcher("/api/mutiLogin").forward(request, response);
        }
      }
    }

    chain.doFilter(request, response);
  }
}
