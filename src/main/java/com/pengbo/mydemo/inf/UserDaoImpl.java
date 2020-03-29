package com.pengbo.mydemo.inf;

import com.pengbo.mydemo.annotation.MyAop;
import com.pengbo.mydemo.contants.Type;
import com.pengbo.mydemo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    @MyAop(type = Type.before)
    public User queryUserById(String id) {
        System.out.println("hello user: " + id);
        return new User();
    }

    @MyAop(type = Type.after)
    public User queryUserByName(String name) {
        System.out.println("hello user: " + name);
        return new User();
    }

}
