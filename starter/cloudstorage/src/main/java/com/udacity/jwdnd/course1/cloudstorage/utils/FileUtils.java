package com.udacity.jwdnd.course1.cloudstorage.utils;


import java.util.Arrays;

public class FileUtils {
  public static String getFileName(String fileName, String contentType) {
    String suffix = Arrays.stream(contentType.split("/")).toList().get(1);
    return fileName + "." + suffix;
  }

  public static String splitSuffix(String fileNameWithPrefix) {
    String fileName = Arrays.stream(fileNameWithPrefix.split("\\.")).toList().get(0);
    return fileName;
  }
}
