package com.customer.app;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Resource
    private CustomerRepository cr;

    public List<Customer> getAllCustomers(){
        return cr.findAll();
    }

    public Optional<Customer> searchForCustomerByPesel(String pesel){
        if(pesel.length() == 11){ return cr.getByPesel(pesel); } //sprawdzenie czy zgadza się ilość cyfr w peselu
        else{return Optional.empty();} //zwracanie pustego Optionala w przypadku braku odnalezienia klienta
    }

    public String checkAndSave(Customer c){
        String msg = null;
        if(c.getPesel().length() !=11){ msg = "pesel must have 11 digits!"; }
        if(c.getFirstName() == null){ msg = "no name given!"; }
        if(c.getLastName() == null){ msg = "no surname given!"; }
        else{msg = cr.saveCustomer(c) + " ";} //w przypadku spełnienia warunków wykonaj zapis i zwróć wiadomość z nadanym id
        return msg;
    }
}
