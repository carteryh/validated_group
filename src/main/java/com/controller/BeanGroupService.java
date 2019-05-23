package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
@Slf4j
public class BeanGroupService {
    @Validated(CreateAction.class)
    public void validateInCreate(@Valid BeanInGroup beanGroup) {
        log.info("validateInCreate:{}", beanGroup);
    }

    @Validated(UpdateAction.class)
    public void validateInUpdate(@Valid BeanInGroup beanGroup) {
        log.info("validateInUpdate:{}", beanGroup);
    }
}