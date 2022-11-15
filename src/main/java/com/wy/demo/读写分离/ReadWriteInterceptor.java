package com.wy.demo.读写分离;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Order(Integer.MAX_VALUE - 2) //@1
@Component
public class ReadWriteInterceptor {

    @Pointcut("target(IService)")
    public void pointcut() {
    }

    //获取当前目标方法的最后一个参数
    private Object getLastArgs(final ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (Objects.nonNull(args) && args.length > 0) {
            return args[args.length - 1];
        } else {
            return null;
        }
    }

    @Around("pointcut()")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        //获取当前的dsType
        DsType oldDsType = DsTypeHolder.getDsType();
        try {
            //获取最后一个参数
            Object lastArgs = getLastArgs(pjp);
            //lastArgs为SLAVE，走从库，其他的走主库
            if (DsType.SLAVE.equals(lastArgs)) {
                DsTypeHolder.slave();
            } else {
                DsTypeHolder.master();
            }
            return pjp.proceed();
        } finally {
            //退出的时候，还原dsType
            DsTypeHolder.setDsType(oldDsType);
        }
    }
}