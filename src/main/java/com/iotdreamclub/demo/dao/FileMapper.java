package com.iotdreamclub.demo.dao;

import com.iotdreamclub.demo.entity.FileUpload;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
    List<FileUpload> selectAllFile();
    void insertFileDetail(FileUpload fileUpload);
}
