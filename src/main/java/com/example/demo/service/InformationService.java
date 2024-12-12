package com.example.demo.service;

import com.example.demo.dto.BannerDTO;
import com.example.demo.entity.Banner;
import com.example.demo.mapper.BannerMapper;
import com.example.demo.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InformationService {

    private final InformationRepository informationRepository;
    private final BannerMapper bannerMapper;

    public List<BannerDTO> banner() {
        log.info("start processing get banner");
        List<Banner> banners = informationRepository.getAllBanner();
        log.info("end processing get banner");
        return bannerMapper.toDto(banners);
    }
}
