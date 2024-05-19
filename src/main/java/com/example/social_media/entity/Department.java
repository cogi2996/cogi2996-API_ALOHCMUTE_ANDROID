package com.example.social_media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="khoa")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="khoa_id")
    private int id;
    @Column(name="ten_khoa")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Major> majors;

}
