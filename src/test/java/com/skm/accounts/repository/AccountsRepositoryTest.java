package com.skm.accounts.repository;

import com.skm.accounts.entity.Customer;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;


@DisplayName("When customer is saved test")
@DataJpaTest
class AccountsRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @Test
    public void givenCustomer_whenSaved_thenReturnCustomer(){
        //given
        Customer customer=new Customer();
        customer.setEmail("sofi@mail.com");
        customer.setName("Sofia");
        customer.setMobileNumber("3875121101");
        LocalDateTime now = LocalDateTime.now();
        customer.setCreatedAt(now);
        customer.setCreatedBy("Skme");
        customer.setUpdatedAt(now);
        customer.setUpdatedBy("Sofikm");
        //when
        Customer customerSaved=customerRepository.save(customer);
        //then
        assertThat(customerSaved).isNotNull();
        assertThat(customerSaved.getCustomerId()).isGreaterThan(0);
    }
}