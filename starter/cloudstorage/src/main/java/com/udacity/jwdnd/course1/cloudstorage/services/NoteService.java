package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.utils.UsersUtils;
import com.udacity.jwdnd.course1.cloudstorage.web.model.NoteModel;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

  private final NoteMapper noteMapper;

  private final UserMapper userMapper;

  public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
    this.noteMapper = noteMapper;
    this.userMapper = userMapper;
  }

  public String insertOrUpdateNote(NoteModel noteModel) {
    int userId = UsersUtils.getUserId(userMapper);
    int result;
    Note note =
        new Note(noteModel.noteId(), noteModel.noteTitle(), noteModel.noteDescription(), userId);
    if (Objects.isNull(noteModel.noteId())) {
      result = noteMapper.insert(note);
    } else {
      result = noteMapper.updateNote(note);
    }
    if (result == 1) {
      return null;
    } else {
      return "Add or update note failed";
    }
  }

  public List<Note> getListNote() {
    int userId = UsersUtils.getUserId(userMapper);
    return noteMapper.getListNote(userId);
  }

  public String deleteNote(Integer noteId) {
    int userId = UsersUtils.getUserId(userMapper);
    int result = noteMapper.deleteNote(noteId, userId);
    if (result == 1) {
      return null;
    } else {
      return "Delete note failed";
    }
  }
}
