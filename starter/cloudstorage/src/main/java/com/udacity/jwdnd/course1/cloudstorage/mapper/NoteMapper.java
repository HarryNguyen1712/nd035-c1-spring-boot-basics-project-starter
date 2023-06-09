package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface NoteMapper {
  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) "
      + "VALUES (#{noteTitle}, #{noteDescription} ,#{userId})")
  @Options(useGeneratedKeys = true, keyColumn = "noteid")
  int insert(Note note);

  @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
  List<Note> getListNote(Integer userId);

  @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}"
      + " WHERE (noteId = #{noteId} AND userId = #{userId})")
  int updateNote(Note note);

  @Delete("DELETE FROM NOTES WHERE (noteid = #{noteId} AND userid = #{userId})")
  int deleteNote(Integer noteId, Integer userId);
}
