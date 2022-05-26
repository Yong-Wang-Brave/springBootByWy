package com.wy.demo.java8;

import com.wy.demo.java8.PresentOrElseHandler.PresentOrElseHandler;
import com.wy.demo.java8.ThrowExceptionFunctionWy.ThrowExceptionFunction;
import com.wy.demo.java8.isTureOrFalseWy.BranchHandle;

public class VUtils {
    /**  场景1：处理抛出异常的if
     *  如果参数为true抛出异常
     *
     * @param b
     * @return com.example.demo.func.ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isTure(boolean b){

        return (errorMessage) -> {
            if (b){
                throw new RuntimeException(errorMessage);
            }
        };
    }

    /**
            * 参数为true或false时，分别进行不同的操作
 *
         * @param b
 * @return com.example.demo.func.BranchHandle
 **/
    public static BranchHandle isTureOrFalse(boolean b){

        return (trueHandle, falseHandle) -> {
            if (b){
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }
    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param
     * @return com.example.demo.func.BranchHandle
     **/
    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str){

        return (consumer, runnable) -> {
            if (str == null || str.length() == 0){
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }


}
