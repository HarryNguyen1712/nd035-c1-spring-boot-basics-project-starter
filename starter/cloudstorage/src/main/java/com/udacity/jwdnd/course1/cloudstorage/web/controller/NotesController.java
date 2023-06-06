package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.NoteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/note")
public class NotesController {

  private final NoteService noteService;

  private final FilesService filesService;

  private final FileModelMapper fileModelMapper;

  private final NoteModelMapper noteModelMapper;

  public NotesController(NoteService noteService, FilesService filesService,
                         FileModelMapper fileModelMapper, NoteModelMapper noteModelMapper) {
    this.noteService = noteService;
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
    this.noteModelMapper = noteModelMapper;
  }

  @PostMapping("/addNote")
  public String insertNote(@ModelAttribute NoteModel noteModel, Model model) {
    String message = noteService.insertOrUpdateNote(noteModel);
    if (message != null) {
      model.addAttribute("message", message);
    }
    model.addAttribute("listFiles",
        fileModelMapper.convertEntityToModelList(filesService.getListFile()));
    model.addAttribute("listNotes",
        noteModelMapper.convertEntityToModelList(noteService.getListNote()));
    return "home";
  }

  @GetMapping("/deleteNote")
  public String insertNote(@RequestParam("noteId") Integer noteId, Model model) {
    String message = noteService.deleteNote(noteId);
    if (message != null) {
      model.addAttribute("message", message);
    }
    model.addAttribute("listFiles",
        fileModelMapper.convertEntityToModelList(filesService.getListFile()));
    model.addAttribute("listNotes",
        noteModelMapper.convertEntityToModelList(noteService.getListNote()));
    return "home";
  }
}
