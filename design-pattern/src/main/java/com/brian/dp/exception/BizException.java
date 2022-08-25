package com.brian.dp.exception;

/**
 * 余额不足异常
 *
 * @author BrianX
 * @date 2022/08/25 18:22
 **/
public class BizException extends RuntimeException {
    private String code;
    private String message;
    private Object [] args;

    public BizException(String code) {
        this.code = code;
    }

    public BizException(String code,Object... args){
        this.code = code;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
