package com.familyshop.paybackcheckapi.service.implementation;

import com.familyshop.paybackcheckapi.model.Customer;
import com.familyshop.paybackcheckapi.model.Transaction;
import com.familyshop.paybackcheckapi.model.TransactionRequest;
import com.familyshop.paybackcheckapi.respository.PayCheckRepo;
import com.familyshop.paybackcheckapi.service.CustomerService;
import com.familyshop.paybackcheckapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CustomerService customerService;

    @Autowired
    PayCheckRepo repository;


    @Override
    public Transaction getTransactionById(String custId, String txnId) {
        Customer customer = customerService.getCustomer(custId);
        if(customer!=null) {
            List<Transaction> txnList = customer.getTxnList();
            for(Transaction txn: txnList) {
                if(txn.getTxnId().equals(txnId)) {
                    return txn;
                }
            }

            return null;
        }

        return null;
    }

    @Override
    public void updateTransaction(String custId, String txnId, TransactionRequest request) {
        Customer customer = customerService.getCustomer(custId);
        if(customer!=null) {
            List<Transaction> txnList = customer.getTxnList();
            for(Transaction txn: txnList) {
                if(txn.getTxnId().equals(txnId)) {
                    if(request.getTxnNote()!=null) {
                        txn.setTxnNote(request.getTxnNote());
                    }
                    if(request.getTotalAmount()!=null) {
                        int prevPayable = txn.getPayable();
                        int newTotalPayable = customer.getTotalPayable() - prevPayable;
                        txn.setTotalAmount(request.getTotalAmount());
                        txn.setPayable(txn.getTotalAmount() - request.getPayed());
                        newTotalPayable = newTotalPayable + txn.getPayable();
                        customer.setTotalPayable(newTotalPayable);
                    }
                    txn.setUpdatedOn(String.valueOf(System.currentTimeMillis()));
                    break;
                }
            }
            repository.save(customer);
        }
    }

    @Override
    public void addTransaction(String custId, TransactionRequest request) {
        Customer customer = customerService.getCustomer(custId);

        if(customer!=null) {
            Transaction txn = new Transaction();
            txn.setTxnId(String.valueOf(customer.getTxnList().size()+1));
            txn.setTxnNote(request.getTxnNote());
            txn.setTotalAmount(request.getTotalAmount());
            txn.setPayable(request.getTotalAmount() - request.getPayed());
            customer.setTotalPayable(customer.getTotalPayable() + txn.getPayable());
            txn.setCreatedOn(String.valueOf(System.currentTimeMillis()));
            txn.setUpdatedOn(String.valueOf(System.currentTimeMillis()));

            customer.getTxnList().add(txn);

            repository.save(customer);
        }
    }

    @Override
    public void updateTransactionPayed(String custId, String txnId, int payedAmount) {
        Customer customer = customerService.getCustomer(custId);

        if(customer!=null) {
            List<Transaction> txnList = customer.getTxnList();
            for(Transaction txn: txnList) {
                if(txn.getTxnId().equals(txnId)) {
                    txn.setPayable(txn.getPayable() - payedAmount);
                    customer.setTotalPayable(customer.getTotalPayable() - payedAmount);
                    txn.setUpdatedOn(String.valueOf(System.currentTimeMillis()));
                    break;
                }
            }
            repository.save(customer);
        }
    }
}
