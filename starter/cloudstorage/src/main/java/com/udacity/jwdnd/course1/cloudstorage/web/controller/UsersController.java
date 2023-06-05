package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import com.udacity.jwdnd.course1.cloudstorage.web.mapper.FileModelMapper;
import com.udacity.jwdnd.course1.cloudstorage.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

  private final UsersService usersService;

  private final FilesService filesService;
  private final FileModelMapper fileModelMapper;

  public UsersController(UsersService usersService, FilesService filesService,
                         FileModelMapper fileModelMapper) {
    this.usersService = usersService;
    this.filesService = filesService;
    this.fileModelMapper = fileModelMapper;
  }

  @GetMapping("/home")
  public String homePage(Model model) {
    model.addAttribute("listFiles",
        fileModelMapper.convertEntityToModelList(filesService.getListFile()));
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signUp(@ModelAttribute UserModel userModel, Model model) {
    String message = usersService.signUpUser(userModel);
    model.addAttribute("message", message);
    return "login";
  }
}
