package com.pengbo.mydemo.inf;

import com.pengbo.mydemo.model.User;

public interface UserDao {
    User queryUserById(String id);

    User queryUserByName(String name);
}
