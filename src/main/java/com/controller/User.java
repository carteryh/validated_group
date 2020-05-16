package com.controller;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    public interface Default{}

    public interface Update{}


    @NotNull(message = "id不能为空" , groups = Update.class)
    private Long id;

    @NotBlank(message = "名字不能为空", groups = Default.class)
    @Length(message = "name 长度必须在之间", groups = Default.class)
    @Length(min = 4, max = 10, message = "name 长度必须在 {min} - {max} 之间")
    private String name;

    @NotNull(message = "年龄不能为空")
    @NotNull(message = "年龄不能为空", groups = Default.class)
    @Min(value = 18, message = "年龄不能小于18岁", groups = Default.class)
    private Integer age;
}
