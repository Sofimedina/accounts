package com.skm.accounts.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostumerTest {

    Customer costumer;

    @BeforeEach
    void setUp() {
        costumer=new Customer();
        costumer.setCreatedBy("Sofi");
    }

    @Test
    void getCreatedBy() {
        assertEquals(costumer.getCreatedBy(),"Sofi");
    }
}