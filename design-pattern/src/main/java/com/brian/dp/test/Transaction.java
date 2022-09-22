package com.brian.dp.test;

import com.brian.dp.rpc.WalletRpcService;
import lombok.Data;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;

/**
 * Mall Transaction
 *
 * @author BrianX
 * @date 2022/09/21 13:23
 **/
public class Transaction {

    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;

    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;

    private String walletTransactionId;

    private WalletRpcService walletRpcService;

    private TransactionLock lock;

    public void setTransactionLock(TransactionLock lock){
        this.lock = lock;
    }

    //通过依赖注入的方式提供rpc示例，方便测试
    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    public Transaction(String preAssignedId,Long buyerId,Long sellerId,Long productId,Long orderId,double amount) {
        if(preAssignedId != null && !preAssignedId.isEmpty()){
            this.id = preAssignedId;
        }else{
            this.id = IdGenerator.generateTransactionId();
        }

        if(this.id.startsWith("t_")){
            this.id = "t_"+preAssignedId;
        }
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.status = this.setStatus();
        this.createTimestamp = System.currentTimeMillis();
        this.amount = amount;
    }

    /**
     *
    * @param :
    *
    * @return boolean
    * @author BrianX
    * @description
     * 负责执行转账操作，交易成功后，将钱从买家钱包转到卖家钱包
    * @date 2022/9/21 17:09
    */
    public boolean execute() {
        if(buyerId  == null || (sellerId == null || amount < 0.0)) {

        }
        if(status == STATUS.EXECUTED){
            return true;
        }
        boolean isLocked = false;
        try {
            isLocked = lock.lock(id);
            if(!isLocked){
                return false;
            }
            //double check
            if(status == STATUS.EXECUTED){
                return true;
            }
            if(isExpired()){
                this.status = STATUS.EXPIRED;
                return false;
            }
//            WalletRpcService walletRpcService = new WalletRpcService();
            String walletTransactionId = walletRpcService.moveMoney(id,buyerId,sellerId,amount);
            if(walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            }else{
                this.status = STATUS.FAILED;
                return false;
            }
        }finally {
            if(isLocked){
                lock.unlock(id);
            }
        }
    }

    protected boolean isExpired(){
        long executionInvokedTimestamp = System.currentTimeMillis();
        long interval = (executionInvokedTimestamp - createTimestamp) / (1000*3600*24);
        if(interval > 14) {
            return true;
        }
        return false;
    }

    protected STATUS setStatus(){
        return STATUS.TO_BE_EXPIRED;
    }

    public STATUS getStatus(){
        return status;
    }


}
