package com.paypal.transaction_service.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HoldResponse with only getters and all-args constructor
 */
@Getter
@AllArgsConstructor
public class HoldResponse {
    private String holdReference;
    private Long amount;
    private String status;
}
