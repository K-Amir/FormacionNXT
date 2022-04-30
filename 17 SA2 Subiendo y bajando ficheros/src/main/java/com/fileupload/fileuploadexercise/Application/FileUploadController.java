package com.fileupload.fileuploadexercise.Application;

import com.fileupload.fileuploadexercise.Domain.File;
import com.fileupload.fileuploadexercise.Domain.FileRepository;
import com.fileupload.fileuploadexercise.Domain.StorageService;
import com.fileupload.fileuploadexercise.Infrastructure.Jpa.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;


@RestController
@RequestMapping("files")
public class FileUploadController {

    private final StorageService storageService;
    private final FileRepository fileRepository;

    private final StorageProperties properties;



    @Autowired
    public FileUploadController(StorageService storageService, FileRepository fileRepository, StorageProperties properties) {
        this.storageService = storageService;
        this.fileRepository = fileRepository;
        this.properties = properties;
    }

    @GetMapping("/setpath")
    public ResponseEntity<?> setFilePath(@PathParam("path") String path) throws IOException {
        properties.setLocation(path);
        return ResponseEntity.ok().body("Filepath changed successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable String id){
        File file = fileRepository.findFileById(Integer.parseInt(id));
        return getFileByNameAndReturn(file.getFilename());
    }

    @GetMapping()
    public ResponseEntity<Resource> getFileByName(@PathParam("filename") String filename){
        return getFileByNameAndReturn(filename);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathParam("filetype") String filetype) {


        if(!file.getContentType().equals(filetype)){
            return ResponseEntity.badRequest().body("The file type specified does not match with the file type submitted");
        }


        storageService.store(file);

        fileRepository.saveFile(new File(file.getOriginalFilename(), file.getContentType()));

        return ResponseEntity.ok().body("it worked");

    }

    private ResponseEntity<Resource> getFileByNameAndReturn(String fileName) {
        Resource fileOutput = storageService.loadAsResource(fileName);
        String fileHeader = String.format("attachment; filename=\"%s\"", fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, fileHeader).body(fileOutput);
    }

}
