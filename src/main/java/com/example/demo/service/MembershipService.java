package com.example.demo.service;

import com.example.demo.config.ApplicationProperties;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.MembershipDTO;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.entity.Balance;
import com.example.demo.entity.Membership;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.ProfileMapper;
import com.example.demo.mapper.RegistrationMapper;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.MembershipRepository;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final BalanceRepository balanceRepository;

    private final PasswordEncoder passwordEncoder;
    private final ApplicationProperties applicationProperties;
    private final RegistrationMapper registrationMapper;
    private final ProfileMapper profileMapper;

    public String registration(RegistrationDTO registrationDTO) throws CustomException, JsonProcessingException {
        String message = CommonUtils.convertUsingJackson(registrationDTO);
        log.info("start processing registration with payload: {}", message);
        ValidationUtils.validateEmail(registrationDTO.getEmail());
        ValidationUtils.validatePassword(registrationDTO.getPassword(), null, "registration");
        String membershipId = "MEMBER_".concat(String.valueOf(Instant.now().toEpochMilli()));

        String encoderPassword = passwordEncoder.encode(registrationDTO.getPassword());
        Membership membership = membershipRepository.insertMembership(membershipId, registrationDTO.getEmail(), registrationDTO.getFirstName(), registrationDTO.getLastName(), encoderPassword, "");
        balanceRepository.save(new Balance(membership, 0));

        log.info("end processing registration with payload: {}", message);
        return "Registrasi berhasil silahkan login";
    }

    public Map<String, String> login(LoginDTO loginDTO) throws CustomException, JsonProcessingException {
        String message = CommonUtils.convertUsingJackson(loginDTO);
        log.info("start processing login with payload: {}", message);
        Membership membership = membershipRepository.findByEmail(loginDTO.getEmail());
        ValidationUtils.validateEmail(loginDTO.getEmail());
        ValidationUtils.validatePassword(loginDTO.getPassword(), membership.getPassword(), "login");

        String token = JwtUtils.generateToken(loginDTO.getEmail());

        log.info("end processing login with payload: {}", message);
        return Map.of("token", token);
    }

    public MembershipDTO getProfile(HttpServletRequest request) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing get profile with email: {}", email);
        Membership membership = membershipRepository.findByEmail(email);
        log.info("end processing get profile with email: {}", email);
        return profileMapper.toDto(membership);
    }

    public MembershipDTO updateProfile(HttpServletRequest request, ProfileDTO profileDTO) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing update profile with email: {}", email);
        Membership membership = membershipRepository.findByEmail(email);
        membershipRepository.updateMembership(membership.getId(), profileDTO.getFirstName(), profileDTO.getLastName());

        log.info("end processing update profile with email: {}", email);
        return profileMapper.toDto(new Membership(email, profileDTO.getFirstName(), profileDTO.getLastName(), membership.getProfileImage()));
    }

    public MembershipDTO updateProfileImage(HttpServletRequest request, MultipartFile file) throws CustomException, IOException {
        String UPLOAD_DIR = applicationProperties.getBaseUrl();
        String email = ValidationUtils.validateLogin(request);
        log.info("start processing update profile image with email: {}", email);
        ValidationUtils.validateFile(file);
        Membership membership = membershipRepository.findByEmail(email);

        try {
            String fileName = file.getOriginalFilename();
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path path = Paths.get(System.getProperty("user.dir").concat(UPLOAD_DIR), String.valueOf(Instant.now().toEpochMilli()).concat(fileName));
            Files.copy(file.getInputStream(), path);

            membershipRepository.updateProfileImage(membership.getId(), path.toString());
            log.info("end processing update profile image with email: {}", email);
            return profileMapper.toDto(membership);
        } catch (IOException e) {
            log.error("File upload failed: {}", e.getMessage());
            throw new CustomException(102, "File upload failed", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
