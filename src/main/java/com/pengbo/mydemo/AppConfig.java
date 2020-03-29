package com.pengbo.mydemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.pengbo.mydemo")
@EnableAspectJAutoProxy
public class AppConfig {
}
