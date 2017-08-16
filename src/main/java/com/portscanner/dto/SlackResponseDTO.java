package com.portscanner.dto;

import java.util.List;

public class SlackResponseDTO {
	
	private String text;
	private List<String> attachments;
	
	public SlackResponseDTO() {
		super();
	}

	public SlackResponseDTO(String text, List<String> attachments) {
		super();
		this.text = text;
		this.attachments = attachments;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public List<String> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
}
