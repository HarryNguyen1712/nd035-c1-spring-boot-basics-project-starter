package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CredentialMapper {

  @Insert("INSERT INTO CREDENTIALS (url, username, `key`, password, userid) "
      + "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
  @Options(useGeneratedKeys = true, keyColumn = "credentialid")
  int insert(Credential credential);

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
  List<Credential> getListCredential(Integer userId);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, `key` = #{key}, "
      + "password = #{password} WHERE (credentialid = #{credentialId} AND userId = #{userId})")
  int updateCredential(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId} AND userid = #{userId}")
  int deleteCredential(Integer credentialId, Integer userId);
}
