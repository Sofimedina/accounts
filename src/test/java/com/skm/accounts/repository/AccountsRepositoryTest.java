package com.skm.accounts.repository;

import com.skm.accounts.entity.Accounts;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@DisplayName("Save Account")
@DataJpaTest
class AccountsRepositoryTest {
    @Autowired
    AccountsRepository accountsRepository;
    private Accounts accounts1;
    private Accounts accounts2;

    @BeforeEach
    public void setup(){
        accounts1=new Accounts();
        accounts2=new Accounts();
        LocalDateTime time = LocalDateTime.now();

        accounts1.setAccountNumber(1578564583L);
        accounts1.setAccountType("Savings");
        accounts1.setBranchAddress("123 Main Street, New York");
        accounts1.setCustomerId(1L);
        accounts1.setCreatedAt(time);
        accounts1.setCreatedBy("SKMEDINA");
        accounts1.setUpdatedAt(time);
        accounts1.setUpdatedBy("SKMEDINA");

        accounts2.setAccountNumber(1578564584L);
        accounts2.setAccountType("Savings-Investments");
        accounts2.setBranchAddress("0123 live stret, NY");
        accounts2.setCustomerId(2L);
        accounts2.setCreatedAt(time);
        accounts2.setCreatedBy("SKMEDINA");
        accounts2.setUpdatedAt(time);
        accounts2.setUpdatedBy("SKMEDINA");
    }


    @Test
    public void givenAccount_whenSaved_thenReturnAccount(){

        //given
        //Setup

        //when
        Accounts accountSaved1=accountsRepository.save(accounts1);
        Accounts accountSaved2=accountsRepository.save(accounts2);
        //then
        assertThat(accountSaved1).isNotNull();
        assertThat(accountSaved2).isNotNull();
        assertThat(accountSaved1.getCustomerId()).isEqualTo(1);
        assertThat(accountSaved2.getCustomerId()).isEqualTo(2);
    }




    @Test
    public void givenAccountsSaved_whenGet_thenReturnAccounts(){

        //given
        Accounts accountSaved1=accountsRepository.save(accounts1);
        Accounts accountSaved2=accountsRepository.save(accounts2);

        //when
        List<Accounts> accountsList= accountsRepository.findAll();

        //then
        assertThat(accountsList).size().isEqualTo(2);

    }

    @Test
    public void givenAccountsSaved_whenGetById_thenReturnAccount(){

        //given

        Accounts accountSaved1=accountsRepository.save(accounts1);
        Accounts accountSaved2=accountsRepository.save(accounts2);

        //when
        Optional<Accounts> account= accountsRepository.findById(1578564583L);

        //then
        assertThat(account.get().getCustomerId()).isEqualTo(1L);

    }

    @Test
    public void givenAccount_whenUpdate_thenReturnUpdated(){

        //given

        accountsRepository.save(accounts1);

        //when
        accounts1.setUpdatedBy("Sofi");
        Accounts accountUpdated=accountsRepository.save(accounts1);

        //then
        assertThat(accountUpdated.getUpdatedBy()).isEqualTo("Sofi");

    }

    @Test
    public void givenAccounts_whenDelete_thenReturnAccounts(){

        //given

        accountsRepository.save(accounts1);

        //when
        accountsRepository.deleteById(1578564583L);
        Optional<Accounts> accountDeleted = accountsRepository.findById(1578564583L);

        //then
        assertThat(accountsRepository.findAll()).size().isEqualTo(0);
        assertThat(accountDeleted).isEmpty();

    }

    @Test
    public void givenCustomerIdAccountId_findByJpql_thenReturnAccount(){
        //give- precondition-setup

        accountsRepository.save(accounts1);

        //when action or behavior to test
        Optional<Accounts> account=accountsRepository.findByJPQL(8L,1578564999L);

        //then verify output
        assertThat(account).isNotNull();
    }

    @Test
    public void givenCustomerIdAccountId_findByJpqlNamed_thenReturnAccount(){
        //give- precondition-setup

        accountsRepository.save(accounts1);

        //when action or behavior to test
        Optional<Accounts> account=accountsRepository.findByJPQLNamedParams(8L,1578564999L);

        //then verify output
        assertThat(account).isNotNull();
    }
}