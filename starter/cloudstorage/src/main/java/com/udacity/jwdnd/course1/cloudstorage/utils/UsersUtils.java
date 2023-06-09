package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsersUtils {


  public static Integer getUserId(UserMapper userMapper) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    int userId = userMapper.getUserId(username);
    return userId;
  }
}
