package org.cloudfoundry.samples.test;

import org.cloudfoundry.samples.MailUtility;

public class TestSendMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailUtility mailUtility = new MailUtility();
		try {
			mailUtility.sendMail("smtp.sendgrid.net", 465, "username", "password", "recipient@someemail.com", "sender@someemail.com", "body test", "test send email");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
