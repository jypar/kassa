package com.example.kassa.controller;


import com.example.kassa.entity.Cashier;
import com.example.kassa.entity.Transfer;
import com.example.kassa.entity.TransferDto;
import com.example.kassa.repository.CashierRepository;
import com.example.kassa.service.CashierService;
import com.example.kassa.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class TransferController {
    private final TransferService transferService;
    private final CashierService cashierService;
    private final CashierRepository cashierRepository;

   @Autowired
    public TransferController(TransferService transferService, CashierService cashierService, CashierRepository cashierRepository) {
        this.transferService = transferService;
       this.cashierService = cashierService;
       this.cashierRepository = cashierRepository;
   }

    @GetMapping
    public String getAllTransfers(Model model) {
        Cashier cashier = cashierRepository.findByUsername(cashierService.getAuthenticatedUser());
        Long id = cashier.getId();
        model.addAttribute("transfers", transferService.getAll());
        model.addAttribute("id", id);
        return "transfers";

    }
    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword) {
        List<TransferDto> transfers = transferService.search(keyword);
        model.addAttribute("transfers", transfers);
        model.addAttribute("keyword", keyword);

        return "transfers";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("transfer", new Transfer());
        return "create";
    }

    @PostMapping("/save")
    public String createTransfer(@ModelAttribute("transfer") Transfer transfer) {
        Cashier cashier = cashierRepository.findByUsername(cashierService.getAuthenticatedUser());
        cashier.setBalance(cashier.getBalance() + transfer.getAmount());
        transfer.setStatus("СОЗДАН");
        transfer.setCashier(cashier);
        transfer.setCreationDateTime(LocalDate.now().toString());
        transfer.setCode(String.valueOf(new Random().nextInt(9000)+1000));
        transferService.save(transfer);
        return "redirect:/";
    }

    @PostMapping("/process")
    public String process(@RequestParam("transferId") Long transferId, @RequestParam("code") String code,  RedirectAttributes redirectAttributes) {


        Transfer transfer = transferService.getbyId(transferId);

        if (code.equals(transfer.getCode())) {
            Cashier cashier = cashierRepository.findByUsername(cashierService.getAuthenticatedUser());
            cashier.setBalance(cashier.getBalance() - transfer.getAmount());
            transfer.setStatus("ВЫДАН");
            transferService.save(transfer);

            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Incorrect code");
            return "submitError";
        }
    }

}
