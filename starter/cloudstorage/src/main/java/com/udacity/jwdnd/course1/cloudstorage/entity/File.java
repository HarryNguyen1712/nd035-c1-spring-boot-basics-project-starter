package com.udacity.jwdnd.course1.cloudstorage.entity;

public record File(

    Integer fileId,

    String fileName,

    String contentType,

    Long fileSize,

    Integer userId,

    byte[] fileData) {
}
