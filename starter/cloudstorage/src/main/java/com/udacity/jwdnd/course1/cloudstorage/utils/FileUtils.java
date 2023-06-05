package com.udacity.jwdnd.course1.cloudstorage.utils;


import java.util.Arrays;

public class FileUtils {
  public static String getFileName(String fileName, String contentType) {
    String suffix = Arrays.stream(contentType.split("/")).toList().get(1);
    return fileName + "." + suffix;
  }
}
