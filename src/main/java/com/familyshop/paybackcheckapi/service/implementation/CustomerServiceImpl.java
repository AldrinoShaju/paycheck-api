package com.familyshop.paybackcheckapi.service.implementation;

import com.familyshop.paybackcheckapi.model.Customer;
import com.familyshop.paybackcheckapi.model.CustomerRequest;
import com.familyshop.paybackcheckapi.respository.PayCheckRepo;
import com.familyshop.paybackcheckapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    PayCheckRepo repository;

    @Override
    public List<Customer> getAll() {
        List<Customer> allCustomers = repository.findAll();
        return allCustomers;
    }

    @Override
    public Customer getCustomer(String custId) {
        Optional<Customer> customer = repository.findById(custId);
        if(customer.isPresent()) {
            return customer.get();
        }

        return null;
    }

    @Override
    public void addCustomer(CustomerRequest request) {
        Customer newCustomer = new Customer();
        newCustomer.setCustName(request.getCustName());
        newCustomer.setPhoneNumber(request.getPhoneNumber());
        newCustomer.setCreatedOn(String.valueOf(System.currentTimeMillis()));
        newCustomer.setTotalPayable(0);
        newCustomer.setTxnList(new ArrayList<>());

        repository.save(newCustomer);



    }

    @Override
    public void updateCustomer(CustomerRequest request, String custId) {
        Customer customer = getCustomer(custId);

        if(customer!=null) {
            if(request.getCustName()!=null) {
                customer.setCustName(request.getCustName());
            }
            if(request.getPhoneNumber()!=null) {
                customer.setPhoneNumber(request.getPhoneNumber());
            }
        }

        repository.save(customer);
    }
}
