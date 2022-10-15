package com.familyshop.paybackcheckapi.service;

import com.familyshop.paybackcheckapi.model.Customer;
import com.familyshop.paybackcheckapi.model.CustomerRequest;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getCustomer(String custId);
    void addCustomer(CustomerRequest request);
    void updateCustomer(CustomerRequest request, String custId);
}
