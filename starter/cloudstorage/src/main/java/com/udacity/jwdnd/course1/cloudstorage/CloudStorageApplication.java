package com.udacity.jwdnd.course1.cloudstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.udacity.jwdnd.course1.cloudstorage.mapper")
@SpringBootApplication
public class CloudStorageApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudStorageApplication.class, args);
  }

}
