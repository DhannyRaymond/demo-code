package com.example.demo.mapper;

import com.example.demo.dto.PartnerDTO;
import com.example.demo.entity.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartnerMapper extends EntityMapper<PartnerDTO, Partner> {
}
