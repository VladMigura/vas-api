package com.bsuir.vas.repository;

import com.bsuir.vas.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM admin_table " +
                    "WHERE admin_name = CAST(:adminName AS TEXT) " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    AdminEntity findOneByAdminName(@Param("adminName") String adminName);

    @Query(value = "SELECT * " +
                    "FROM admin_table " +
                    "WHERE id = :adminId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    AdminEntity findOneById(@Param("adminId") long adminId);

    @Query(value = "SELECT * " +
                    "FROM admin_table " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    List<AdminEntity> findAll();

    @Modifying
    @Transactional
    @Query(value = "UPDATE admin_table " +
                    "SET deleted_at = now() " +
                    "WHERE id = :adminId ",
            nativeQuery = true)
    void deleteOneById(@Param("adminId") long adminId);
}
