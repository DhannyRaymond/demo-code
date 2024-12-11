package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BANNER")
public class Banner implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "banner_name", length = 100)
    private String bannerName;

    @Column(name = "banner_image", length = 100)
    private String bannerImage;

    @Column(name = "description", length = 100)
    private String description;
}
