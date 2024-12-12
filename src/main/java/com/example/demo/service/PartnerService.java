package com.example.demo.service;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;
import com.example.demo.exception.CustomException;
import com.example.demo.mapper.PartnerMapper;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.utils.ValidationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public List<PartnerDTO> getServices(HttpServletRequest request) throws CustomException, IOException {
        String email = ValidationUtils.validateLogin(request);

        log.info("start processing get service with email: {}", email);
        List<Partner> partners = partnerRepository.findAll();
        log.info("end processing get service with email: {}", email);
        return partnerMapper.toDto(partners);
    }
}
