package com.customer.app;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    default List<Customer> getAllCustomers(){
        return findAll();
    }

    default Optional<Customer> getByPesel(String pesel){
        Optional<Customer> customer = Optional.empty(); //tworzenie Customera bez przypisania wartości polom
        List<Customer> col = findAll() //Lista zebranych Customerów z użyciem metody findall z JPA repository
                .stream()
                .filter(c-> c.getPesel().equals(pesel))
                .collect(Collectors.toList());
        if(col.size() > 1){
            //w przypadku gdy zebranych zostanie więcej niż 1 customer wyświetl błąd i zwracaj pustego customera
            throw new DuplicateKeyException("<<< More than one person has same pesel number!!!! >>>");
        }
        if(col.size() ==1 ){
            //w przypadku gdy lista ma 1 element to zwracaj ten elemnt jako szukany customer
            customer = Optional.ofNullable(col.get(0));
        }
        return customer;
    }

    default String saveCustomer(Customer c){
        save(c);
        return c.getId() + " ";
    }
}
