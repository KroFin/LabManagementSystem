package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.dao.FileMapper;
import com.iotdreamclub.demo.entity.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface FileService {
    List<FileUpload> selectAllFile();
    void insertFileDetail(FileUpload fileUpload);
}
