package com.paypal.wallet_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HoldRequest DTO
 */
@Data // ✅ Getters and setters
@NoArgsConstructor // ✅ No-args constructor
public class HoldRequest {
    private Long userId;
    private String currency;
    private Long amount;
}
