package com.imageurl.imageurlrespon.response;

public class FileResponse {

    String fileId;
    String fileName;
    String fileType;
    String fileUri;

    public FileResponse( String fileName, String fileId,String fileType, String fileUri) {
        this.fileName = fileName;
        this.fileId=fileId;
        this.fileType = fileType;
        this.fileUri = fileUri;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
