package com.familyshop.paybackcheckapi.controller;

import com.familyshop.paybackcheckapi.model.Customer;
import com.familyshop.paybackcheckapi.model.CustomerRequest;
import com.familyshop.paybackcheckapi.model.Response;
import com.familyshop.paybackcheckapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Response> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAll();
        Response<List<Customer>> response = new Response<>(HttpStatus.OK.value(), allCustomers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity getCustomerDetails(@RequestParam("id") String id) {
        Customer customer = customerService.getCustomer(id);
        Response<Customer> response = new Response<>(HttpStatus.OK.value(), customer);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerRequest request) {
        customerService.addCustomer(request);
        Response<String> response = new Response<>(HttpStatus.CREATED.value(), "New Customer added to DB");
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateCustomer(@RequestBody CustomerRequest request, @RequestParam("id") String id) {
        customerService.updateCustomer(request, id);
        Response<String> response =new Response<>(HttpStatus.CREATED.value(), "Customer details updated");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteCustomer(@RequestParam("id") String id) {
        Response<String> response = new Response<>(HttpStatus.OK.value(), "Not Allowed to Delete customer");
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
