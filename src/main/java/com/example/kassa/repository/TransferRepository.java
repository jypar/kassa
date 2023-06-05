package com.example.kassa.repository;

import com.example.kassa.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

    @Query("SELECT t FROM Transfer t WHERE t.senderPhoneNumber=:keyword OR t.senderFullName = :keyword OR t.recipientFullName = :keyword OR " +
            "t.recipientPhoneNumber = :keyword OR t.status = :keyword or t.amount  = :keywordAsDouble OR " +
            "t.currency = :keyword OR t.comment = :keyword OR t.code = :keyword ")
    List<Transfer> search(String keyword, Double keywordAsDouble);

}
