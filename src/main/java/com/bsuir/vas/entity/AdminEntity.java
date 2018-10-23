package com.bsuir.vas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admin_table")
public class AdminEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "adminEntity")
    private List<TokenEntity> tokenEntities;
}
