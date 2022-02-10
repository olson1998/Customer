package com.customer.app;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin

@RestController
public class CustomerController {

    @Resource
    private CustomerService cs; //logika biznesowa dla obiektu Customer

    @GetMapping
    @RequestMapping("/all")
    public List<Customer> allCustomers(){
        System.out.println("Returned all at " + LocalDateTime.now());
        return cs.getAllCustomers();
    }

    @GetMapping
    @RequestMapping("/search/{pesel}")
    public Optional<Customer> searchCustomer(@PathVariable String pesel){
        System.out.println("Returned" + cs.searchForCustomerByPesel(pesel).toString() + LocalDateTime.now());
        return cs.searchForCustomerByPesel(pesel);
    }

}
