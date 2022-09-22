package com.wy.demo.Exception.Exception2;

import lombok.Data;

@Data
public class HealthManageException extends RuntimeException {

    private String code = Result.SERVICE_ERROR_CODE;
    private String msg;

    public HealthManageException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public HealthManageException(String msg, Throwable e) {

        super(msg, e);
        this.msg = msg;
    }

    public HealthManageException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public HealthManageException(String code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
