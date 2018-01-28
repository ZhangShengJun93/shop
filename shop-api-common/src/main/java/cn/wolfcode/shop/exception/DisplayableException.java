package cn.wolfcode.shop.exception;

import java.io.Serializable;

public class DisplayableException extends RuntimeException implements Serializable{
    public DisplayableException(String message) {
        super(message);
    }

}
