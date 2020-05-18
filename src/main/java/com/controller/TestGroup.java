package com.controller;

import cn.hutool.core.exceptions.ValidateException;
import com.alibaba.fastjson.JSON;
import com.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

@RestController
public class TestGroup {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/user")
    public Result addUser( @RequestBody User user, BindingResult br){
//    public Object addUser(@Validated(value = User.Default.class) @RequestBody User user, BindingResult br){
//        Set<ConstraintViolation<User>> set= Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, User.Default.class);

        for (ConstraintViolation<User> constraintViolation : set) {
           String messageTemplate = constraintViolation.getMessageTemplate();//验证信息
           System.out.println(messageTemplate);
        }

        return Result.success(user);
    }

    @PutMapping("/user")
    public Result updateUser(@Validated(value = {User.Update.class, User.Default.class}) @RequestBody User user,
                        BindingResult br) {

        if(br.hasErrors()){
            System.out.println(br);

//            return R.isFail().msg(br.getFieldError().getDefaultMessage());
        } else {

//            return R.isOk().data(user);
        }
        return Result.success(user);
    }


    @PostMapping("/old")
    public Result old(@RequestBody @Valid User user){
        System.out.println("=====");
        System.out.println(JSON.toJSONString(user));
        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, Default.class);

        for (ConstraintViolation<User> constraintViolation : set) {
            String messageTemplate = constraintViolation.getMessageTemplate();//验证信息
            System.out.println(messageTemplate);
        }

        return Result.success(user);
    }

    @PostMapping("/test")
    public Result test(@RequestBody @Valid User user){
        System.out.println("=====");
        System.out.println(JSON.toJSONString(user));

        return Result.success(user);
    }

    @PutMapping("/test")
    public Result testUser(@Validated(value = {User.Update.class, User.Default.class}) @RequestBody User user) {
        System.out.println(JSON.toJSONString(user));
        return Result.success(user);
    }

    @GetMapping("/test2")
    public Result test2() {
        Object testGroup = applicationContext.getBean("testGroup");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(JSON.toJSONString(beanDefinitionNames));
//
        User user = new User();
         user.setAge(12);
         user.setId(12l);
         user.setName("臧尚");
        return Result.success(user);
    }

}