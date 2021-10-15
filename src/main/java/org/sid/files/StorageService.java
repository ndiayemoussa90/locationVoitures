package org.sid.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String storeFile(MultipartFile file);

    String getFileExtension(String filename);

    Resource loadFileAsResource(String filename);

}
