package com.modus.edeliveryclient.model;

public class Messages
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
        return "ClassPojo [MessageId = "+MessageId+"]";
    }
}
