package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.ResponseModel;
import com.example.demo.exception.CustomException;
import com.example.demo.service.MembershipService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/module/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseModel<Object>> registration(@RequestBody RegistrationDTO registrationDTO) throws CustomException {
        return ResponseEntity.ok(new ResponseModel<>(0, membershipService.registration(registrationDTO), null));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<Object>> login(@RequestBody LoginDTO loginDTO) throws CustomException {
        return ResponseEntity.ok(new ResponseModel<>(0, "Login Sukses", membershipService.login(loginDTO)));
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseModel<Object>> profile(HttpServletRequest request) throws CustomException, IOException {
        // TODO: UNAUTHORIZE HANDLING STILL GOT 403 INSTEAD 401 WHEN TOKEN EXPIRED
        return ResponseEntity.ok(new ResponseModel<>(0, "Sukses", membershipService.getProfile(request)));
    }

    @PutMapping("/profile/update")
    public ResponseEntity<ResponseModel<Object>> updateProfile(HttpServletRequest request, @RequestBody ProfileDTO profileDTO) throws CustomException, IOException {
        // TODO: UNAUTHORIZE HANDLING STILL GOT 403 INSTEAD 401 WHEN TOKEN EXPIRED
        return ResponseEntity.ok(new ResponseModel<>(0, "Update Pofile berhasil", membershipService.updateProfile(request, profileDTO)));
    }

    @PutMapping("/profile/image")
    public ResponseEntity<ResponseModel<Object>> updateProfileImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws CustomException, IOException {
        // TODO: UNAUTHORIZE HANDLING STILL GOT 403 INSTEAD 401 WHEN TOKEN EXPIRED
        return ResponseEntity.ok(new ResponseModel<>(0, "Update Profile Image berhasil", membershipService.updateProfileImage(request, file)));
    }
}
