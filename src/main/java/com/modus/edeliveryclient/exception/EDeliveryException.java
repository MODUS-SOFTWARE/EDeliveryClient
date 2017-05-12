/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.exception;

/**
 *
 * @author Pantelispanka
 */
public class EDeliveryException extends RuntimeException {

    private int status;

    public EDeliveryException() {
    }

    public EDeliveryException(String message) {
        super(message);
    }

    public EDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public EDeliveryException(Throwable cause) {
        super(cause);
    }

    public EDeliveryException(int status, String message) {
        super(message);
        this.status = status;
    }

    public EDeliveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

  
}
