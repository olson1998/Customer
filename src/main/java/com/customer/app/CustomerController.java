package com.customer.app;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
        System.out.println("Returned all at: " + getCurrentTime()); //wyświetlenie w konsoli informacji
        return cs.getAllCustomers();
    }

    @GetMapping
    @RequestMapping("/search/{pesel}")
    public Optional<Customer> searchCustomer(@PathVariable String pesel){
        //wyświetlenie w konsoli informacji
        System.out.println("Returned" + cs.searchForCustomerByPesel(pesel).toString() + " at: "  +getCurrentTime());
        return cs.searchForCustomerByPesel(pesel);
    }

    //zwraca czas lokalny
    private LocalDateTime getCurrentTime(){
        return LocalDateTime.now().withNano(0);
    }

}
