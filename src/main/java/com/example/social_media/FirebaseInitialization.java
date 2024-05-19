package com.example.social_media;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseInitialization {
    @Bean
    public FirebaseApp createFireBaseApp() throws IOException {
            FileInputStream serviceAccount =
                    new FileInputStream("D:\\android\\social_media_v2\\src\\main\\resources\\serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
           return FirebaseApp.initializeApp(options);
    }
}
