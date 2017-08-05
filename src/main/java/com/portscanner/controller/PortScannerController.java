package com.portscanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portscanner.business.PortScannerBusiness;
import com.portscanner.dto.OpenPortDTO;

@RestController
public class PortScannerController {
	
	@Autowired
	private PortScannerBusiness portScannerBusiness;
	
	@RequestMapping(path = "/ports", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<OpenPortDTO> getAvailablePorts(@RequestParam("ip") String ip) {
		OpenPortDTO openPortsDTO;
		
		try {
			openPortsDTO = portScannerBusiness.getOpenPorts(ip);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<OpenPortDTO>(openPortsDTO, HttpStatus.OK); 
	}

}
