package com.example.demo.repository;

import com.example.demo.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Banner, String> {

    @Query(nativeQuery = true, value = "select * from banner b")
    List<Banner> getAllBanner();
}
