/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modus.edeliveryclient.models;

import java.util.List;

/**
 *
 * @author Pantelispanka
 */
public class MessageId
{
    private String[] MessageId;

    public String[] getMessageId ()
    {
        return MessageId;
    }

    public void setMessageId (String[] MessageId)
    {
        this.MessageId = MessageId;
    }

    @Override
    public String toString()
    {
        return "MessageId [MessageId = "+MessageId+"]";
    }
}

