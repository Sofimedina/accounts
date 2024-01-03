package com.skm.accounts.service.Impl;

import com.skm.accounts.constants.AccountsConstants;
import com.skm.accounts.dto.AccountsDto;
import com.skm.accounts.dto.CustomerDto;
import com.skm.accounts.entity.Accounts;
import com.skm.accounts.entity.Customer;
import com.skm.accounts.exception.CustomerAlreadyExistException;
import com.skm.accounts.exception.ResourceNotFoundException;
import com.skm.accounts.mapper.AccountsMapper;
import com.skm.accounts.mapper.CustomerMapper;
import com.skm.accounts.repository.AccountsRepository;
import com.skm.accounts.repository.CustomerRepository;
import com.skm.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service @AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto
     */

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer=CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already exist with given mobile number:"+
                    customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     * @param customer
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber=1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }

    /**
     * @param mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer= customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Customer","Mobile number",mobileNumber));
        Accounts accounts=accountsRepository
                .findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        ()->new ResourceNotFoundException("Account","customerID",customer.getCustomerId().toString()));
        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;

    }
}
