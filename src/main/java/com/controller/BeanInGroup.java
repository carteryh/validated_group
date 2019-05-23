package com.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class BeanInGroup {
    @Null(groups = CreateAction.class)
    @NotNull(groups = UpdateAction.class, message = "更新不能为空!")
    private Long id;
}