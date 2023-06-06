package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

  private final UsersService usersService;

  private final FilesService filesService;

  private final FileModelMapper fileModelMapper;

  private final NoteModelMapper noteModelMapper;

  private final NoteService noteService;

  public UsersController(UsersService usersService, FilesService filesService,
                         FileModelMapper fileModelMapper, NoteModelMapper noteModelMapper,
                         NoteService noteService) {
    this.usersService = usersService;
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
    this.noteModelMapper = noteModelMapper;
    this.noteService = noteService;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @GetMapping("/home")
  public String homePage(Model model) {
    model.addAttribute("listFiles",
        fileModelMapper.convertEntityToModelList(filesService.getListFile()));
    model.addAttribute("listNotes",
        noteModelMapper.convertEntityToModelList(noteService.getListNote()));
    return "home";
  }

  @PostMapping("/signup")
  public RedirectView signUp(@ModelAttribute UserModel userModel, Model model) {
    String message = usersService.signUpUser(userModel);
    model.addAttribute("message", message);
    return new RedirectView("/login");
  }
}
