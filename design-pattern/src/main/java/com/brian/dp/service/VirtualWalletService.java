package com.brian.dp.service;

import com.brian.dp.domain.VirtualWallet;
import com.brian.dp.entity.VirtualWalletEntity;
import com.brian.dp.exception.BizException;
import com.brian.dp.repository.VirtualWalletRepository;
import com.brian.dp.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * service
 *
 * @author BrianX
 * @date 2022/08/25 22:12
 **/
public class VirtualWalletService {
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepository;

    /**
     * get wallet by walletId
    * @param walletId:
    *
    * @return VirtualWallet
    * @author BrianX
    * @description TODO
    * @date 2022/8/25 22:43
    */
    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    /**
     * get balance by walletId
    * @param walletId:
    *
    * @return BigDecimal
    * @author BrianX
    * @description TODO
    * @date 2022/8/25 22:44
    */
    public BigDecimal getBalance(Long walletId){
        return walletRepo.getBalance(walletId);
    }

    //@Transaction
    public void debit(Long walletId,BigDecimal amount){
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if(balance.compareTo(amount) < 0){
            throw new BizException("Err_001");
        }


    }



    private VirtualWallet convert(VirtualWalletEntity walletEntity){
        return new VirtualWallet(1000L);
    }


}
