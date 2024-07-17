package com.uploadImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uploadImage.Entity.FileEntity;

//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//
//import com.uploadImage.Entity.User;
//
//import jakarta.transaction.Transactional;
//@Transactional
//public interface UserRepository extends  JpaRepository<User,Integer> {
//
//	User findByName(String name);
//       
//}
@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {}