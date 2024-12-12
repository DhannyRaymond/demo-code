package com.example.demo.repository;

import com.example.demo.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {

    @Query(nativeQuery = true, value = "INSERT INTO membership (id, email, first_name, last_name, \"password\", profile_image) VALUES(:id, :email, :firstName, :lastName, :password, :profileImage) RETURNING *")
    Membership insertMembership(@Param("id") String id, @Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("password") String password, @Param("profileImage") String profileImage);

    @Query(nativeQuery = true, value = "SELECT * FROM membership m WHERE m.email = :email")
    Membership findByEmail(@Param("email") String email);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE membership SET first_name = :firstName, last_name = :lastName WHERE id = :id")
    void updateMembership(@Param("id") String id, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE membership SET profile_image = :profileImage where id = :id")
    void updateProfileImage(@Param("id") String id, @Param("profileImage") String profileImage);
}
