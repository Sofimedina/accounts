package com.skm.accounts.service;

import com.skm.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto
     */
     void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     */
     CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);



}
