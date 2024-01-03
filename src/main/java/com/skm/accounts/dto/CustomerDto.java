package com.skm.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The lenght of a customer should be beetween 5 and 30")
    private String name;
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Invalid email name")
    private String email;
    @Pattern(regexp ="(^$|[0-9]{10})" ,message = "Number shoul contain 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
