package com.brian.dp.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * wallet transaction entity
 *
 * @author BrianX
 * @date 2022/08/25 23:23
 **/
@Data
public class VirtualWalletTransactionEntity {
    private BigDecimal amount;
}
