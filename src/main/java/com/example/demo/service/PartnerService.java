package com.example.demo.service;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;
import com.example.demo.mapper.PartnerMapper;
import com.example.demo.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public List<PartnerDTO> getServices() {
        List<Partner> partners = partnerRepository.findAll();
        return partnerMapper.toDto(partners);
    }
}
