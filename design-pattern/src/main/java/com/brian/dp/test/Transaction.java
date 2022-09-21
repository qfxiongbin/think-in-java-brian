package com.brian.dp.test;

import com.brian.dp.rpc.WalletRpcService;
import lombok.Data;

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

    public Transaction(String preAssignedId,Long buyerId,Long sellerId,Long productId,String orderId) {
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
        this.status = STATUS.TO_BE_EXPIRED;
        this.createTimestamp = System.currentTimeMillis();
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
            isLocked = RedisDistributedLock.getSingletonInstance().lockTransaction(id);
            if(!isLocked){
                return false;
            }
            if(status == STATUS.EXECUTED){
                return true;
            }
            long executionInvokedTimestamp = System.currentTimeMillis();
            long interval = (executionInvokedTimestamp - createTimestamp) / (1000*3600*24);
            if(interval > 14){
                this.status = STATUS.EXPIRED;
                return false;
            }
            WalletRpcService walletRpcService = new WalletRpcService();
            String walletTransactionId = walletRpcService.moveMoney(id,buyerId,sellerId);
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
                RedisDistributedLock.getSingletonInstance().unlockTransaction(id);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getWalletTransactionId() {
        return walletTransactionId;
    }

    public void setWalletTransactionId(String walletTransactionId) {
        this.walletTransactionId = walletTransactionId;
    }
}
