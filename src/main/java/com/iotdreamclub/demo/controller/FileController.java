package com.iotdreamclub.demo.controller;

import com.iotdreamclub.demo.config.FtpConfig;
import com.iotdreamclub.demo.entity.FileUpload;
import com.iotdreamclub.demo.service.FileService;
import com.iotdreamclub.demo.service.FunctionService;
import com.iotdreamclub.demo.service.impl.FunctionServiceImpl;
import com.iotdreamclub.demo.utils.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FtpConfig ftpConfig;

    @RequestMapping("zuul/fileUpload")
    @Transactional
    public String fileUpload(@RequestParam("file_upload")MultipartFile multipartFile, FileUpload fileUpload , HttpSession session , HttpServletRequest request){
        FunctionService functionService = new FunctionServiceImpl();
        String data = functionService.createAnOrderId();
        fileUpload.setFileId(data);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String realPath = request.getSession().getServletContext().getRealPath(File.separator);
        String format = sd.format(new Date(1));
        File file = new File(realPath + format);
        if (!file.isDirectory()){
            file.mkdirs();
        }

        System.out.println(multipartFile.getSize());

        String oldName = multipartFile.getOriginalFilename();

        String[] fileType = oldName.split("\\.");

        String newName = data + "." + fileType[1];

        MessageDigest messageDigest;
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(oldName.substring(0, oldName.indexOf(".")).getBytes());
            File storeFile = new File(file, oldName);
            multipartFile.transferTo(storeFile);
            InputStream in = new FileInputStream(storeFile);
            boolean res = FTPUtil.uploadFile(ftpConfig.getFTP_ADDRESS(), 21, ftpConfig.getFTP_USERNAME(),ftpConfig.getFTP_PASSWORD(), ftpConfig.getFTP_BASEPATH(), "/", newName, in);

            fileUpload.setFileName(oldName);
            fileUpload.setFileUrl(ftpConfig.getFTP_BASEPATH());
            fileUpload.setFileUploader(String.valueOf(session.getAttribute("username")));

//            boolean delete = storeFile.delete();
//            if(res && delete){
//                return "index_file_upload";
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
        fileService.insertFileDetail(fileUpload);
        return "index_file_upload";
    }

    @RequestMapping("fileDownload/{fileId}")
    public String fileDownload(@PathVariable String fileId){
        String fileName = null;
        FTPUtil.downloadFile(ftpConfig.getFTP_ADDRESS(),21,ftpConfig.getFTP_USERNAME(),ftpConfig.getFTP_PASSWORD(),ftpConfig.getFTP_BASEPATH(),ftpConfig.getFTP_BASEPATH() + fileName,fileName);
        return "index_file_upload";
    }
}
