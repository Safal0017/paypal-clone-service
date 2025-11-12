package com.paypal.transaction_service.dto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * HoldRequest with getters, setters, no-args constructor
 */
@Getter
@Setter
@NoArgsConstructor
public class HoldRequest {
    private Long userId;
    private String currency;
    private Long amount;
}
