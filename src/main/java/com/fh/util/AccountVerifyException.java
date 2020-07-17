package com.fh.util;

import lombok.Getter;

public class AccountVerifyException extends RuntimeException {

    @Getter
    private Integer code;

    public AccountVerifyException(MyEnum myEnum){
        super(myEnum.getMsg());
        this.code=myEnum.getCode();
    }

}
