package com.imageurl.imageurlrespon.controller;

import com.imageurl.imageurlrespon.model.FileModel;
import com.imageurl.imageurlrespon.response.FileResponse;
import com.imageurl.imageurlrespon.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/File")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/Upload")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        FileModel model = fileService.saveFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").
                path(model.getFileId()).toUriString();
        return new FileResponse(model.getFileName(), model.getFileType(), fileUri);
    }

    @PostMapping("/UploadMultipleFiles")
    public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).
                stream().
                map(file -> uploadFile(file)).
                collect(Collectors.toList());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        FileModel model = fileService.getFile(id);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + model.getFileName() + "\"").
                body(new ByteArrayResource(model.getFileData()));


    }


    @GetMapping("/Allfiles")
    public  List<FileModel>  getListFiles(Model model) {
        List<FileModel> fileDetails = fileService.getListOfFiles();

        return fileDetails;
    }
}
