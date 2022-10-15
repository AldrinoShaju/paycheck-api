package com.familyshop.paybackcheckapi.service;

import com.familyshop.paybackcheckapi.model.Transaction;
import com.familyshop.paybackcheckapi.model.TransactionRequest;

public interface TransactionService {

    Transaction getTranactionById(String custId, String txnId);
    void updateTransaction(String custId, String txnId, TransactionRequest request);
    void addTransaction(String custId, TransactionRequest request);

    void updateTransactionPayed(String custId, String txnId, int payedAmount);
}
