package com.uploadImage.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Data
//@Entity
//@Table(name = "Uploaduser")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column
//    private String name;
//    @Column
//    private String type;
//
//    @Lob
//    @Column(name = "profile_photo")
//    private byte[] profilePhoto;
//
//   
//}
@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
public class FileEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String contentType;
    
    @Lob
    @Column(nullable = false)
    private byte[] data;
    
    // Getters and setters
}