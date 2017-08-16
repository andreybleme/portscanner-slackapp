package com.portscanner.business;

public interface SlackVerificationTokenBusiness {
	
	boolean isRequestComingFromSlack(String verificationToken);
	
}
