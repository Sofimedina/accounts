package com.skm.accounts.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp ="(^$|[0-9]{10})" ,message = "Account number should contain 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Type number can not be null or empty")
    private String accountType;
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;

}
