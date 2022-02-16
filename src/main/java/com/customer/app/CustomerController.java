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
        displayConsoleMessage("Returned certain clients: " + cc.toString());
        return cc;
    }

    @GetMapping
    @RequestMapping("/search/{pesel}")
    public Optional<Customer> searchCustomer(@PathVariable String pesel){
        //wyświetlenie w konsoli informacji
        Optional<Customer> c = cs.searchForCustomerByPesel(pesel);
        displayConsoleMessage("Returned customer: " + c.toString());
        return c;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    public Integer createCustomer(@RequestBody Customer customer){
        Integer id = cs.generateNewId();
        customer.setId(id);
        displayConsoleMessage("Requested to save: " + customer.toString());
        id = cs.checkAndSave(customer);  //sprawdzenie poprawności danych, w przypadku nie zatwierdzenia formularza zwraca null
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        return id;
    }

    //zwraca czas lokalny
    private ZonedDateTime getCurrentTime(){
        return ZonedDateTime.now().withNano(0);
    }

    private void displayConsoleMessage(String msg){
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("msg: " + msg);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }

}
