package com.paypal.wallet_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaptureRequest DTO
 */
@Data
@NoArgsConstructor
public class CaptureRequest {
    private String holdReference;
}