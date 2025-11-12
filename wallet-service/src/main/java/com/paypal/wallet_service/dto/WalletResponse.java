package com.paypal.wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WalletResponse DTO
 */
@Data // ✅ Generates getters/setters, toString, equals, hashCode
@NoArgsConstructor // ✅ No-args constructor (required by Jackson)
@AllArgsConstructor  // ✅ All-args constructor for convenience
public class WalletResponse {
    private Long id;
    private Long userId;
    private String currency;
    private Long balance;
    private Long availableBalance;
}