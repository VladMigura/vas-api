package com.bsuir.vas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "token")
public class TokenEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;

    @Column(name = "admin_id", updatable = false, insertable = false)
    private long adminId;
}
