package com.pengbo.mydemo.annotation;

import com.pengbo.mydemo.contants.Type;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAop {

    Type type();
}

