
package com.elvin.course.dao;

import com.elvin.course.model.User;

public interface UserDao {
     public User login(int role,String username, String password);
}
