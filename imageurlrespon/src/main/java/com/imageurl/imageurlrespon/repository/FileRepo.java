package com.imageurl.imageurlrespon.repository;


import com.imageurl.imageurlrespon.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String>{

    Optional<FileModel> findByName(String fileName);
}
