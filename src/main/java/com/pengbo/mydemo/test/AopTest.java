package com.pengbo.mydemo.test;

import com.pengbo.mydemo.inf.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.pengbo.mydemo")
@EnableAspectJAutoProxy//(proxyTargetClass = true)
public class AopTest {
    public static void main(String[] args) {
        // 保存jdk动态代理proxy临时文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 保存cglib动态代理proxy临时文件
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglibproxyclass");

        // 初始化spring，已完成代理
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);

        UserDao userDao = context.getBean(UserDao.class);

        userDao.queryUserById("123");
        System.out.println();
        userDao.queryUserByName("haha");
    }
}
