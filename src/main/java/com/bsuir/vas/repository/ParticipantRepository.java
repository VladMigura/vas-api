package com.bsuir.vas.repository;

import com.bsuir.vas.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM participant " +
                    "WHERE id = :participantId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    ParticipantEntity findOneById(@Param("participantId") long participantId);

    @Query(value = "SELECT * " +
                    "FROM participant " +
                    "WHERE (:firstName IS NULL OR first_name = CAST(:firstName AS TEXT)) " +
                    "AND (:lastName IS NULL OR last_name = CAST(:lastName AS TEXT)) " +
                    "AND (:middleName IS NULL OR middle_name = CAST(:middleName AS TEXT)) " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    List<ParticipantEntity> findAllByParameters(@Param("firstName") String firstName,
                                                @Param("lastName") String lastName,
                                                @Param("middleName") String middleName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE participant " +
                    "SET deleted_at = now() " +
                    "WHERE id = :participantId ",
            nativeQuery = true)
    void deleteOneById(@Param("participantId") long participantId);
}
