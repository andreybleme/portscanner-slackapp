package com.portscanner.dto;

import java.util.List;

public class OpenPortDTO {
	
	private int count;
	private List<String> openPorts;
	
	public OpenPortDTO() {
	}
	
	public OpenPortDTO(int count, List<String> openPorts) {
		this.count = count;
		this.openPorts = openPorts;
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenPorts() {
		return openPorts;
	}

	public void setOpenPorts(List<String> openPorts) {
		this.openPorts = openPorts;
	}	
}
