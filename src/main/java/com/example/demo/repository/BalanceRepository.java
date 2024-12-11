package com.example.demo.repository;

import com.example.demo.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, String> {

    Balance findByMembershipId(String membershipId);
}
