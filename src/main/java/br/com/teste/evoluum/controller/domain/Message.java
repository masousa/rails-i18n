package br.com.teste.evoluum.controller.domain;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = -5554434963618490913L;
	private String textMessage;
	
	public Message(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

}
