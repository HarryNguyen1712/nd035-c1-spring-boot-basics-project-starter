package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import static com.udacity.jwdnd.course1.cloudstorage.utils.ControllerUtils.returnHome;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.FileUtils;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.CredentialModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/web/file")
public class FilesController {

  private final FilesService filesService;

  private final FileModelMapper fileModelMapper;

  private final NoteService noteService;

  private final NoteModelMapper noteModelMapper;

  private final CredentialModelMapper credentialModelMapper;

  private final CredentialsService credentialsService;


  public FilesController(FilesService filesService, FileModelMapper fileModelMapper,
                         NoteService noteService, NoteModelMapper noteModelMapper,
                         CredentialModelMapper credentialModelMapper,
                         CredentialsService credentialsService) {
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
    this.noteService = noteService;
    this.noteModelMapper = noteModelMapper;
    this.credentialModelMapper = credentialModelMapper;
    this.credentialsService = credentialsService;
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
    String message = filesService.uploadFile(file);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }

  @GetMapping("/delete")
  public String deleteFile(@RequestParam("fileId") Integer fileId, Model model) {
    String message = filesService.deleteFile(fileId);
    return returnHome(model, message, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }

  @GetMapping("/download")
  public void downloadFile(@RequestParam("fileId") Integer fileId, HttpServletResponse response)
      throws IOException {
    File file = filesService.downloadFile(fileId);
    response.setContentType("application/octet-stream");
    response.setHeader("Content-disposition",
        "attachment; filename=" + FileUtils.getFileName(file.fileName(), file.contentType()));
    response.setContentLength(file.fileData().length);
    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(file.fileData()));
    FileCopyUtils.copy(inputStream, response.getOutputStream());
  }
}
