package com.udacity.jwdnd.course1.cloudstorage.web.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.utils.FileUtils;
import com.udacity.jwdnd.course1.cloudstorage.web.model.FileModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FileModelMapper {

  public FileModel convertEntityToModel(File file) {
    return new FileModel(file.fileId(), FileUtils.getFileName(file.fileName(), file.contentType()));
  }

  public List<FileModel> convertEntityToModelList(List<File> fileList) {
    List<FileModel> fileModelList = new ArrayList<>();
    for (File file : fileList) {
      fileModelList.add(convertEntityToModel(file));
    }
    return fileModelList;
  }
}
