package com.portscanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portscanner.business.SlackVerificationTokenBusiness;
import com.portscanner.dto.SlackRequestDTO;
import com.portscanner.dto.SlackResponseDTO;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
	
	@Autowired
	private SlackVerificationTokenBusiness slackVerificationTokenBusiness;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SlackResponseDTO>  healthCheck(SlackRequestDTO slackRequestDTO) {
		SlackResponseDTO slackResponseDTO = new SlackResponseDTO();
		
		if (slackRequestDTO.getToken() == null || !slackVerificationTokenBusiness.isRequestComingFromSlack(slackRequestDTO.getToken())) {
			slackResponseDTO.setText("The provided Validation Token is not valid!");
			return new ResponseEntity<SlackResponseDTO>(slackResponseDTO, HttpStatus.FORBIDDEN);
		}
		
		slackResponseDTO.setText("Hi " + slackRequestDTO.getUser_name() + "! Everything works fine here.");
		return new ResponseEntity<SlackResponseDTO>(slackResponseDTO, HttpStatus.OK);
	}

}
