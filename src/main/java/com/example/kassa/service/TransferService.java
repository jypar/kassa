package com.example.kassa.service;

import com.example.kassa.entity.Transfer;
import com.example.kassa.entity.TransferDto;

import java.util.List;

public interface TransferService {
    List<TransferDto> getAll();
    void save(Transfer transfer);
    Transfer getbyId(Long id);
    List<TransferDto> search(String keyword);

}
