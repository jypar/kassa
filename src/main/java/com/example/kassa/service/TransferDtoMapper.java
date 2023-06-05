package com.example.kassa.service;

import com.example.kassa.entity.Transfer;
import com.example.kassa.entity.TransferDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferDtoMapper {

    private final CashierService cashierService;
    private final ModelMapper modelMapper;
    @Autowired
    public TransferDtoMapper(CashierService cashierService, ModelMapper modelMapper){
        this.cashierService = cashierService;
        this.modelMapper = modelMapper;
    }

    public TransferDto convertToDto(Transfer transfer){
        TransferDto transferDto = modelMapper.map(transfer, TransferDto.class);

        if(!transferDto.getCashier().getUsername().equals(cashierService.getAuthenticatedUser())){
            transferDto.setCode(null);
        }
        return transferDto;
    }


}
