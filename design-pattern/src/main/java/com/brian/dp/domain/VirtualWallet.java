package com.brian.dp.domain;

import com.brian.dp.exception.BizException;

import java.math.BigDecimal;

/**
 * 虚拟钱包domain
 *
 * @author BrianX
 * @date 2022/08/25 18:11
 **/
public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId){
        this.id = preAllocatedId;
    }

    public BigDecimal balance(){
        return this.balance;
    }

    public void debit(BigDecimal amount){
        if(this.balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException("Err_001");
        }
    }
}
