package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FIleMapper {

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
  @Options(useGeneratedKeys = true, keyColumn = "FILEID")
  int insert(File file);

  @Select("SELECT * FROM FILES")
  List<File> getListFile();

  @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
  int deleteFile(Integer fileId);

  @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
  File getFile(Integer fileId);
}
