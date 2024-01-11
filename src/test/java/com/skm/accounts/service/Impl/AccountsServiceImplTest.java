package com.skm.accounts.service.Impl;

import com.skm.accounts.dto.AccountsDto;
import com.skm.accounts.dto.CustomerDto;
import com.skm.accounts.entity.Accounts;
import com.skm.accounts.entity.Customer;
import com.skm.accounts.exception.CustomerAlreadyExistException;
import com.skm.accounts.exception.ResourceNotFoundException;
import com.skm.accounts.repository.AccountsRepository;
import com.skm.accounts.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountsServiceImplTest {
    @InjectMocks
    AccountsServiceImpl accountsService;
    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private CustomerRepository customerRepository;
    private CustomerDto customerDto;
    private AccountsDto accountsDto;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setName("Sofia");
        customerDto.setEmail("sofi.k.medina");
        customerDto.setMobileNumber("3875121101");
        accountsDto=new AccountsDto();
        customerDto.setAccountsDto(accountsDto);
    }

    @Test
    void createAccountSuccess() {
        //given
        given(customerRepository
                .findByMobileNumber(anyString()))
                .willReturn(Optional.empty());
        given(customerRepository.save(any(Customer.class)))
                .willReturn(new Customer());
        given(accountsRepository.save(any(Accounts.class)))
                .willReturn(new Accounts());


        //when
        accountsService.createAccount(customerDto);
        //then
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(accountsRepository, times(1)).save(any(Accounts.class));
    }

    @Test
    void createAccountCustomerAlreadyExists() {
        //given
        given(customerRepository
                .findByMobileNumber(anyString()))
                .willReturn(Optional.of(new Customer()));

        //when
        CustomerAlreadyExistException exception = assertThrows(CustomerAlreadyExistException.class,
                () -> accountsService.createAccount(customerDto));
        //then
        assertEquals("Customer already exist with given mobile number:" + customerDto.getMobileNumber(), exception.getMessage());
    }

    @Test
    void fetchAccount() {
        //given
        //Mock Data
        String mobileNumber = "1234567890";
        Long customerId = 1L;

        // Mock Customer
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(customerId);
        mockCustomer.setMobileNumber(mobileNumber);

        // Mock Accounts
        Accounts mockAccount = new Accounts();
        mockAccount.setCustomerId(customerId);
        given(customerRepository.findByMobileNumber(mobileNumber))
                .willReturn(Optional.of(mockCustomer));

        given(accountsRepository.findByCustomerId(customerId))
                .willReturn(Optional.of(mockAccount));

        //when
        CustomerDto customerDtoFetched = accountsService.fetchAccount(mobileNumber);

        //then
        verify(customerRepository, times(1)).findByMobileNumber(mobileNumber);
        verify(accountsRepository, times(1)).findByCustomerId(customerId);

        assertNotNull(customerDtoFetched);
        assertEquals(mobileNumber, customerDtoFetched.getMobileNumber());
    }

    @Test
    void fetchAccountNotFound() {
        //given
        //Mock Data
        String mobileNumber = "1234567890";
        Long customerId = 1L;

        // Mock Customer
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(customerId);
        mockCustomer.setMobileNumber(mobileNumber);


        given(customerRepository.findByMobileNumber(mobileNumber))
                .willReturn(Optional.of(mockCustomer));


        given(accountsRepository.findByCustomerId(customerId))
                .willReturn(Optional.empty());

        //when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> accountsService.fetchAccount(mobileNumber));
        //then
        assertEquals(exception.getMessage(), "Account not found with the given input data customerID: '" + customerId + "'");
    }

    @Test
    void fetchAccountCustomerNotFound() {
        //given
        String mobileNumber = "1234567890";
        given(customerRepository.findByMobileNumber(anyString()))
                .willReturn(Optional.empty());

        //when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> accountsService.fetchAccount(mobileNumber));
        //then
        assertEquals(exception.getMessage(), "Customer not found with the given input data Mobile number: '" + mobileNumber + "'");
    }

    @Test
    void updateAccount() {
        //TODO REVISE LOGIC
        /*// Mock data
        AccountsDto mockAccountsDto = new AccountsDto();
        mockAccountsDto.setAccountNumber(123456L);

        CustomerDto mockCustomerDto = new CustomerDto();
        mockCustomerDto.setAccountsDto(mockAccountsDto);

        // Mock Accounts
        Accounts mockAccounts = new Accounts();
        mockAccounts.setAccountNumber(mockAccountsDto.getAccountNumber());
        mockAccounts.setCustomerId(1L);

        // Mock Customer
        Long customerId = mockAccounts.getCustomerId();
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(customerId);

        // Mocking repository calls
        when(accountsRepository.findById(mockAccountsDto.getAccountNumber())).thenReturn(Optional.of(mockAccounts));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

        // Execute the method
        boolean result = accountsService.updateAccount(mockCustomerDto);

        // Verify the calls
        verify(accountsRepository, times(1)).findById(mockAccountsDto.getAccountNumber());
        verify(customerRepository, times(1)).findById(customerId);
        verify(accountsRepository, times(1)).save(mockAccounts);
        verify(customerRepository, times(1)).save(mockCustomer);

        // Assertions
        assertTrue(result);*/
    }

    @Test
    void deleteAccount() {
        // Mock data
        String mobileNumber = "1234567890";

        // Mock Customer
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(1L);

        // Mocking repository calls
        when(customerRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(mockCustomer));

        // Execute the method
        boolean result = accountsService.deleteAccount(mobileNumber);

        // Verify the calls
        verify(customerRepository, times(1)).findByMobileNumber(mobileNumber);
        verify(accountsRepository, times(1)).deleteByCustomerId(mockCustomer.getCustomerId());
        verify(customerRepository, times(1)).deleteById(mockCustomer.getCustomerId());

        // Assertions
        assertTrue(result);
    }

}