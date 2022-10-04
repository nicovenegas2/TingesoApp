package com.example.TingesoApp.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
    private String folder="files" + File.pathSeparator;
    private String finalName="DATA.txt";

    public String save(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(folder + finalName);
                Files.write(path, bytes);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return "";
    }
}
