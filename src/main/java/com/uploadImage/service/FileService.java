package com.uploadImage.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.uploadImage.Entity.FileEntity;
import com.uploadImage.repository.FileRepository;

//
//	import java.io.IOException;
//import java.net.http.HttpHeaders;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.uploadImage.Entity.User;
//import com.uploadImage.repository.UserRepository;
//import com.uploadImage.util.ImageUtils;
//
//import jakarta.annotation.Resource;
//
//	@Service
//	public class ImageService {
//
//		@Autowired
//		private UserRepository userRepo;
//
//		public String uploadUser(MultipartFile file) throws IOException {
//			User userData = userRepo.save(User.builder().name(file.getOriginalFilename()).type(file.getContentType())
//					.profilePhoto(ImageUtils.compressImage(file.getBytes())).build());
//			if (userData != null) {
//				return "Userfile uploaded Sucesfully : " + file.getOriginalFilename();
//			} else {
//				return "No file is present";
//			}
//
//		}
//		public byte[] downloadUser(String name) {
//			User dbUserfile = userRepo.findByName(name);
//			byte[] userData = ImageUtils.decompressImage(dbUserfile.getProfilePhoto());
//			return userData;
//		}
		 
	//}   
@Service
public class FileService {
    
    private final FileRepository fileRepository;
    
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    
    public FileEntity saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();
        //byte[] data = ImageUtils.compressImage(file.getBytes());
        byte[] data = file.getBytes();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setContentType(contentType);
        fileEntity.setData(data);
        return fileRepository.save(fileEntity);
    }
    
    public ResponseEntity<ByteArrayResource> downloadFile(Long fileId) {
        Optional<FileEntity> optionalFileEntity = fileRepository.findById(fileId);
        if (optionalFileEntity.isPresent()) {
            FileEntity fileEntity = optionalFileEntity.get();
           // ByteArrayResource resource = new ByteArrayResource(ImageUtils.decompressImage(fileEntity.getData()));
            ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileEntity.getName());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileEntity.getData().length)
                    .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
                    .body(resource);
            
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
    }




}

