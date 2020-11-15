package com.iotdreamclub.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FtpConfig {
    /**
     * 获取ip地址
     */
    @Value("123.57.252.81")
    private String FTP_ADDRESS;

    /**
     * 端口号
     */
    @Value("21")
    private String FTP_PORT;

    /**
     * 用户名
     */
    @Value("LabManagementSystem")
    private String FTP_USERNAME;

    /**
     * 密码
     */
    @Value("iot123456")
    private String FTP_PASSWORD;

    /**基本路径
     */
    @Value("/www/wwwroot/LabManagementSystem/file")
    private String FTP_BASEPATH;

    public FtpConfig() {

    }

    /**
     * 下载地址地基础url
     */
    @Value("ftp://123.57.252.81/YunHome")
    private String IMAGE_BASE_URL;

    @Override
    public String toString() {
        return "FtpConfig{" +
                "FTP_ADDRESS='" + FTP_ADDRESS + '\'' +
                ", FTP_PORT='" + FTP_PORT + '\'' +
                ", FTP_USERNAME='" + FTP_USERNAME + '\'' +
                ", FTP_PASSWORD='" + FTP_PASSWORD + '\'' +
                ", FTP_BASEPATH='" + FTP_BASEPATH + '\'' +
                ", IMAGE_BASE_URL='" + IMAGE_BASE_URL + '\'' +
                '}';
    }

    public String getFTP_ADDRESS() {
        return FTP_ADDRESS;
    }

    public void setFTP_ADDRESS(String FTP_ADDRESS) {
        this.FTP_ADDRESS = FTP_ADDRESS;
    }

    public String getFTP_PORT() {
        return FTP_PORT;
    }

    public void setFTP_PORT(String FTP_PORT) {
        this.FTP_PORT = FTP_PORT;
    }

    public String getFTP_USERNAME() {
        return FTP_USERNAME;
    }

    public void setFTP_USERNAME(String FTP_USERNAME) {
        this.FTP_USERNAME = FTP_USERNAME;
    }

    public String getFTP_PASSWORD() {
        return FTP_PASSWORD;
    }

    public void setFTP_PASSWORD(String FTP_PASSWORD) {
        this.FTP_PASSWORD = FTP_PASSWORD;
    }

    public String getFTP_BASEPATH() {
        return FTP_BASEPATH;
    }

    public void setFTP_BASEPATH(String FTP_BASEPATH) {
        this.FTP_BASEPATH = FTP_BASEPATH;
    }

    public String getIMAGE_BASE_URL() {
        return IMAGE_BASE_URL;
    }

    public void setIMAGE_BASE_URL(String IMAGE_BASE_URL) {
        this.IMAGE_BASE_URL = IMAGE_BASE_URL;
    }
}
