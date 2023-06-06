package com.udacity.jwdnd.course1.cloudstorage.web.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.utils.FileUtils;
import com.udacity.jwdnd.course1.cloudstorage.web.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.web.model.NoteModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NoteModelMapper {

  public NoteModel convertEntityToModel(Note note) {
    return new NoteModel(note.noteId(), note.noteTitle(), note.noteDescription());
  }

  public List<NoteModel> convertEntityToModelList(List<Note> noteList) {
    List<NoteModel> noteModelList = new ArrayList<>();
    for (Note note : noteList) {
      noteModelList.add(convertEntityToModel(note));
    }
    return noteModelList;
  }
}
