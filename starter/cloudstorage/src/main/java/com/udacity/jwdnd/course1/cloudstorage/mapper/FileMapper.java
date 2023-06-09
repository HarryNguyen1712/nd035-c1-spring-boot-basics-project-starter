package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) "
      + "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
  @Options(useGeneratedKeys = true, keyColumn = "fileid")
  int insert(File file);

  @Select("SELECT * FROM FILES WHERE userid = #{userId}")
  List<File> getListFile(Integer userId);

  @Delete("DELETE FROM FILES WHERE (fileid = #{fileId} AND #{userId})")
  int deleteFile(Integer fileId, Integer userId);

  @Select("SELECT * FROM FILES WHERE (fileid = #{fileId} AND userid = #{userId})")
  File getFile(Integer fileId, Integer userId);
}
