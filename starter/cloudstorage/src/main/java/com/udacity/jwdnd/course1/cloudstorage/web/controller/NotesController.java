package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import static com.udacity.jwdnd.course1.cloudstorage.utils.ControllerUtils.returnHome;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.CredentialModelMapper;
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

  private final CredentialModelMapper credentialModelMapper;

  private final CredentialsService credentialsService;

  public NotesController(NoteService noteService, FilesService filesService,
                         FileModelMapper fileModelMapper, NoteModelMapper noteModelMapper,
                         CredentialModelMapper credentialModelMapper,
                         CredentialsService credentialsService) {
    this.noteService = noteService;
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
    this.noteModelMapper = noteModelMapper;
    this.credentialModelMapper = credentialModelMapper;
    this.credentialsService = credentialsService;
  }

  @PostMapping("/addNote")
  public String insertNote(@ModelAttribute NoteModel noteModel, Model model) {
    String message = noteService.insertOrUpdateNote(noteModel);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }

  @GetMapping("/deleteNote")
  public String deleteNote(@RequestParam("noteId") Integer noteId, Model model) {
    String message = noteService.deleteNote(noteId);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }
}
