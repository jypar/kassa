package com.example.kassa.service;

import com.example.kassa.entity.Transfer;
import com.example.kassa.entity.TransferDto;
import com.example.kassa.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService{

    private final TransferRepository transferRepository;
    private final TransferDtoMapper transferDtoMapper;
    private final CashierService cashierService;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, TransferDtoMapper transferDtoMapper, CashierService cashierService) {
        this.transferRepository = transferRepository;
        this.transferDtoMapper = transferDtoMapper;
        this.cashierService = cashierService;
    }

    @Override
    public List<TransferDto> getAll() {
        return transferRepository.findAll().stream()
                .map(transferDtoMapper::convertToDto)
                //.filter(tr -> !tr.getCashier().getUsername().equals(cashierService.getAuthenticatedUser().getName()))
                .collect(Collectors.toList());
    }
    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public Transfer getbyId(Long id) {
        Transfer transfer = transferRepository.findById(id).get();

        return transfer;
    }
    @Override
    public List<TransferDto> search(String keyword) {
        List<TransferDto> searchResults;
        try {
            double v = Double.parseDouble(keyword);
            searchResults = transferRepository.search(keyword, Double.parseDouble(keyword)).stream().map(transferDtoMapper::convertToDto)
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            searchResults =  transferRepository.search(keyword, null).stream().map(transferDtoMapper::convertToDto)
                    .collect(Collectors.toList());
        }
        return searchResults;
    }

}
