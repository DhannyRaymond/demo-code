package com.example.demo.repository;

import com.example.demo.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {

    Membership findByEmail(String email);
}
