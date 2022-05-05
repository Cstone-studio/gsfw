package com.gs.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.gs.model.dto.UserDTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 两次输入密码是否一致验证规则
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {PasswordEqual.PasswordUserValidator.class})
public @interface PasswordEqual {

    // 校验未通过时的返回信息
    String message() default "两次输入的密码不一致";

    // 以下两行为固定模板
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class PasswordUserValidator implements ConstraintValidator<PasswordEqual, UserDTO> {

        @Override
        public boolean isValid(UserDTO changePasswordDto, ConstraintValidatorContext constraintValidatorContext) {
            String newPassword = changePasswordDto.getPassword();
            String confirmPassword = changePasswordDto.getConfirmPassword();
            return newPassword.equals(confirmPassword);
        }

        @Override
        public void initialize(PasswordEqual constraintAnnotation) {
        }
    }
}
