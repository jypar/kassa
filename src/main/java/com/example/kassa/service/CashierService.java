package com.example.kassa.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface CashierService {
    String getAuthenticatedUser();
}
