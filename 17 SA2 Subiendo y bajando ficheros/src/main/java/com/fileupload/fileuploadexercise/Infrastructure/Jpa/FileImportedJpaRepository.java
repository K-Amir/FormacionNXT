package com.fileupload.fileuploadexercise.Infrastructure.Jpa;

import com.fileupload.fileuploadexercise.Domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileImportedJpaRepository extends JpaRepository<File, Integer> {
}
