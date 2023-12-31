package com.skm.accounts.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    BaseEntity baseEntity;

    @BeforeEach
    void setUp() {
        baseEntity=new BaseEntity();
        baseEntity.setCreatedBy("Sofia");
    }

    @Test
    void getCreatedBy() {
        assertEquals(baseEntity.getCreatedBy(),"Sofia");
    }
}