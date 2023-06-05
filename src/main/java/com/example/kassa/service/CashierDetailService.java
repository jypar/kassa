package com.example.kassa.service;

import com.example.kassa.entity.Cashier;
import com.example.kassa.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CashierDetailService implements UserDetailsService {

    private final CashierRepository   cashierRepository;
    @Autowired
    public CashierDetailService(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cashier cashier = cashierRepository.findByUsername(username);
        if (cashier == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new CashierUserDetails(cashier);

    }
}
