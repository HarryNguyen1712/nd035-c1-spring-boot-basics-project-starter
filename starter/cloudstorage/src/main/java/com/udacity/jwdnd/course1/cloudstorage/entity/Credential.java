package com.udacity.jwdnd.course1.cloudstorage.entity;

public record Credential(

    Integer credentialId,

    String url,

    String username,

    String key,

    String password,

    Integer userId) {
}
