package com.example.demo.mapper;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.entity.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationMapper extends EntityMapper<RegistrationDTO, Membership> {
}
