package com.fileupload.fileuploadexercise.Domain;

import java.util.List;

public interface FileRepository {

    void saveFile(File file);
    File findFileById(int id);

    void deleteFileById(int id);

    List<File> getAllFiles();

    File updateFileById(int id, File file);
}
