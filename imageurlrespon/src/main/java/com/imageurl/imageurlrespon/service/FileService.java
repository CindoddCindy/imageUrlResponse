package com.imageurl.imageurlrespon.service;

import com.imageurl.imageurlrespon.constants.FileErrors;
import com.imageurl.imageurlrespon.exception.FileNotFoundException;
import com.imageurl.imageurlrespon.exception.FileSaveException;
import com.imageurl.imageurlrespon.model.FileModel;
import com.imageurl.imageurlrespon.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepo fileRepo;

    public FileModel saveFile(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (filename.contains("...")) {
                throw new FileSaveException(FileErrors.INVALID_FILE + filename);
            }

            FileModel model = new FileModel(filename, file.getContentType(), file.getBytes());
            return fileRepo.save(model);

        } catch (Exception e) {

            throw new FileSaveException(FileErrors.FILE_NOT_STORED, e);
        }

    }

    public FileModel getFile(String fileName) {

        return fileRepo.findByName(fileName).orElseThrow(() -> new FileNotFoundException(FileErrors.FILE_NOT_FOUND + fileName));
    }

    public FileModel getFiles(String id) {
        return fileRepo.findById(id).get();
    }

    public List<FileModel> getListOfFiles(){

        return fileRepo.findAll();
    }
}
