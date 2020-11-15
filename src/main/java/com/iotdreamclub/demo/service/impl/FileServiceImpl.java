package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.FileMapper;
import com.iotdreamclub.demo.entity.FileUpload;
import com.iotdreamclub.demo.service.FileService;
import com.iotdreamclub.demo.service.FunctionService;
import com.iotdreamclub.demo.utils.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileUpload> selectAllFile() {
        return fileMapper.selectAllFile();
    }

    @Override
    public void insertFileDetail(FileUpload fileUpload) {
        fileMapper.insertFileDetail(fileUpload);
    }
}
