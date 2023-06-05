package com.udacity.jwdnd.course1.cloudstorage.web.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import com.udacity.jwdnd.course1.cloudstorage.web.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

  private final UsersService usersService;

  public UsersController( UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping("/home")
  public String getHomePage() {
    return "home";
  }

  @GetMapping ("/signup")
  public String getSignup() {
    return "signup";
  }

  @PostMapping ("/signup")
  public String postSignup(@ModelAttribute UserModel userModel, Model model) {
    String message = usersService.signUpUser(userModel);
    model.addAttribute("message",message);
    return "login";
  }
}
