package com.portscanner.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portscanner.dto.SlackRequestDTO;
import com.portscanner.dto.SlackResponseDTO;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
	
	@RequestMapping(method = RequestMethod.POST)
	public SlackResponseDTO healthCheck(@RequestBody SlackRequestDTO slackRequestDTO) {
		String textMessage = "Hi " + slackRequestDTO.getUser_name() + "! Everything works fine here.";
		
		return new SlackResponseDTO(textMessage, null);
	}

}
