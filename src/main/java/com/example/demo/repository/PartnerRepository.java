package com.example.demo.repository;

import com.example.demo.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, String> {

    @Query(nativeQuery = true, value = "select * from partner p")
    List<Partner> getAllPartner();

    @Query(nativeQuery = true, value = "select * from partner p where p.service_code = :serviceCode")
    Partner findByServiceCode(String serviceCode);
}
