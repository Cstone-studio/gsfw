package com.gs.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.model.entity.mybatis.db1.User;
import com.gs.repository.db1.UserMybatisRepository;

import lombok.AllArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 邮箱是否注册验证规则
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsEmailExist.IsEmailExistValidator.class})
@Target({ElementType.METHOD,
         ElementType.FIELD,
         ElementType.ANNOTATION_TYPE,
         ElementType.CONSTRUCTOR,
         ElementType.PARAMETER})
public @interface IsEmailExist {

    String message() default "该账号已注册";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @AllArgsConstructor
    public class IsEmailExistValidator implements ConstraintValidator<IsEmailExist, String> {

        private final UserMybatisRepository userRepository;

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            if ("".equals(value) || null == value) {
                return false;
            }else{
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(User::getEmail, value);
                User emailExist = userRepository.selectOne(queryWrapper);
                if (null == emailExist) {
                    return true;
                }else {
                    return false;
                }
            }
        }

        @Override
        public void initialize(IsEmailExist constraintAnnotation) {

        }
    }
}
