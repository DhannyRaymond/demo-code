package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "MEMBERSHIP")
public class Membership implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "email", length = 100)
    private String email;

    @NotNull
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = 100)
    private String lastName;

    @JsonIgnore
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_image", length = 256)
    private String profileImage;

    @OneToOne(mappedBy = "membership", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Balance balance;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public Membership(String email, String firstName, String lastName, String profileImage) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }
}
