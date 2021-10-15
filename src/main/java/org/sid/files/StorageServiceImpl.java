package org.sid.files;

import org.sid.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class StorageServiceImpl implements  StorageService{

    private final Path fileStoragePath;

    @Autowired
    public StorageServiceImpl(StorageProperties storageProperties) {
        this.fileStoragePath = Paths.get(storageProperties.getUploadDir() + "/images").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStoragePath);
        } catch (Exception ex){
            throw new StorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {

        String fileOriginName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFilename = RandomUtil.unique() + "-" + fileOriginName.toLowerCase().replaceAll(" ", "-");
        try{
            if(newFilename.contains("..")) {
                throw new StorageException("Sorry! Filename contains invalide path sequence " + newFilename);
            }
            else if(file.getSize() > 1000000000){
                throw new StorageException("Sorry! Size can not more then 1 000 000 KB ");
            }
            Path targetPath = this.fileStoragePath.resolve(newFilename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return newFilename;
        } catch (IOException ex){
            throw new StorageException("Sorry! Could not store file " + newFilename + ". Please try again!", ex);
        }

    }

    @Override
    public String getFileExtension(String fileOriginName) {
        int index = fileOriginName.lastIndexOf(".");
        if(index >= 0) return fileOriginName.substring(index + 1);
        return null;
    }

    @Override
    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = this.fileStoragePath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            } else {
                throw new StorageException("File not found " + filename);
            }
        } catch (MalformedURLException ex){
            throw new StorageException("File not found " + filename, ex);
        }
    }

}
