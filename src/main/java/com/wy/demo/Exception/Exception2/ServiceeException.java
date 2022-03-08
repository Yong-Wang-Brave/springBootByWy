package com.wy.demo.Exception.Exception2;

import com.wy.demo.Exception.ServiceException;
import lombok.Data;

@Data
public class ServiceeException extends RuntimeException {

    private String code = Result.SERVICE_ERROR_CODE;
    private String msg;

    public ServiceeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ServiceeException(String msg, Throwable e) {

        super(msg, e);
        this.msg = msg;
    }

    public ServiceeException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ServiceeException(String code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
