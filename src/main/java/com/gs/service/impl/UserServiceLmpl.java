package com.gs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gs.model.dto.UserDTO;
import com.gs.model.dto.UserLoginDTO;
import com.gs.model.entity.jpa.db1.User;
import com.gs.repository.jpa.UserRepository;
import com.gs.service.intf.UserService;
import com.gs.convert.UserConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.transaction.annotation.Propagation;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceLmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert userConvert;

    @Override
    public Map<String, Object> page(UserDTO dto, Pageable pageable){
        Page<User> page = userRepository.findAll(new Spec(dto),pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("content", page.stream().collect(Collectors.toList()));
        map.put("totalElements", page.getTotalElements());
        return map;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.isPresent() ? userConvert.toDto(userOptional.get()) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(UserDTO dto) {
        User user = userRepository.save(userConvert.toEntity(dto));
        return userConvert.toDto(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDTO dto) {
        userRepository.save(userConvert.toEntity(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<User> optionalNews = userRepository.findById(id);
        if(optionalNews.isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        return userRepository.findByUserNameAndPassword(userLoginDTO.getUserName(), DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes()));
    }

    /**
     * 登录成功后保存token,用来检验重复登录
     * @param User 用户新信息
     */
    @Override
    public void loginSuccess(User user) {
        userRepository.save(user);
    }

    class Spec implements Specification<User> {

        private UserDTO userDTO;

        public Spec(UserDTO dto){
            this.userDTO = dto;
        }

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

            List<Predicate> list = new ArrayList<Predicate>();

            if(!ObjectUtils.isEmpty(userDTO.getUserName())){
                list.add(cb.like(root.get("userName").as(String.class),"%" + userDTO.getUserName() + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }

    }
}
