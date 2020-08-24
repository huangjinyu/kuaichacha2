package com.kcc.core.exception;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * 如果多个请求参数都校验失败，则遇到第一个校验失败就抛出异常，接下来的异常参数不做校验
 */
@Configuration
public class ValidationExceptionConfiguration {
//    @Bean
//    public Validator validator() {
//        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
//                .configure()
//                //failFast的意思只要出现校验失败的情况，就立即结束校验，不再进行后续的校验。
//                .failFast(true)
//                .buildValidatorFactory();
//
//        return validatorFactory.getValidator();
//    }
//
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
//        methodValidationPostProcessor.setValidator(validator());
//        return methodValidationPostProcessor;
//    }
}
