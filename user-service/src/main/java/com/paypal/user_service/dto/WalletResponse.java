package com.paypal.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a user's wallet response.
 */
@Data // ✅ Generates getters, setters, equals, hashCode, and toString
@NoArgsConstructor // ✅ No-args constructor (required by Jackson)
@AllArgsConstructor // ✅ All-args constructor
public class WalletResponse {
    private Long id;
    private Long userId;
    private String currency;
    private Long balance;
    private Long availableBalance;
}
