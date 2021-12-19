package com.example.demo.demo.services.Image;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageService {
    public ImageService() {

    }
    @PostConstruct
    public void initFirebase() throws IOException {
        InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("key.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("demobackbonejs-3fa77.appspot.com")
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        Bucket bucket = StorageClient.getInstance().bucket();
    }

    public static String uploadFile(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        String name =  UUID.randomUUID().toString() + StringUtils.getFilenameExtension(file.getOriginalFilename());
        bucket.create(name, file.getBytes(), file.getContentType());

        return name;
    }

}
