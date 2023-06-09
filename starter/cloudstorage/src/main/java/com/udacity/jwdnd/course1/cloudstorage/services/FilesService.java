package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.utils.FileUtils;
import com.udacity.jwdnd.course1.cloudstorage.utils.UsersUtils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {

  private final FileMapper fileMapper;

  private final UserMapper userMapper;

  public FilesService(FileMapper fileMapper, UserMapper userMapper) {
    this.fileMapper = fileMapper;
    this.userMapper = userMapper;
  }

  public String uploadFile(MultipartFile file) {
    int userId = UsersUtils.getUserId(userMapper);
    try {
      File uploadFile =
          new File(null, FileUtils.splitSuffix(Objects.requireNonNull(file.getOriginalFilename())),
              file.getContentType(), file.getSize(), userId,
              file.getInputStream().readAllBytes());
      int result = fileMapper.insert(uploadFile);
      if (result == 1) {
        return null;
      } else {
        return "Upload file failed";
      }
    } catch (IOException ioException) {
      return "Error file";
    }
  }

  public List<File> getListFile() {
    int userId = UsersUtils.getUserId(userMapper);
    return fileMapper.getListFile(userId);
  }

  public String deleteFile(Integer fileId) {
    int userId = UsersUtils.getUserId(userMapper);
    int result = fileMapper.deleteFile(fileId, userId);
    if (result == 1) {
      return null;
    } else {
      return "Delete file failed";
    }

  }

  public File downloadFile(Integer fileId) {
    int userId = UsersUtils.getUserId(userMapper);
    return fileMapper.getFile(fileId, userId);
  }
}
