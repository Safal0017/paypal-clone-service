package com.paypal.transaction_service.dto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * CreateWalletRequest with getters, setters, no-args constructor
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateWalletRequest {
    private Long userId;
    private String currency;
}
