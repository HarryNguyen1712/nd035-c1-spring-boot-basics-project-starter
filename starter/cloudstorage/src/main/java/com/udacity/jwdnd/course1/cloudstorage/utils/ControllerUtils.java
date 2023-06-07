package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.CredentialModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import org.springframework.ui.Model;

public class ControllerUtils {

  private static EncryptionService encryptionService;

  public ControllerUtils(EncryptionService encryptionService) {
    ControllerUtils.encryptionService = encryptionService;
  }

  public static String returnHome(Model model, String message, FileModelMapper fileModelMapper,
                                  FilesService filesService, NoteModelMapper noteModelMapper,
                                  NoteService noteService,
                                  CredentialModelMapper credentialModelMapper,
                                  CredentialsService credentialsService) {
    if (message != null) {
      model.addAttribute("message", message);
    }
    model.addAttribute("listFiles",
        fileModelMapper.convertEntityToModelList(filesService.getListFile()));
    model.addAttribute("listNotes",
        noteModelMapper.convertEntityToModelList(noteService.getListNote()));
    model.addAttribute("listCredentials",
        credentialModelMapper.convertEntityToModelList(credentialsService.getListCredential()));
    model.addAttribute("encryptionService", encryptionService);
    return "home";
  }
}
