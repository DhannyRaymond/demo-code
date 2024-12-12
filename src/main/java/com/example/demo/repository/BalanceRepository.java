package com.example.demo.repository;

import com.example.demo.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM balance b WHERE b.membership_id = :membershipId")
    Balance getBalanceByMembershipId(@Param("membershipId") String membershipId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE balance SET balance = :balance WHERE membership_id = :membershipId and id = :id")
    void updateBalance(@Param("id") String id, @Param("membershipId") String membershipId, @Param("balance") Integer balance);
}
