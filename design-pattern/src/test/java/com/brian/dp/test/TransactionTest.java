package com.brian.dp.test;

import com.brian.dp.mock.MockWalletRpcServiceOne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void execute() {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        double amount = 100;
        Transaction transaction = new Transaction(null,buyerId,sellerId,productId,orderId,amount);
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
    }

    @Test
    void execute_transaction(){
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        double amount = 100;
        TransactionLock lock = new TransactionLock() {
            public boolean lock(String id){
                return true;
            }

            public void unlock(String id){

            }

        };
        Transaction transaction = new Transaction(null,buyerId,sellerId,productId,orderId,amount);
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        transaction.setTransactionLock(lock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
    }

    /**
    * @param :
    *
    * @return void
    * @author BrianX
    * @description Transaction success
    * @date 2022/9/22 17:00
    */
    @Test
    void testTransactionExecuted(){
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        double amount = 100;
        TransactionLock lock = new TransactionLock() {
            public boolean lock(String id){
                return true;
            }

            public void unlock(String id){

            }
        };

        MockWalletRpcServiceOne mockWalletRpcServiceOne = new MockWalletRpcServiceOne(){
            public String moveMoney(String id,Long buyerId,Long sellerId,double amount){
                return "123";
            }

        };
        Transaction transaction = new Transaction(null,buyerId,sellerId,productId,orderId,amount);
        transaction.setWalletRpcService(mockWalletRpcServiceOne);
        transaction.setTransactionLock(lock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
    }

    /**
    * @param :
    *
    * @return void
    * @author BrianX
    * @description Transaction failed case
    * @date 2022/9/22 17:01
    */
    @Test
    void transactionFailed(){
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        double amount = 100;
        TransactionLock lock = new TransactionLock() {
            public boolean lock(String id){
                return true;
            }

            public void unlock(String id){

            }
        };

        MockWalletRpcServiceOne mockWalletRpcServiceOne = new MockWalletRpcServiceOne(){
            public String moveMoney(String id,Long buyerId,Long sellerId,double amount){
                return null;
            }

        };
        Transaction transaction = new Transaction(null,buyerId,sellerId,productId,orderId,amount);
        transaction.setWalletRpcService(mockWalletRpcServiceOne);
        transaction.setTransactionLock(lock);
        boolean executedResult = transaction.execute();
        assertFalse(executedResult);
    }

    /**
    * @param :
    *
    * @return void
    * @author BrianX
    * @description test transaction executing case
    * @date 2022/9/22 17:08
    */
    @Test
    public void testTransactionExecuting(){
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        double amount = 100;
        TransactionLock lock = new TransactionLock() {
            public boolean lock(String id){
                return true;
            }

            public void unlock(String id){

            }
        };

        MockWalletRpcServiceOne mockWalletRpcServiceOne = new MockWalletRpcServiceOne(){
            public String moveMoney(String id,Long buyerId,Long sellerId,double amount){
                return "123";
            }

        };
        Transaction transaction = new Transaction(null,buyerId,sellerId,productId,orderId,amount){
            @Override
            protected STATUS setStatus() {
                return STATUS.EXECUTED;
            }
        };
        transaction.setWalletRpcService(mockWalletRpcServiceOne);
        transaction.setTransactionLock(lock);
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED,transaction.getStatus());
    }

}