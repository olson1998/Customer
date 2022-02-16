package com.customer.app;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Resource
    private CustomerRepository cr;

    //zwraca klientów o podanym w liście id
    public List<Customer> getFilteredCustomers(List<Integer> ids){
        if(ids.size() == 0){return new ArrayList<>();}
        else{return cr.getCertainCustomers(ids);}
    }

    public Optional<Customer> searchForCustomerByPesel(String pesel){
        if(pesel.length() == 11){ return cr.getByPesel(pesel); } //sprawdzenie czy zgadza się ilość cyfr w peselu
        else{return Optional.empty();} //zwracanie pustego Optionala w przypadku braku odnalezienia klienta
    }

    //sprawdza dane przesłane przez aplikację credit i zwraca nadany numer id, w przypadku błędnych danych zwróci null
    public Integer checkAndSave(Customer c){
        String msg = "Did not save... because: ";
        Integer id = null;
        if(c.getPesel().length() !=11){ System.out.println(msg + "pesel must have 11 digits!"); }
        else if(c.getFirstName() == null){ System.out.println(msg + "no name given!");}
        else if(c.getLastName() == null){ System.out.println(msg + "no surname given!");}
        else if(!c.getPesel().chars().allMatch(Character::isDigit)){
            System.out.println(msg + "given pesel contains char other than number");
        }
        else{
            System.out.println("Savend new customer! " + c.toString());
            cr.saveCustomer(c);
            id = c.getId();   //w przypadku gdy dane są poprawne zwróci nadane mu id
        } //w przypadku spełnienia warunków wykonaj zapis i zwróć wiadomość z nadanym id
        return id;
    }

    public int generateNewId(){
        Optional<Integer> id = cr.findLastID();
        if(id.isEmpty()){return 1;}
        else{return id.get()+1;}
    }

}
