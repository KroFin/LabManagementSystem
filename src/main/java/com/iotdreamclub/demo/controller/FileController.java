package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.config.FtpConfig;
import com.iotdreamclub.demo.entity.FileUpload;
import com.iotdreamclub.demo.service.FileService;
import com.iotdreamclub.demo.service.FunctionService;
import com.iotdreamclub.demo.service.impl.FunctionServiceImpl;
import com.iotdreamclub.demo.utils.FTPUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FtpConfig ftpConfig;

    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file_upload")MultipartFile multipartFile, FileUpload fileUpload , HttpSession session , HttpServletRequest request){
        FunctionService functionService = new FunctionServiceImpl();
        fileUpload.setFileId(functionService.createAnOrderId());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String realPath = request.getSession().getServletContext().getRealPath(File.separator);
        String format = sd.format(new Date(1));
        File file = new File(realPath + format);
        if (!file.isDirectory()){
            file.mkdirs();
        }
        String oldName = multipartFile.getOriginalFilename();
        MessageDigest messageDigest;
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(oldName.substring(0, oldName.indexOf(".")).getBytes());
            String newName = byte2Hex(messageDigest.digest()) + oldName.substring(oldName.indexOf("."));
            File storeFile = new File(file, newName);
            multipartFile.transferTo(storeFile);
            InputStream in = new FileInputStream(storeFile);
            boolean res = FTPUtil.uploadFile(ftpConfig.getFTP_ADDRESS(), 21, ftpConfig.getFTP_USERNAME(),ftpConfig.getFTP_PASSWORD(), ftpConfig.getFTP_BASEPATH(), "/", newName, in);

            fileUpload.setFileName(newName);
            fileUpload.setFileUrl(ftpConfig.getFTP_BASEPATH());
            fileUpload.setFileUploader(String.valueOf(session.getAttribute("username")));

            boolean delete = storeFile.delete();
            if(res && delete){
                return "index_file_upload";
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        fileService.insertFileDetail(fileUpload);

        return "index_file_upload";

    }

    private String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = "";
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0XFF);
            if(temp.length() == 1){
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
