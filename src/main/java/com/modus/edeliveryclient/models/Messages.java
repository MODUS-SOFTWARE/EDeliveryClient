/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Pantelispanka
 */
public class Messages {

    public Messages() {
    }

    private MessageId Messages;

    private String NumberOfMessages;

    public MessageId getMessages() {
        return Messages;
    }

    public void setMessages(MessageId Messages) {
        this.Messages = Messages;
    }

    public String getNumberOfMessages() {
        return NumberOfMessages;
    }

    public void setNumberOfMessages(String NumberOfMessages) {
        this.NumberOfMessages = NumberOfMessages;
    }
}
