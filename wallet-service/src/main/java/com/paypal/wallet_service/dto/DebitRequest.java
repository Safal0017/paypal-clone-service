package com.paypal.wallet_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DebitRequest DTO
 */
@Data
@NoArgsConstructor
public class DebitRequest {
    private Long userId;
    private String currency;
    private Long amount;
}
