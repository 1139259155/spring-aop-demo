package com.pengbo.mydemo.test;

import com.pengbo.mydemo.AppConfig;
import com.pengbo.mydemo.inf.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        userDao.queryUserById("123");
        System.out.println();
        userDao.queryUserByName("haha");
    }
}
