package com.iotdreamclub.demo.entity;


import java.sql.Date;

public class FileUpload {

  private String fileId;
  private String fileName;
  private java.sql.Date fileDate;
  private String fileUploader;
  private String fileUrl;

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public java.sql.Date getFileDate() {
    return fileDate;
  }

  public void setFileDate(java.sql.Date fileDate) {
    this.fileDate = fileDate;
  }


  public String getFileUploader() {
    return fileUploader;
  }

  public void setFileUploader(String fileUploader) {
    this.fileUploader = fileUploader;
  }

}
