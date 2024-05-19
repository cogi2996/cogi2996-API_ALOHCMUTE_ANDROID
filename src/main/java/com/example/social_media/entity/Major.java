package com.example.social_media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="Nganh")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nganh_id")
    private int id;

    @Column(name="ten_nganh")
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="khoa_id",referencedColumnName = "khoa_id")
    private Department department;
}
