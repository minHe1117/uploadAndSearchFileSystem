package com.example.uploadfilesystem.Service;

import org.springframework.web.multipart.MultipartFile;

public interface UpLoadFileService {

   String uploadFile(MultipartFile file) throws Exception;
}
