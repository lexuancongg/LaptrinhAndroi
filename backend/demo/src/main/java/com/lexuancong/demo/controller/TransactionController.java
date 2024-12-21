package com.lexuancong.demo.controller;

import com.lexuancong.demo.service.TransactionService;
import com.lexuancong.demo.viewmodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private  final  TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/create")
    public ResponseEntity<TransactionVm> create(@RequestBody TransactionPostVm transactionPostVm){
        System.out.println("this ");
        return ResponseEntity.ok(transactionService.create(transactionPostVm));

    }


    @GetMapping("/list-transactions")
    public ResponseEntity<List<TransactionVm>> getListTransactions(@RequestParam String fromDate,
                                                                   @RequestParam String toDate){
          return ResponseEntity.ok(transactionService.getTransactions(fromDate,toDate));
    }

}
