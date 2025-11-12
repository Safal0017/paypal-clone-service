package com.paypal.wallet_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreditRequest DTO
 */
@Data
@NoArgsConstructor
public class CreditRequest {
    private Long userId;
    private String currency;
    private Long amount;
}