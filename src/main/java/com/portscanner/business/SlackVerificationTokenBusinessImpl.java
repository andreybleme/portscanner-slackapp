package com.portscanner.business;

import org.springframework.stereotype.Component;

@Component
public class SlackVerificationTokenBusinessImpl implements SlackVerificationTokenBusiness {
	
	private static final String VERIFICATION_TOKEN = "ldVs4lciYm6t0IEBwlXLE9ra";
	
	@Override
	public boolean isRequestComingFromSlack(String verificationToken) {
		return verificationToken.equals(VERIFICATION_TOKEN);
	}
}
