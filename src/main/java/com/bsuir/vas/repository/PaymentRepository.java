package com.bsuir.vas.repository;

import com.bsuir.vas.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query(value = "SELECT * " +
                    "FROM payment " +
                    "WHERE user_id = :participantId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    List<PaymentEntity> findAllByParticipantId(@Param("participantId") long ParticipantId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE payment " +
                    "SET deleted_at = now() " +
                    "WHERE user_id = :participantId " +
                    "AND year IN (:paymentYears) ",
            nativeQuery = true)
    void deleteAllByParameters(@Param("participantId") long participantId,
                               @Param("paymentYears") List<Long> paymentYears);
}
