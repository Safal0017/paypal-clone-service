package com.paypal.wallet_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateWalletRequest DTO
 */
@Data
@NoArgsConstructor
public class CreateWalletRequest {
    private Long userId;
    private String currency;
}
