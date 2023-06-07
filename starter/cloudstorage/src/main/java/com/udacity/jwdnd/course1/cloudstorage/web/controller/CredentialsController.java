package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import static com.udacity.jwdnd.course1.cloudstorage.utils.ControllerUtils.returnHome;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.CredentialModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.CredentialModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/credential")
public class CredentialsController {

  private final CredentialsService credentialsService;

  private final NoteService noteService;

  private final FilesService filesService;

  private final FileModelMapper fileModelMapper;

  private final NoteModelMapper noteModelMapper;

  private final CredentialModelMapper credentialModelMapper;


  public CredentialsController(CredentialsService credentialsService,
                               FileModelMapper fileModelMapper, NoteService noteService,
                               FilesService filesService, NoteModelMapper noteModelMapper,
                               CredentialModelMapper credentialModelMapper) {
    this.credentialsService = credentialsService;
    this.fileModelMapper = fileModelMapper;
    this.noteService = noteService;
    this.filesService = filesService;
    this.noteModelMapper = noteModelMapper;
    this.credentialModelMapper = credentialModelMapper;
  }

  @PostMapping("/addCredential")
  public String insertNote(@ModelAttribute CredentialModel credentialModel, Model model) {
    String message = credentialsService.insertOrUpdateCredential(credentialModel);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }

  @GetMapping("/deleteCredential")
  public String deleteCredential(@RequestParam("noteId") Integer credentialId, Model model) {
    String message = credentialsService.deleteCredential(credentialId);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }
}
