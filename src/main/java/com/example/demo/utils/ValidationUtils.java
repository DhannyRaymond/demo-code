package com.example.demo.utils;

import com.example.demo.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
public class ValidationUtils {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    public static void validateEmail(String email) throws CustomException {
        if (StringUtils.isEmpty(email)) {
            throw new CustomException(102, "Parameter email tidak boleh kosong", null, HttpStatus.BAD_REQUEST);
        } else if (!email.contains("@") || !email.contains(".com")) {
            throw new CustomException(102, "Parameter email tidak sesuai format", null, HttpStatus.BAD_REQUEST);
        }
    }

    public static void validatePassword(String requestPassword, String dbPassword, String source) throws CustomException {

        if ("registration".equals(source)) {
            if (StringUtils.isEmpty(requestPassword)) {
                throw new CustomException(102, "Parameter password tidak boleh kosong", null, HttpStatus.BAD_REQUEST);
            } else if (requestPassword.length() < 8) {
                throw new CustomException(102, "Parameter password minimal 8 karakter", null, HttpStatus.BAD_REQUEST);
            }
        } else if ("login".equals(source)) {
            if (!passwordEncoder.matches(requestPassword,dbPassword)) {
                throw new CustomException(103, "Username atau password salah", null, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    public static void validateFile(MultipartFile file) throws CustomException {
        String imageType = file.getContentType();
        if (file.isEmpty()) {
            throw new CustomException(102, "Image tidak boleh kosong", null, HttpStatus.BAD_REQUEST);
        } else if (!"image/jpeg".equals(imageType) && !"image/png".equals(imageType)) {
            throw new CustomException(102, "Format Image tidak sesuai", null, HttpStatus.BAD_REQUEST);
        }
    }

    public static String validateLogin(HttpServletRequest request) throws CustomException, IOException {
        try {
            String email = JwtUtils.getEmailFromRequest(request);
            if (StringUtils.isEmpty(email)) {
                throw new CustomException(108, "Token tidak tidak valid atau kadaluwarsa", null, HttpStatus.UNAUTHORIZED);
            }

            return email;
        } catch (IOException e) {
            throw new CustomException(108, "Token tidak tidak valid atau kadaluwarsa", null, HttpStatus.UNAUTHORIZED);
        }
    }

    public static Integer validateTopup(Object amount) throws CustomException {
        if (amount instanceof String) {
            throw new CustomException(102, "Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 0", null, HttpStatus.BAD_REQUEST);
        }

        if (amount instanceof Integer && 0 >= (Integer) amount) {
            throw new CustomException(102, "Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 0", null, HttpStatus.BAD_REQUEST);
        }

        return (Integer) amount;
    }
}
