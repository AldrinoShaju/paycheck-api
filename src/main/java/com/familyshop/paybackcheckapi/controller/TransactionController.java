package com.familyshop.paybackcheckapi.controller;

import com.familyshop.paybackcheckapi.model.Response;
import com.familyshop.paybackcheckapi.model.Transaction;
import com.familyshop.paybackcheckapi.model.TransactionRequest;
import com.familyshop.paybackcheckapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("txn")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity getTransactionById(@RequestParam("id") String txnId, @RequestParam String custId) {
        Transaction txn = transactionService.getTransactionById(custId, txnId);
        Response<Transaction> response = new Response<>(HttpStatus.OK.value(), txn);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTransactionToCustomer(@RequestParam("id") String id, @RequestBody TransactionRequest request) {
        transactionService.addTransaction(id, request);

        Response<String> response = new Response<>(HttpStatus.CREATED.value(), "Txn Added to customer "+id);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateTransactionById(@RequestParam String custId,
                                                @RequestParam("id") String txnId, @RequestBody TransactionRequest request) {
        transactionService.updateTransaction(custId, txnId, request);

        Response<String> response = new Response<>(HttpStatus.CREATED.value(), "Updated txn "+txnId);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("pay")
    public ResponseEntity updateTransactionPayedById(@RequestParam String custId,
                                                     @RequestParam String txnId, @RequestParam int payed) {
        transactionService.updateTransactionPayed(custId, txnId, payed);
        Response<String> response = new Response<>(HttpStatus.CREATED.value(), "Updated txn "+txnId);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteTransactionById(@RequestParam("id") String id) {
        Response<String> response = new Response<>(HttpStatus.FORBIDDEN.value(), "Not allowed to delete transactions");
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
}
