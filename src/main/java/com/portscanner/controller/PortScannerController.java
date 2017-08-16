package com.portscanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portscanner.business.PortScannerBusiness;
import com.portscanner.business.SlackVerificationTokenBusiness;
import com.portscanner.dto.SlackRequestDTO;
import com.portscanner.dto.SlackResponseDTO;

@RestController
public class PortScannerController {
	
	@Autowired
	private PortScannerBusiness portScannerBusiness;
	
	@Autowired
	private SlackVerificationTokenBusiness slackVerificationTokenBusiness;
	
	@RequestMapping(path = "/ports", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<SlackResponseDTO> getAvailablePorts(@RequestBody SlackRequestDTO slackRequestDTO) {
		SlackResponseDTO slackResponseDTO = new SlackResponseDTO();
		
		if (slackRequestDTO.getToken() == null ||
				slackVerificationTokenBusiness.isRequestComingFromSlack(slackRequestDTO.getToken())) {
			slackResponseDTO.setText("The provided Validation Token is not valid!");
			return new ResponseEntity<SlackResponseDTO>(slackResponseDTO, HttpStatus.FORBIDDEN);
		}
		
		try {
			slackResponseDTO = portScannerBusiness.getOpenPorts(slackRequestDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<SlackResponseDTO>(slackResponseDTO, HttpStatus.OK); 
	}

}
