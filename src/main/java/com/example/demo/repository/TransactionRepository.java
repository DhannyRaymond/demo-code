package com.example.demo.repository;

import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction t where t.membership_id = :membershipId order by created_on desc")
    List<Transaction> findByMembershipIdOrderByCreatedOnDesc(String membershipId);

    @Query(nativeQuery = true, value = "SELECT * FROM transaction t where t.membership_id = :membershipId order by created_on desc limit :limit offset :offset")
    List<Transaction> findByMembershipIdOrderByCreatedOnDesc(@Param("membershipId") String membershipId, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query(nativeQuery = true, value = "INSERT INTO \"transaction\" (id, created_on, description, invoice_number, amount, transaction_type, membership_id) VALUES(:id, :createdOn, :description, :invoiceNumber, :amount, :transactionType, :membershipId) RETURNING *")
    Transaction insertTransaction(@Param("id") String id, @Param("createdOn") Instant createdOn, @Param("description") String description, @Param("invoiceNumber") String invoiceNumber, @Param("amount") Integer amount, @Param("transactionType") String transactionType, @Param("membershipId") String membershipId);
}
