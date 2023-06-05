package com.example.kassa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class TransferDto {


    private Long id;

    private String code;

    private double amount;

    private String currency;

    private String senderFullName;

    private String recipientFullName;

    private String senderPhoneNumber;

    private String recipientPhoneNumber;

    private String comment;

    private String creationDateTime;

    private String status;

    private Cashier cashier;

    public void setCode(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        if(!cashier.getUsername().equals(currentUsername)){
            this.code = null;
        }
    }
}
