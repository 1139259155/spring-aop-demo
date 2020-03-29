package com.pengbo.mydemo.aspect;

import com.pengbo.mydemo.annotation.MyAop;
import com.pengbo.mydemo.contants.Type;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class MyAspect {

    @Pointcut("@annotation(com.pengbo.mydemo.annotation.MyAop)")
    public void pointCut1() {
    }

    @Around("pointCut1()")
    public void around1(ProceedingJoinPoint joinPoint) throws Throwable {

        //case1: process args
        //if (joinPoint.getArgs())

        //case2: process annotation
        MyAop myAop = getMethodAnnotaion(joinPoint, MyAop.class);
        Type type = myAop.type();
        System.out.println("myaop type is : " + type.value());

        if (StringUtils.equals(type.value(), Type.before.value())) {
            System.out.println("begin around");
        }

        joinPoint.proceed();

        if (StringUtils.equals(type.value(), Type.after.value())) {
            System.out.println("after around");
        }
    }

    @Before("pointCut1()")
    public void before() {
        System.out.println("before");
    }

    @After("pointCut1()")
    public void after() {
        System.out.println("after");
    }


    public <T extends Annotation> T getMethodAnnotaion(ProceedingJoinPoint joinPoint, Class<T> annotationCls) throws Exception {
        // get class
        Class cls = joinPoint.getTarget().getClass();

        // get method by method sign
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = cls.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        // get annotation
        return method.getAnnotation(annotationCls);
    }
}
