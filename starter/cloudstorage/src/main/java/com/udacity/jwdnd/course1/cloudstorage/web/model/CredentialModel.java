package com.udacity.jwdnd.course1.cloudstorage.web.model;

public record CredentialModel(

    Integer credentialId,

    String url,

    String username,

    String key,

    String password) {
}
