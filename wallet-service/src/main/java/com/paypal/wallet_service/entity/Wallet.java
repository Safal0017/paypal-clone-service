package com.paypal.wallet_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entity representing a Wallet.
 */
@Entity
@Table(name = "wallets")
@Data // ✅ Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // ✅ No-args constructor
@AllArgsConstructor // ✅ All-args constructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false, length = 3)
    @Builder.Default
    private String currency = "INR";

    @Column(nullable = false)
    @Builder.Default
    private Long balance = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long availableBalance = 0L;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Wallet(Long userId, String currency) {
        this.userId = userId;
        this.currency = currency;
        this.balance = 0L;
        this.availableBalance = 0L;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}

