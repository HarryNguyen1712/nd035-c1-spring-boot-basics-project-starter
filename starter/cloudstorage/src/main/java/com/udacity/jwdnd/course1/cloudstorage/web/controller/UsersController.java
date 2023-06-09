package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import static com.udacity.jwdnd.course1.cloudstorage.utils.ControllerUtils.returnHome;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.CredentialModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.NoteModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsersController {

  private final UsersService usersService;

  private final FilesService filesService;

  private final FileModelMapper fileModelMapper;

  private final NoteModelMapper noteModelMapper;

  private final NoteService noteService;
  private final CredentialsService credentialsService;
  private final CredentialModelMapper credentialModelMapper;

  public UsersController(UsersService usersService, FilesService filesService,
                         FileModelMapper fileModelMapper, NoteModelMapper noteModelMapper,
                         NoteService noteService, CredentialsService credentialsService,
                         CredentialModelMapper credentialModelMapper) {
    this.usersService = usersService;
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
    this.noteModelMapper = noteModelMapper;
    this.noteService = noteService;
    this.credentialsService = credentialsService;
    this.credentialModelMapper = credentialModelMapper;
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
    return returnHome(model, null, fileModelMapper, filesService, noteModelMapper, noteService,
        credentialModelMapper, credentialsService);
  }

  @PostMapping("/signup")
  public String signUp(@ModelAttribute UserModel userModel, RedirectAttributes redirectAttrs) {
    String message = usersService.signUpUser(userModel);
    if (message != null) {
      redirectAttrs.addFlashAttribute("message", message);
      return "/signup";
    } else {
      redirectAttrs.addFlashAttribute("message", "You successfully signed up!");
      return "redirect:/login";
    }
  }
}
