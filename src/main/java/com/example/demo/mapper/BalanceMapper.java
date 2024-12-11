package com.example.demo.mapper;

import com.example.demo.dto.BalanceDTO;
import com.example.demo.entity.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BalanceMapper extends EntityMapper<BalanceDTO, Balance> {
}
