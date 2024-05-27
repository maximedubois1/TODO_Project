package com.sp.repository;

import com.sp.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.userId IS NULL")
    List<Card> findAllWhereUserIdIsNull();

    List<Card> findAllByUserId(Long userId);
}
