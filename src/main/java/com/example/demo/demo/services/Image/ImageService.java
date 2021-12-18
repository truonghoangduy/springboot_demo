package com.example.demo.demo.services.Image;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

//    public Message uploadFile(File file) throws IOException {
//        BlobId blobId =
//    }

}
