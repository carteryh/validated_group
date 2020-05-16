package com.controller;

import cn.hutool.core.exceptions.ValidateException;
import com.alibaba.fastjson.JSON;
import com.dto.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@RestController("")
public class TestGroup {

    //https://blog.csdn.net/liu19900205/article/details/81698985
//https://blog.csdn.net/dream_broken/article/details/53584169
    @PostMapping("/user")
    public Object addUser( @RequestBody User user, BindingResult br){
//    public Object addUser(@Validated(value = User.Default.class) @RequestBody User user, BindingResult br){
//        Set<ConstraintViolation<User>> set= Validation.buildDefaultValidatorFactory().getValidator().validate(user);
        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, User.Default.class);

        for (ConstraintViolation<User> constraintViolation : set) {
           String messageTemplate = constraintViolation.getMessageTemplate();//验证信息
           System.out.println(messageTemplate);
        }



        if(br.hasErrors()){
            System.out.println(br);
//            return R.isFail().msg(br.getFieldError().getDefaultMessage());
        } else {

//            return R.isOk().data(user);
        }
        return "1";
    }

    @PutMapping("/user")
    public Object updateUser(@Validated(value = {User.Update.class, User.Default.class}) @RequestBody User user,
                        BindingResult br) {

        if(br.hasErrors()){
            System.out.println(br);

//            return R.isFail().msg(br.getFieldError().getDefaultMessage());
        } else {

//            return R.isOk().data(user);
        }
        return "2";

    }

    @PostMapping("/test")
    public Result test(@RequestBody User user){
        System.out.println("=====");
        System.out.println(JSON.toJSONString(user));
//    public Object addUser(@Validated(value = User.Default.class) @RequestBody User user, BindingResult br){
//        Set<ConstraintViolation<User>> set= Validation.buildDefaultValidatorFactory().getValidator().validate(user);
//        Validator validator = Validation.buildDefaultValidatorFactory()
//                .getValidator();
//        Set<ConstraintViolation<User>> set = validator.validate(user, User.Default.class);
//
//        for (ConstraintViolation<User> constraintViolation : set) {
//            String messageTemplate = constraintViolation.getMessageTemplate();//验证信息
//            System.out.println(messageTemplate);
//        }

        return Result.success(user);
    }

}