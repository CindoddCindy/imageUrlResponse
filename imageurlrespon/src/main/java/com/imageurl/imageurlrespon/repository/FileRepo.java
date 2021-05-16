package com.imageurl.imageurlrespon.repository;


import com.imageurl.imageurlrespon.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String>{
}
