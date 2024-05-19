package com.example.social_media.rest;

import com.example.social_media.DTO.*;
import com.example.social_media.Utils.OTPGenerator;
import com.example.social_media.entity.Account;
import com.example.social_media.entity.User;
import com.example.social_media.security.AuthenticationFacade;
import com.example.social_media.security.IAuthenticationFacade;
import com.example.social_media.security.Role;
import com.example.social_media.service.AccountService;
import com.example.social_media.service.AuthenticationService;
import com.example.social_media.service.EmailService;
import com.example.social_media.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final IAuthenticationFacade authenticationFacade;
    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AccountDTO accountDTO) throws FirebaseAuthException {
        accountDTO.getUserDTO().setCreateDate(new Date(System.currentTimeMillis()));
        Account newAccount = convertToAccountEntity(accountDTO);
        User newUser = convertToUserEntity(accountDTO.getUserDTO());
        // không phải admin thì new user là user hết
        if(!(authenticationFacade.getRole() == Role.ADMIN)){
            newAccount.setRole(Role.USER);
        }
        AuthenticationResponse token =  authenticationService.register(newAccount,newUser);
        String otp = OTPGenerator.generateOTP();
        emailService.SendEmail(accountDTO.getEmail(), "OTP alohcmute, please don't leak this!", otp);
        return  ResponseEntity.ok(token);
    }

    @PostMapping("/register/OTP")
    public ResponseEntity<?> registerOTP(@RequestBody RequestOTP otp) throws FirebaseAuthException {
        System.out.println("OTPHERE");
        System.out.println(otp.getOtp());
        User user = authenticationFacade.getUser();
        // save user
        Account account = user.getAccount();
        user.setCreateDate(new Date(System.currentTimeMillis()));
        account.setUser(user);
        account.setRole(Role.USER);
        // valid user && account
        account.setStatus(true);
        accountService.save(account);
        return ResponseEntity.ok("done");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassRequest changePassRequest){
        authenticationService.changePassword(changePassRequest);
        ModelMap modelMap = new ModelMap();
        return ResponseEntity.ok(ResponseDTO.builder().message("Change password successfully").build());
    }

    private Account convertToAccountEntity(AccountDTO accountDTO){
        return modelMapper.map(accountDTO,Account.class);
    }

    private User convertToUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }


}
