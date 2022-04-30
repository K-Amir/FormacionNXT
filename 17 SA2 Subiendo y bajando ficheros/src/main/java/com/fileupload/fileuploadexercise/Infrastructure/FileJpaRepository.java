package com.fileupload.fileuploadexercise.Infrastructure;

import com.fileupload.fileuploadexercise.Domain.File;
import com.fileupload.fileuploadexercise.Domain.FileRepository;
import com.fileupload.fileuploadexercise.Infrastructure.Jpa.FileImportedJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileJpaRepository implements FileRepository {
    private final FileImportedJpaRepository fileImportedJpaRepository;

    @Autowired
    public FileJpaRepository(FileImportedJpaRepository fileImportedJpaRepository) {
        this.fileImportedJpaRepository = fileImportedJpaRepository;
    }

    @Override
    public void saveFile(File file) {
        fileImportedJpaRepository.saveAndFlush(file);
    }

    @Override
    public File findFileById(int id) {
        return this.fileImportedJpaRepository.getById(id);
    }

    @Override
    public void deleteFileById(int id) {
        this.fileImportedJpaRepository.deleteById(id);
    }

    @Override
    public List<File> getAllFiles() {
        return fileImportedJpaRepository.findAll();
    }

    @Override
    public File updateFileById(int id, File file) {
        File oldFile = this.fileImportedJpaRepository.getById(id);
        oldFile.setFilename(file.getFilename());
        fileImportedJpaRepository.saveAndFlush(oldFile);
        return oldFile;
    }
}
