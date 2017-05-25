package com.modus.edeliveryclient.model;

public  class TestJson {
	public TestJson() {
	}

	private Messages Messages;

	private String NumberOfMessages;

	public Messages getMessages() {
		return Messages;
	}

	public void setMessages(Messages Messages) {
		this.Messages = Messages;
	}

	public String getNumberOfMessages() {
		return NumberOfMessages;
	}

	public void setNumberOfMessages(String NumberOfMessages) {
		this.NumberOfMessages = NumberOfMessages;
	}

	@Override
	public String toString() {
		return "ClassPojo [Messages = " + Messages + ", NumberOfMessages = " + NumberOfMessages + "]";
	}
}
