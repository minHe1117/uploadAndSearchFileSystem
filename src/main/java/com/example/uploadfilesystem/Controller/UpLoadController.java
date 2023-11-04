package com.example.uploadfilesystem.Controller;

import com.example.uploadfilesystem.Service.UpLoadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class UpLoadController {

    UpLoadFileService upLoadFileService;
    public UpLoadController(UpLoadFileService upLoadFileService) {
        this.upLoadFileService = upLoadFileService;
    }

    @PostMapping("uploads")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        return new ResponseEntity<>(upLoadFileService.uploadFile(file), HttpStatus.CREATED);

    }

}
