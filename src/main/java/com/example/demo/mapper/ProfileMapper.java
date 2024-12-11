package com.example.demo.mapper;

import com.example.demo.dto.MembershipDTO;
import com.example.demo.entity.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper extends EntityMapper<MembershipDTO, Membership> {
}
