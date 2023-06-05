package com.udacity.jwdnd.course1.cloudstorage.web.model;

public record UserModel(Integer userId, String username, String salt, String password, String firstName, String lastName){
}
