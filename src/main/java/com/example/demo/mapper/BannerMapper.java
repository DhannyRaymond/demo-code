package com.example.demo.mapper;

import com.example.demo.dto.BannerDTO;
import com.example.demo.entity.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BannerMapper extends EntityMapper<BannerDTO, Banner> {
}
