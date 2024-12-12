package com.example.demo.controller;

import com.example.demo.dto.ResponseModel;
import com.example.demo.exception.CustomException;
import com.example.demo.service.InformationService;
import com.example.demo.service.PartnerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/module/information")
@RequiredArgsConstructor
public class InformationController {

    private final InformationService informationService;
    private final PartnerService partnerService;

    @GetMapping("/banner")
    public ResponseEntity<ResponseModel<Object>> banner() throws CustomException {
        log.info("start get banner");
        return ResponseEntity.ok(new ResponseModel<>(0, "Sukses", informationService.banner()));
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseModel<Object>> services(HttpServletRequest request) throws CustomException, IOException {
        log.info("start get service");
        return ResponseEntity.ok(new ResponseModel<>(0, "Sukses", partnerService.getServices(request)));
    }
}
