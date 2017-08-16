package com.portscanner.business;

import java.util.concurrent.ExecutionException;

import com.portscanner.dto.OpenPortDTO;

public interface PortScannerBusiness {
	
	OpenPortDTO getOpenPorts(String ip) throws InterruptedException, ExecutionException;
	
}
