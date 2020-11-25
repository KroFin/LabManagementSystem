package com.iotdreamclub.demo.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FTPUtil {

    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            ftp.login(username, password);
            ftp.setConnectTimeout(3600000);
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean downloadFile(String host, int port, String username, String password, String basePath,
                                String filePath, String filename){
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        OutputStream os = null;
        try {
            ftp.connect(host , port);
            ftp.login(username,password);
            int reply = ftp.getReplyCode();
            System.out.println(reply);
            ftp.enterLocalActiveMode();
            FTPFile[] ftpFiles = ftp.listFiles();
            for (FTPFile file : ftpFiles){
                byte[] bytes = file.getName().getBytes("ISO-8859-1");
                file.setName(new String(bytes, "utf-8"));
                System.out.println("name: " + file.getName());//
                if (filename.equalsIgnoreCase(file.getName())) {

                    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                    System.out.println("该文件的id："+fileName);
                    File localFile = new File(basePath + "/" + fileName);
                    os = new FileOutputStream(localFile);
                    ftp.retrieveFile(file.getName(), os);
                    os.close();
                }
                ftp.logout();
                flag = true;
                System.out.println("下载文件成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("下载文件失败");
        }finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
