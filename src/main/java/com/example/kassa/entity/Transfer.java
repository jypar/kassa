package com.example.kassa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")

    private String currency;

    @Column(name = "sender_full_name")
    private String senderFullName;

    @Column(name = "recipient_full_name")
    private String recipientFullName;

    @Column(name = "sender_phone_number")
    private String senderPhoneNumber;

    @Column(name = "recipient_phone_number")
    private String recipientPhoneNumber;

    @Column(name = "comment")
    private String comment;

    @Column(name = "creation_date_time")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String creationDateTime;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    private Cashier cashier;
}
