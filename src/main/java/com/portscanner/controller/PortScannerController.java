package com.portscanner.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortScannerController {
	
	@RequestMapping(path = "/ports", method = RequestMethod.GET)
	public String getAvailablePorts() {
		return "8080";
	}

}
