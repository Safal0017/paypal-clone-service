package com.paypal.wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * HoldResponse DTO
 */
@Data // ✅ Generates getters/setters, toString, equals, hashCode
@AllArgsConstructor // ✅ All-args constructor
public class HoldResponse {
    private String holdReference;
    private Long amount;
    private String status;
}
