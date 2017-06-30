/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.models;

import java.io.File;
import java.util.Collection;

/**
 *
 * @author Pantelispanka
 */
public class ResponseModel<E> {

    private boolean success = true; //success param for stores
    private Collection<E> data; //rootproperty
//    private MetaDataModel metaData;
    private E simpleData;
    private int messageType = 2; //SUCCESS TODO
    private String messageTitle;
    private String processMessage;
    private int total; //totalproperty

    private Boolean validated;
    private String response;

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Collection<E> getData() {
        return data;
    }

    public void setData(Collection<E> data) {
        this.data = data;
    }

//    public MetaDataModel getMetaData() {
//        return metaData;
//    }
//
//    public void setMetaData(MetaDataModel metaData) {
//        this.metaData = metaData;
//    }
    public E getSimpleData() {
        return simpleData;
    }

    public void setSimpleData(E simpleData) {
        this.simpleData = simpleData;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
