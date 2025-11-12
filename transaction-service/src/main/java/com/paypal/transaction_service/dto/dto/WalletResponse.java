package com.paypal.transaction_service.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * WalletResponse with only getters and all-args constructor
 */
@Getter
@AllArgsConstructor
public class WalletResponse {
    private Long id;
    private Long userId;
    private String currency;
    private Long balance;
    private Long availableBalance;
}
