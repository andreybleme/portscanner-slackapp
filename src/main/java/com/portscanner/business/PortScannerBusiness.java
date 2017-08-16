package com.portscanner.business;

import java.util.concurrent.ExecutionException;

import com.portscanner.dto.SlackRequestDTO;
import com.portscanner.dto.SlackResponseDTO;

public interface PortScannerBusiness {
	
	SlackResponseDTO getOpenPorts(SlackRequestDTO slackRequestDTO) throws InterruptedException, ExecutionException;
	
}
