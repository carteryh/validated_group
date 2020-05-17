package com.aspect;

import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * Aspect切面
 * @author chenyouhong
 */
//声明这是一个组件
@Component
//声明这是一个切面Bean，AnnotaionAspect是一个面，由框架实现的
@Aspect
@Slf4j
public class ParamValidAspect {

	Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	//切点的集合，这个表达式所描述的是一个虚拟面（规则）
	//就是为了Annotation扫描时能够拿到注解中的内容
	@Pointcut("execution(* com.controller..*(..))")
	public void aspect(){}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) throws Exception {
		log.info("before " + joinPoint);
	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint){
		log.info("after " + joinPoint);
	}
	
	//配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable, Exception {
		boolean validSuccess = true;
		String messageTemplate = null;
		MethodSignature signature= (MethodSignature)joinPoint.getSignature();
		Annotation[] declaredAnnotations = signature.getMethod().getDeclaredAnnotations();
		if (declaredAnnotations.length > 0) {
			for (Annotation annotation: declaredAnnotations) {
				System.out.println(annotation);
			}
		}

		Annotation[][] parameterAnnotations= signature.getMethod().getParameterAnnotations();
		if (parameterAnnotations.length > 0) {
			for (Annotation[] annotations: parameterAnnotations) {
				for (Annotation annotation: annotations) {
					System.out.println(annotation);
					if (annotation instanceof Valid) {
						System.out.println(annotation);
					}
				}
			}
		}


//		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
//			for (Object obj: joinPoint.getArgs()) {
//				Set<ConstraintViolation<Object>> set = validator.validate(obj, Default.class);
//				for (ConstraintViolation<Object> constraintViolation : set) {
//					messageTemplate = constraintViolation.getMessageTemplate();//验证信息
//					validSuccess = false;
//					break;
//				}
//				if (!validSuccess) {
//					break;
//				}
//			}
//		}

		long start = System.currentTimeMillis();
		log.info("around start " + joinPoint + "\tUse time : " + start + " ms!");
		if (validSuccess) {
			return ((ProceedingJoinPoint) joinPoint).proceed();
		} else {
			new ValidateException(messageTemplate);
		}
		long end = System.currentTimeMillis();
		log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
		return null;
	}
	
	//配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint){
		log.info("afterReturn " + joinPoint);
	}
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
	}
	
}