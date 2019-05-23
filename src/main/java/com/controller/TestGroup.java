package com.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}