package com.gs.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.model.entity.db1.User;
import com.gs.repository.db1.UserRepository;

import lombok.AllArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机号是否已经注册验证规则
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsPhoneExist.IsPhoneExistValidator.class})
@Target({ElementType.METHOD,
         ElementType.FIELD,
         ElementType.ANNOTATION_TYPE,
         ElementType.CONSTRUCTOR,
         ElementType.PARAMETER})
public @interface IsPhoneExist {

    String message() default "该手机号已注册";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @AllArgsConstructor
    public class IsPhoneExistValidator implements ConstraintValidator<IsPhoneExist, String> {

        private UserRepository userRepository;

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            if ("".equals(value) || null == value) {
                return false;
            }else{
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(User::getMobile, value);
                User mobileExist = userRepository.selectOne(queryWrapper);
                if (null == mobileExist) {
                    return true;
                }else {
                    return false;
                }
            }
        }

        @Override
        public void initialize(IsPhoneExist constraintAnnotation) {

        }
    }
}
