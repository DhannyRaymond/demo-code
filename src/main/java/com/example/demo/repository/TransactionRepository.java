package com.example.demo.repository;

import com.example.demo.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findByMembershipIdOrderByCreatedOnDesc(String membershipId);

//    @Query(nativeQuery = true, value = "select * from transaction t where t.membership_id = :membershipId order by created_on desc limit :limit offset :offset")
//    List<Transaction> findByMembershipIdOrderByCreatedOnDesc(@Param("membershipId") String membershipId, @Param("limit") int limit, @Param("offset") int offset);

    Page<Transaction> findByMembershipIdOrderByCreatedOnDesc(String membershipId, Pageable pageable);
}
