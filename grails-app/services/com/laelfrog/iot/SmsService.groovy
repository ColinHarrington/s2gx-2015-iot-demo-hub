package com.laelfrog.iot

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair
import org.springframework.beans.factory.annotation.Value;

class SmsService {
	static transactional = false

	@Value('${sms.twilio.accountSID}')
	String accountSID

	@Value('${sms.twilio.authToken}')
	String authToken

	@Value('${sms.twilio.to}')
	String to

	@Value('${sms.twilio.from}')
	String from

	def sendMessage(String txt) {
		TwilioRestClient client = new TwilioRestClient(accountSID, authToken)

		// Build a filter for the MessageList
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", txt))
		params.add(new BasicNameValuePair("To", to))
		params.add(new BasicNameValuePair("From", from))

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		Message message = messageFactory.create(params);
		System.out.println(message.getSid());
	}
}
