package com.artsoft.scb.model.entity;

import java.util.List;

public class Message {

	private String htmlBody;
	private String textBody;
	private List<String> destinies;
	private String subject;

	public String getHtmlBody() {
		return htmlBody;
	}

	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public List<String> getDestinies() {
		return destinies;
	}

	public void setDestinies(List<String> destinies) {
		this.destinies = destinies;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}