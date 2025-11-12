package com.paypal.user_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO representing a request to create a new wallet.
 */
@Data // ✅ Generates getters, setters, equals, hashCode, and toString
@NoArgsConstructor // ✅ No-args constructor (required by Jackson)
@AllArgsConstructor // ✅ All-args constructor
public class CreateWalletRequest {
    private Long userId;
    private String currency;
}
