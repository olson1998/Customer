package com.customer.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoriesTest {

    @Autowired
    CustomerRepository cr;

    @Test
    public void testGetByPeselCorrectPesel(){
        assertNotNull(cr.getByPesel("98122812345"));
    }

    @Test
    public void testGetByPeselToShortPesel(){
        assertEquals(Optional.empty(),cr.getByPesel("98122812"));
    }

    @Test
    public void testGetAllCustomers(){
        assertNotEquals(0, cr.getAllCustomers().size());
    }
}
