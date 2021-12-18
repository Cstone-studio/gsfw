package com.gs.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

/**
 * 手机号格式验证规则
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsPhone.IsPhoneValidator.class})
@Target({ElementType.METHOD,
         ElementType.FIELD,
         ElementType.ANNOTATION_TYPE,
         ElementType.CONSTRUCTOR,
         ElementType.PARAMETER})
public @interface IsPhone {

    String message() default "手机号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class IsPhoneValidator implements ConstraintValidator<IsPhone, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if ("".equals(value) || null == value) {
                return false;
            }else{
                return isPhone(value);
            }
        }

        /** 验证是否为手机号
         * @param phone
         * @return
         */
        public static boolean isPhone (String phone){
            String pattern  = "^1[0-9]{10}$";
            if (Pattern.matches(pattern, phone)) {
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void initialize(IsPhone constraintAnnotation) {

        }
    }
}
