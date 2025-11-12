package com.paypal.wallet_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity representing WalletHold.
 */
@Entity
@Table(name = "wallet_holds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletHold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(nullable = false)
    private String holdReference;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Builder.Default
    private String status = "ACTIVE";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime expiresAt;
}
