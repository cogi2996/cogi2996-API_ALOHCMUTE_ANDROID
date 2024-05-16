package com.example.social_media.config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FireBaseService {
    // add user into firebase
    public void addUser(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setEmailVerified(false)
                .setPassword(password)
                .setDisabled(false);
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());
    }


}
