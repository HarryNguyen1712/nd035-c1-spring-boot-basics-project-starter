package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  User getUser(String userName);

  @Select("SELECT count(username) FROM USERS WHERE username = #{username}")
  int checkUserExist(String userName);

  @Select("SELECT userid FROM USERS WHERE username = #{username}")
  int getUserId(String userName);

  @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname)"
      + " VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
  @Options(useGeneratedKeys = true, keyColumn = "USERID")
  int insert(User user);
}
