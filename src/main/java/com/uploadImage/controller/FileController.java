package com.uploadImage.controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploadImage.Entity.FileEntity;
import com.uploadImage.service.FileService;



//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//
//import com.uploadImage.service.ImageService;
//
//@RestController
//@RequestMapping("/upload")
//public class  UserController {
//	
//	@Autowired 
//	private ImageService imageService;
//	
//	 @PostMapping("/User")
//	 public ResponseEntity<?> uploadUser(@RequestParam("user") MultipartFile file ) throws IOException  {
//		 String imagedata = imageService.uploadUser(file);
//		return  ResponseEntity.status(HttpStatus.OK)
//				.body(imagedata);
//		 
//	     	    }
//   
//	 @GetMapping("/download/{name}")
//	 public ResponseEntity<?> downloadUser(@PathVariable String name) throws IOException  {
//		 byte[] userDownloadtails = imageService.downloadUser(name);
//		return  ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("plane/text")) //plane/text  image/png
//				.body(userDownloadtails);
//		 
//	     	    }        
//	    
//		
//	}
@RestController
@RequestMapping("/files")
public class FileController {
    
    private final FileService fileService;
    
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity savedFile = fileService.saveFile(file);
        return ResponseEntity.created(URI.create("/files/" + savedFile.getId())).body(savedFile);
    }
    
    @GetMapping("/{fileId}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
        return fileService.downloadFile(fileId);
    }
}

