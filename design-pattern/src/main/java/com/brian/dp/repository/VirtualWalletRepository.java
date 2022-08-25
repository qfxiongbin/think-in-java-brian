package com.brian.dp.repository;

import com.brian.dp.entity.VirtualWalletEntity;

import java.math.BigDecimal;

/**
 * VirtualWalletRepo
 *
 * @author BrianX
 * @date 2022/08/25 22:24
 **/
public class VirtualWalletRepository {

    public VirtualWalletEntity getWalletEntity(Long walletId){
        //此处从数据库中查询TODO
        VirtualWalletEntity wallet = new VirtualWalletEntity();
        return wallet;
    }

    public BigDecimal getBalance(Long walletId) {
        return new BigDecimal(1000L);
    }
}
