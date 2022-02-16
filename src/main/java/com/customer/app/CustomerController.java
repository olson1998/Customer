package com.customer.app;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService cs;     //logika biznesowa dla obiektu Customer

    @GetMapping
    @RequestMapping("/filtered/")
    public List<Customer> allCustomers(@RequestBody List<Integer> ids){
        List<Customer> cc = cs.getFilteredCustomers(ids); //certain clients
        System.out.println("Returned certain clients: " + cc.toString() + " at: " + getCurrentTime());      //wyświetlenie w konsoli informacji
        return cc;
    }

    @GetMapping
    @RequestMapping("/search/{pesel}")
    public Optional<Customer> searchCustomer(@PathVariable String pesel){
        //wyświetlenie w konsoli informacji
        System.out.println("Returned" + cs.searchForCustomerByPesel(pesel).toString() + " at: "  +getCurrentTime());
        return cs.searchForCustomerByPesel(pesel);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public int createCustomer(@RequestBody Customer customer){
        int id = cs.generateNewId();
        customer.setId(id);
        System.out.println(customer.toString());
        cs.checkAndSave(customer);
        return id;
    }

    //zwraca czas lokalny
    private ZonedDateTime getCurrentTime(){
        return ZonedDateTime.now().withNano(0);
    }

}
